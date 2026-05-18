package com.Web2Work.demo.service;

import com.Web2Work.demo.model.*;
import com.Web2Work.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InformeService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ConvenioFERepository convenioFERepository;

    @Autowired
    private AsignacionRepository asignacionRepository;

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // RESUMEN GLOBAL DEL SISTEMA
    public Map<String, Object> resumenGlobal() {
        Map<String, Object> resumen = new HashMap<>();
        resumen.put("totalUsuarios", usuarioRepository.count());
        resumen.put("totalAlumnos", alumnoRepository.count());
        resumen.put("totalEmpresas", empresaRepository.count());
        resumen.put("totalConvenios", convenioFERepository.count());
        resumen.put("conveniosActivos",
            convenioFERepository.findByEstado("activo").size());
        resumen.put("alumnosEnPracticas",
            asignacionRepository.findByEstadoAsignacion("en_practicas").size());
        resumen.put("totalEvaluaciones", evaluacionRepository.count());
        return resumen;
    }

    // ALUMNOS POR EMPRESA
    public Map<String, List<Asignacion>> alumnosPorEmpresa() {
        List<Asignacion> asignaciones = asignacionRepository.findAll();
        return asignaciones.stream()
                .collect(Collectors.groupingBy(
                    a -> a.getConvenio().getEmpresa().getNombreEmpresa()
                ));
    }

    // ESTADÍSTICAS POR CURSO
    public Map<String, Long> alumnosPorCurso() {
        return alumnoRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                    Alumno::getCurso,
                    Collectors.counting()
                ));
    }

    // EVALUACIONES EMITIDAS
    public List<Evaluacion> evaluacionesEmitidas(String tipo) {
        if (tipo != null && !tipo.isEmpty()) {
            return evaluacionRepository.findByTipo(tipo);
        }
        return evaluacionRepository.findAll();
    }

    // ESTADO GLOBAL CONVENIOS
    public Map<String, Long> conveniosPorEstado() {
        return convenioFERepository.findAll().stream()
                .collect(Collectors.groupingBy(
                    ConvenioFE::getEstado,
                    Collectors.counting()
                ));
    }

    // MEDIA DE NOTAS POR EMPRESA
    public Map<String, Double> mediaNotasPorEmpresa() {
        Map<String, Double> medias = new HashMap<>();
        List<Asignacion> asignaciones = asignacionRepository.findAll();

        asignaciones.forEach(asignacion -> {
            String empresa = asignacion.getConvenio()
                                       .getEmpresa()
                                       .getNombreEmpresa();
            List<Evaluacion> evaluaciones = evaluacionRepository
                    .findByAsignacionId(asignacion.getId());

            if (!evaluaciones.isEmpty()) {
                double media = evaluaciones.stream()
                        .mapToDouble(Evaluacion::getNotaFinal)
                        .average()
                        .orElse(0.0);
                medias.put(empresa, Math.round(media * 100.0) / 100.0);
            }
        });

        return medias;
    }

    // PROGRESO MEDIO POR ALUMNO
    public List<Map<String, Object>> progresoPorAlumno() {
        List<Map<String, Object>> resultado = new ArrayList<>();

        asignacionRepository.findAll().forEach(asignacion -> {
            Map<String, Object> datos = new HashMap<>();
            datos.put("alumno", asignacion.getAlumno().getNombre()
                    + " " + asignacion.getAlumno().getApellidos());
            datos.put("empresa", asignacion.getConvenio()
                                          .getEmpresa()
                                          .getNombreEmpresa());
            datos.put("horasRealizadas", asignacion.getHorasRealizadas());
            datos.put("horasTotales", asignacion.getConvenio().getHorasTotales());

            int porcentaje = 0;
            if (asignacion.getConvenio().getHorasTotales() > 0) {
                porcentaje = (asignacion.getHorasRealizadas() * 100)
                        / asignacion.getConvenio().getHorasTotales();
            }
            datos.put("porcentaje", porcentaje);
            datos.put("estado", asignacion.getEstadoAsignacion());
            resultado.add(datos);
        });

        return resultado;
    }
}

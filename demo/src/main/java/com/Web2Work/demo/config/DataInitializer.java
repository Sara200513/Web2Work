package com.Web2Work.demo.config;

import com.Web2Work.demo.model.*;
import com.Web2Work.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired private AlumnoRepository        alumnoRepository;
    @Autowired private ProfesorRepository      profesorRepository;
    @Autowired private EmpresaRepository       empresaRepository;
    @Autowired private AdministradorRepository administradorRepository;
    @Autowired private ConvenioFERepository    convenioFERepository;
    @Autowired private AsignacionRepository    asignacionRepository;
    @Autowired private PasswordEncoder         passwordEncoder;

    @Override
    public void run(String... args) {

        // ADMIN
    	if (administradorRepository.count() == 0) {
            Administrador admin = new Administrador();
            admin.setNombre("Sara");
            admin.setApellidos("Lorenzo Acebal");
            admin.setDni("00000000A");
            admin.setEmail("admin@web2work.es");
            admin.setPasswordHash(passwordEncoder.encode("admin123"));
            admin.setTelefono("600000000");
            admin.setRol("admin");
            admin.setCuentaActiva(true);
            admin.setNivelAcceso("total");
            admin.setDepartamento("TI");
            administradorRepository.save(admin);
        }

        // PROFESORES 
    	if (profesorRepository.count() == 0) {
            Profesor p1 = new Profesor();
            p1.setNombre("Mario"); p1.setApellidos("Fernandez Lago");
            p1.setDni("11111111A"); p1.setEmail("mario.fernandez@web2work.es");
            p1.setPasswordHash(passwordEncoder.encode("1234"));
            p1.setTelefono("611111111"); p1.setRol("profesor");
            p1.setCuentaActiva(true); p1.setDepartamento("Informatica");
            p1.setEspecialidad("Desarrollo Web");
            profesorRepository.save(p1);

            Profesor p2 = new Profesor();
            p2.setNombre("Laura"); p2.setApellidos("Jimenez Torres");
            p2.setDni("11111111B"); p2.setEmail("laura.jimenez@web2work.es");
            p2.setPasswordHash(passwordEncoder.encode("1234"));
            p2.setTelefono("611222222"); p2.setRol("profesor");
            p2.setCuentaActiva(true); p2.setDepartamento("Informatica");
            p2.setEspecialidad("Bases de Datos");
            profesorRepository.save(p2);
        }

        // EMPRESAS
    	if (empresaRepository.count() == 0) {
            Empresa e1 = new Empresa();
            e1.setNombre("Ana"); e1.setApellidos("Rodriguez");
            e1.setDni("33333333A"); e1.setEmail("contacto@techsolutions.es");
            e1.setPasswordHash(passwordEncoder.encode("empresa123"));
            e1.setTelefono("633111111"); e1.setRol("empresa");
            e1.setCuentaActiva(true); e1.setCif("B12345678");
            e1.setNombreEmpresa("Tech Solutions S.L.");
            e1.setDireccion("Gran Via 25, Madrid"); e1.setSector("Tecnologia");
            e1.setRazonSocial("Tech Solutions S.L.");
            e1.setContactoNombre("Ana Rodriguez"); e1.setContactoTelefono("633111111");
            empresaRepository.save(e1);

            Empresa e2 = new Empresa();
            e2.setNombre("Roberto"); e2.setApellidos("Perez");
            e2.setDni("33333333B"); e2.setEmail("rrhh@innovasoft.es");
            e2.setPasswordHash(passwordEncoder.encode("1234"));
            e2.setTelefono("633222222"); e2.setRol("empresa");
            e2.setCuentaActiva(true); e2.setCif("B87654321");
            e2.setNombreEmpresa("InnovaSoft");
            e2.setDireccion("Calle Serrano 18, Madrid"); e2.setSector("Software");
            e2.setRazonSocial("InnovaSoft Consulting");
            e2.setContactoNombre("Roberto Perez"); e2.setContactoTelefono("633222222");
            empresaRepository.save(e2);

            Empresa empDemo = new Empresa();
            empDemo.setNombre("Tutor"); empDemo.setApellidos("Empresarial");
            empDemo.setDni("33333333D"); empDemo.setEmail("empresa@web2work.es");
            empDemo.setPasswordHash(passwordEncoder.encode("empresa123"));
            empDemo.setTelefono("633000000"); empDemo.setRol("empresa");
            empDemo.setCuentaActiva(true); empDemo.setCif("B99999999");
            empDemo.setNombreEmpresa("Empresa Demo S.L.");
            empDemo.setDireccion("Calle Mayor 1, Madrid"); empDemo.setSector("Tecnologia");
            empDemo.setRazonSocial("Empresa Demo S.L.");
            empDemo.setContactoNombre("Tutor Empresarial"); empDemo.setContactoTelefono("633000000");
            empresaRepository.save(empDemo);
        }

        // ALUMNOS
    	if (alumnoRepository.count() == 0) {
            Alumno alumnoDemo = new Alumno();
            alumnoDemo.setNombre("Sara"); alumnoDemo.setApellidos("Lorenzo");
            alumnoDemo.setDni("22222222Z"); alumnoDemo.setEmail("alumno@web2work.es");
            alumnoDemo.setPasswordHash(passwordEncoder.encode("alumno123"));
            alumnoDemo.setTelefono("622000000"); alumnoDemo.setRol("alumno");
            alumnoDemo.setCuentaActiva(true); alumnoDemo.setCurso("2 DAW");
            alumnoDemo.setGrupo("A"); alumnoDemo.setFechaNacimiento(LocalDate.of(2000, 1, 1));
            alumnoRepository.save(alumnoDemo);

            Alumno a1 = new Alumno();
            a1.setNombre("Carlos"); a1.setApellidos("Martinez Lopez");
            a1.setDni("22222222A"); a1.setEmail("carlos.martinez@web2work.es");
            a1.setPasswordHash(passwordEncoder.encode("1234"));
            a1.setTelefono("622111111"); a1.setRol("alumno");
            a1.setCuentaActiva(true); a1.setCurso("2 DAW");
            a1.setGrupo("A"); a1.setFechaNacimiento(LocalDate.of(2003, 5, 10));
            alumnoRepository.save(a1);

            Alumno a2 = new Alumno();
            a2.setNombre("Lucia"); a2.setApellidos("Fernandez Garcia");
            a2.setDni("22222222B"); a2.setEmail("lucia.fernandez@web2work.es");
            a2.setPasswordHash(passwordEncoder.encode("1234"));
            a2.setTelefono("622222222"); a2.setRol("alumno");
            a2.setCuentaActiva(true); a2.setCurso("2 DAW");
            a2.setGrupo("A"); a2.setFechaNacimiento(LocalDate.of(2004, 3, 15));
            alumnoRepository.save(a2);

            Alumno a3 = new Alumno();
            a3.setNombre("Alejandro"); a3.setApellidos("Ruiz Perez");
            a3.setDni("22222222C"); a3.setEmail("alejandro.ruiz@web2work.es");
            a3.setPasswordHash(passwordEncoder.encode("1234"));
            a3.setTelefono("622333333"); a3.setRol("alumno");
            a3.setCuentaActiva(true); a3.setCurso("2 DAW");
            a3.setGrupo("B"); a3.setFechaNacimiento(LocalDate.of(2002, 11, 20));
            alumnoRepository.save(a3);

            Alumno a4 = new Alumno();
            a4.setNombre("Marta"); a4.setApellidos("Sanchez Diaz");
            a4.setDni("22222222D"); a4.setEmail("marta.sanchez@web2work.es");
            a4.setPasswordHash(passwordEncoder.encode("1234"));
            a4.setTelefono("622444444"); a4.setRol("alumno");
            a4.setCuentaActiva(true); a4.setCurso("2 DAW");
            a4.setGrupo("B"); a4.setFechaNacimiento(LocalDate.of(2003, 8, 2));
            alumnoRepository.save(a4);
        }

        // CONVENIOS + ASIGNACIONES 
    	if (convenioFERepository.count() == 0) {
            Empresa empresa = empresaRepository.findAll().stream()
                    .filter(e -> e.getEmail().equals("contacto@techsolutions.es"))
                    .findFirst().orElse(empresaRepository.findAll().get(0));
            Profesor profesor = profesorRepository.findAll().get(0);

            ConvenioFE c1 = new ConvenioFE();
            c1.setEmpresa(empresa); c1.setProfesor(profesor);
            c1.setTitulo("Prácticas DAW 2024-2025 - Tech Solutions");
            c1.setFechaInicio(LocalDate.of(2025, 3, 1));
            c1.setFechaFin(LocalDate.of(2025, 6, 20));
            c1.setHorasTotales(400); c1.setEstado("activo");
            convenioFERepository.save(c1);

            Empresa empresa2 = empresaRepository.findAll().stream()
                    .filter(e -> e.getEmail().equals("rrhh@innovasoft.es"))
                    .findFirst().orElse(empresaRepository.findAll().get(0));

            ConvenioFE c2 = new ConvenioFE();
            c2.setEmpresa(empresa2); c2.setProfesor(profesor);
            c2.setTitulo("Prácticas DAW 2024-2025 - InnovaSoft");
            c2.setFechaInicio(LocalDate.of(2025, 3, 1));
            c2.setFechaFin(LocalDate.of(2025, 6, 20));
            c2.setHorasTotales(400); c2.setEstado("activo");
            convenioFERepository.save(c2);

            if (asignacionRepository.count() == 0 && alumnoRepository.count() > 0) {
                java.util.List<Alumno> alumnos = alumnoRepository.findAll();

                Asignacion as0 = new Asignacion();
                as0.setAlumno(alumnos.get(0)); as0.setConvenio(c1);
                as0.setEstadoAsignacion("en_practicas"); as0.setHorasRealizadas(120);
                asignacionRepository.save(as0);

                if (alumnos.size() > 1) {
                    Asignacion as1 = new Asignacion();
                    as1.setAlumno(alumnos.get(1)); as1.setConvenio(c1);
                    as1.setEstadoAsignacion("en_practicas"); as1.setHorasRealizadas(200);
                    asignacionRepository.save(as1);
                }
                if (alumnos.size() > 2) {
                    Asignacion as2 = new Asignacion();
                    as2.setAlumno(alumnos.get(2)); as2.setConvenio(c2);
                    as2.setEstadoAsignacion("en_practicas"); as2.setHorasRealizadas(80);
                    asignacionRepository.save(as2);
                }
                if (alumnos.size() > 3) {
                    Asignacion as3 = new Asignacion();
                    as3.setAlumno(alumnos.get(3)); as3.setConvenio(c2);
                    as3.setEstadoAsignacion("asignado"); as3.setHorasRealizadas(0);
                    asignacionRepository.save(as3);
                }
            }
        }

        System.out.println("\n==================================");
        System.out.println("  DATOS DEMO CARGADOS");
        System.out.println("==================================");
        System.out.println("  ADMIN:    admin@web2work.es      / admin123");
        System.out.println("  ALUMNO:   alumno@web2work.es     / alumno123");
        System.out.println("  PROFESOR: mario.fernandez@...    / 1234");
        System.out.println("  EMPRESA:  contacto@techsolutions  / empresa123");
        System.out.println("==================================\n");
    }
}
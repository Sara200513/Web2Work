package com.Web2Work.demo.config;

import com.Web2Work.demo.model.*;
import com.Web2Work.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired private AlumnoRepository        alumnoRepository;
    @Autowired private ProfesorRepository      profesorRepository;
    @Autowired private EmpresaRepository       empresaRepository;
    @Autowired private AdministradorRepository administradorRepository;
    @Autowired private ConvenioFERepository    convenioFERepository;
    @Autowired private AsignacionRepository    asignacionRepository;
    @Autowired private ActividadRepository     actividadRepository;
    @Autowired private EvidenciaRepository     evidenciaRepository;
    @Autowired private EvaluacionRepository    evaluacionRepository;
    @Autowired private ComentarioRepository    comentarioRepository;
    @Autowired private MensajeRepository       mensajeRepository;
    @Autowired private NotificacionRepository  notificacionRepository;
    @Autowired private PasswordEncoder         passwordEncoder;

    @Override
    public void run(String... args) {

        if (administradorRepository.count() > 0) {
            System.out.println("Datos ya cargados, omitiendo inicializacion.");
            return;
        }

        // ── ADMIN ─────────────────────────────────────────────────────────────
        Administrador admin = new Administrador();
        admin.setNombre("Sara"); admin.setApellidos("Lorenzo Acebal");
        admin.setDni("00000000A"); admin.setEmail("admin@web2work.es");
        admin.setPasswordHash(passwordEncoder.encode("admin123"));
        admin.setTelefono("600000000"); admin.setRol("admin");
        admin.setCuentaActiva(true); admin.setNivelAcceso("total");
        admin.setDepartamento("TI");
        administradorRepository.save(admin);

        // ── PROFESORES ────────────────────────────────────────────────────────
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

        // ── EMPRESAS ──────────────────────────────────────────────────────────
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

        // ── ALUMNOS ───────────────────────────────────────────────────────────
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

        // ── CONVENIOS ─────────────────────────────────────────────────────────
        ConvenioFE c1 = new ConvenioFE();
        c1.setEmpresa(e1); c1.setProfesor(p1);
        c1.setTitulo("Practicas DAW 2024-2025 - Tech Solutions");
        c1.setFechaInicio(LocalDate.of(2025, 3, 1));
        c1.setFechaFin(LocalDate.of(2025, 6, 20));
        c1.setHorasTotales(400); c1.setEstado("activo");
        convenioFERepository.save(c1);

        ConvenioFE c2 = new ConvenioFE();
        c2.setEmpresa(e2); c2.setProfesor(p1);
        c2.setTitulo("Practicas DAW 2024-2025 - InnovaSoft");
        c2.setFechaInicio(LocalDate.of(2025, 3, 1));
        c2.setFechaFin(LocalDate.of(2025, 6, 20));
        c2.setHorasTotales(400); c2.setEstado("activo");
        convenioFERepository.save(c2);

        ConvenioFE c3 = new ConvenioFE();
        c3.setEmpresa(empDemo); c3.setProfesor(p2);
        c3.setTitulo("Practicas DAW 2024-2025 - Empresa Demo");
        c3.setFechaInicio(LocalDate.of(2025, 3, 1));
        c3.setFechaFin(LocalDate.of(2025, 6, 20));
        c3.setHorasTotales(400); c3.setEstado("activo");
        convenioFERepository.save(c3);

        // ── ASIGNACIONES ──────────────────────────────────────────────────────
        Asignacion as0 = new Asignacion();
        as0.setAlumno(alumnoDemo); as0.setConvenio(c1);
        as0.setEstadoAsignacion("en_practicas"); as0.setHorasRealizadas(245);
        asignacionRepository.save(as0);

        Asignacion as1 = new Asignacion();
        as1.setAlumno(a1); as1.setConvenio(c1);
        as1.setEstadoAsignacion("en_practicas"); as1.setHorasRealizadas(310);
        asignacionRepository.save(as1);

        Asignacion as2 = new Asignacion();
        as2.setAlumno(a2); as2.setConvenio(c2);
        as2.setEstadoAsignacion("en_practicas"); as2.setHorasRealizadas(180);
        asignacionRepository.save(as2);

        Asignacion as3 = new Asignacion();
        as3.setAlumno(a3); as3.setConvenio(c2);
        as3.setEstadoAsignacion("en_practicas"); as3.setHorasRealizadas(95);
        asignacionRepository.save(as3);

        Asignacion as4 = new Asignacion();
        as4.setAlumno(a4); as4.setConvenio(c3);
        as4.setEstadoAsignacion("asignado"); as4.setHorasRealizadas(0);
        asignacionRepository.save(as4);

        // ── ACTIVIDADES ───────────────────────────────────────────────────────
        String[][] actividadesData = {
            {"Diseno de base de datos relacional", "Diseño del modelo entidad-relación para el proyecto de gestion de clientes", "8", "desarrollo"},
            {"Implementacion API REST", "Desarrollo de endpoints REST con Spring Boot para la gestion de usuarios", "6", "desarrollo"},
            {"Testing unitario con JUnit", "Creacion de tests unitarios para los servicios del backend", "5", "testing"},
            {"Documentacion tecnica", "Redaccion de la documentacion de la API con Swagger", "4", "documentacion"},
            {"Reunion de seguimiento", "Reunion semanal con el equipo para revisar el progreso del sprint", "2", "reuniones"},
            {"Desarrollo frontend React", "Implementacion de componentes React para el panel de administracion", "7", "desarrollo"},
            {"Revision de codigo", "Code review de los pull requests del equipo", "3", "desarrollo"},
            {"Despliegue en servidor", "Configuracion y despliegue de la aplicacion en servidor Linux", "5", "desarrollo"},
            {"Diseño de interfaz de usuario", "Creacion de wireframes y maquetas para las nuevas funcionalidades", "4", "diseño"},
            {"Optimizacion de consultas SQL", "Mejora del rendimiento de las consultas a la base de datos", "6", "desarrollo"}
        };

        for (int i = 0; i < actividadesData.length; i++) {
            Actividad act = new Actividad();
            act.setTitulo(actividadesData[i][0]);
            act.setDescripcion(actividadesData[i][1]);
            act.setHoras(Integer.parseInt(actividadesData[i][2]));
            act.setCategoria(actividadesData[i][3]);
            act.setTipoActividad("practica");
            act.setFecha(LocalDate.of(2025, 3, 1).plusDays(i * 7));
            act.setAsignacion(i < 5 ? as0 : as1);
            actividadRepository.save(act);
        }

        // Actividades para a2
        Actividad act11 = new Actividad();
        act11.setTitulo("Analisis de requisitos");
        act11.setDescripcion("Reunion con el cliente para definir los requisitos del proyecto");
        act11.setHoras(4); act11.setCategoria("reuniones");
        act11.setTipoActividad("practica");
        act11.setFecha(LocalDate.of(2025, 3, 5));
        act11.setAsignacion(as2);
        actividadRepository.save(act11);

        Actividad act12 = new Actividad();
        act12.setTitulo("Desarrollo modulo de autenticacion");
        act12.setDescripcion("Implementacion del sistema de login y registro con JWT");
        act12.setHoras(8); act12.setCategoria("desarrollo");
        act12.setTipoActividad("practica");
        act12.setFecha(LocalDate.of(2025, 3, 12));
        act12.setAsignacion(as2);
        actividadRepository.save(act12);

        // ── EVIDENCIAS ────────────────────────────────────────────────────────
        List<Actividad> actividades = actividadRepository.findAll();
        for (int i = 0; i < Math.min(6, actividades.size()); i++) {
            Evidencia ev = new Evidencia();
            ev.setActividad(actividades.get(i));
            ev.setAsignacion(actividades.get(i).getAsignacion());
            ev.setUploadedBy(actividades.get(i).getAsignacion().getAlumno());
            ev.setNombreArchivo("evidencia_" + (i + 1) + ".pdf");
            ev.setFilePath("/uploads/evidencia_" + (i + 1) + ".pdf");
            ev.setFileType("application/pdf");
            ev.setDescripcion("Evidencia de la actividad: " + actividades.get(i).getTitulo());
            ev.setTamaño(1024L * (i + 1));
            evidenciaRepository.save(ev);
        }

        // ── EVALUACIONES ──────────────────────────────────────────────────────
        Evaluacion eval1 = new Evaluacion();
        eval1.setAsignacion(as0); eval1.setEvaluador(p1);
        eval1.setTipo("intermedio"); eval1.setNotaFinal(7.5);
        eval1.setComentarios("Buen progreso. Demuestra iniciativa y capacidad tecnica. Mejorar la documentacion.");
        evaluacionRepository.save(eval1);

        Evaluacion eval2 = new Evaluacion();
        eval2.setAsignacion(as1); eval2.setEvaluador(p1);
        eval2.setTipo("intermedio"); eval2.setNotaFinal(8.5);
        eval2.setComentarios("Excelente trabajo en equipo y muy buena actitud. Continua asi.");
        evaluacionRepository.save(eval2);

        Evaluacion eval3 = new Evaluacion();
        eval3.setAsignacion(as2); eval3.setEvaluador(p1);
        eval3.setTipo("intermedio"); eval3.setNotaFinal(6.0);
        eval3.setComentarios("Necesita mejorar la puntualidad en la entrega de tareas.");
        evaluacionRepository.save(eval3);

        // ── COMENTARIOS ───────────────────────────────────────────────────────
        Comentario com1 = new Comentario();
        com1.setUsuario(p1); com1.setTargetType("alumno");
        com1.setTargetId(alumnoDemo.getId());
        com1.setTexto("Sara demuestra gran capacidad de aprendizaje. Muy proactiva en las tareas asignadas.");
        comentarioRepository.save(com1);

        Comentario com2 = new Comentario();
        com2.setUsuario(e1); com2.setTargetType("alumno");
        com2.setTargetId(a1.getId());
        com2.setTexto("Carlos se ha integrado muy bien en el equipo. Excelente actitud y disposicion.");
        comentarioRepository.save(com2);

        Comentario com3 = new Comentario();
        com3.setUsuario(p1); com3.setTargetType("alumno");
        com3.setTargetId(a2.getId());
        com3.setTexto("Lucia necesita mejorar la gestion del tiempo. Buen nivel tecnico.");
        comentarioRepository.save(com3);

        // ── MENSAJES ─────────────────────────────────────────────────────────
        // Conversacion 1: alumnoDemo <-> profesor
        String conv1 = UUID.randomUUID().toString();
        Mensaje m1 = new Mensaje();
        m1.setFromUser(alumnoDemo); m1.setToUser(p1);
        m1.setAsunto("Duda sobre la actividad de testing");
        m1.setCuerpo("Hola Mario, tengo una duda sobre como estructurar los tests unitarios. Podrias orientarme?");
        m1.setLeido(true); m1.setConversacionId(conv1);
        mensajeRepository.save(m1);

        Mensaje m2 = new Mensaje();
        m2.setFromUser(p1); m2.setToUser(alumnoDemo);
        m2.setAsunto("Re: Duda sobre la actividad de testing");
        m2.setCuerpo("Claro Sara, lo que necesitas es crear una clase de test con la anotacion @SpringBootTest. Revisa la documentacion de JUnit 5.");
        m2.setLeido(true); m2.setConversacionId(conv1);
        mensajeRepository.save(m2);

        Mensaje m3 = new Mensaje();
        m3.setFromUser(alumnoDemo); m3.setToUser(p1);
        m3.setAsunto("Re: Duda sobre la actividad de testing");
        m3.setCuerpo("Muchas gracias! Ya lo he conseguido. He subido la evidencia al sistema.");
        m3.setLeido(false); m3.setConversacionId(conv1);
        mensajeRepository.save(m3);

        // Conversacion 2: alumnoDemo <-> empresa
        String conv2 = UUID.randomUUID().toString();
        Mensaje m4 = new Mensaje();
        m4.setFromUser(e1); m4.setToUser(alumnoDemo);
        m4.setAsunto("Reunion de seguimiento semanal");
        m4.setCuerpo("Sara, recordarte que manana tenemos reunion de seguimiento a las 10:00. Por favor confirma tu asistencia.");
        m4.setLeido(true); m4.setConversacionId(conv2);
        mensajeRepository.save(m4);

        Mensaje m5 = new Mensaje();
        m5.setFromUser(alumnoDemo); m5.setToUser(e1);
        m5.setAsunto("Re: Reunion de seguimiento semanal");
        m5.setCuerpo("Confirmado! Estare puntual. Preparare un resumen de las actividades de esta semana.");
        m5.setLeido(true); m5.setConversacionId(conv2);
        mensajeRepository.save(m5);

        // Conversacion 3: profesor <-> empresa
        String conv3 = UUID.randomUUID().toString();
        Mensaje m6 = new Mensaje();
        m6.setFromUser(p1); m6.setToUser(e1);
        m6.setAsunto("Seguimiento alumnos en practicas");
        m6.setCuerpo("Buenos dias Ana, queria consultarte como van los alumnos asignados a Tech Solutions. Hay alguna incidencia?");
        m6.setLeido(true); m6.setConversacionId(conv3);
        mensajeRepository.save(m6);

        Mensaje m7 = new Mensaje();
        m7.setFromUser(e1); m7.setToUser(p1);
        m7.setAsunto("Re: Seguimiento alumnos en practicas");
        m7.setCuerpo("Todo va muy bien Mario. Sara y Carlos se han integrado perfectamente en el equipo. Muy contentos con ellos.");
        m7.setLeido(false); m7.setConversacionId(conv3);
        mensajeRepository.save(m7);

        // Conversacion 4: a1 <-> profesor
        String conv4 = UUID.randomUUID().toString();
        Mensaje m8 = new Mensaje();
        m8.setFromUser(a1); m8.setToUser(p1);
        m8.setAsunto("Consulta sobre evaluacion intermedia");
        m8.setCuerpo("Hola Mario, cuando sera la evaluacion intermedia? Quiero asegurarme de tener todas las actividades registradas.");
        m8.setLeido(false); m8.setConversacionId(conv4);
        mensajeRepository.save(m8);

        // ── NOTIFICACIONES ────────────────────────────────────────────────────
        Notificacion n1 = new Notificacion();
        n1.setUsuario(alumnoDemo); n1.setTipo("EVALUACION");
        n1.setTexto("Mario Fernandez Lago ha registrado tu evaluacion intermedia. Nota: 7.5");
        n1.setLink("/alumnos/progreso"); n1.setLeido(false);
        notificacionRepository.save(n1);

        Notificacion n2 = new Notificacion();
        n2.setUsuario(alumnoDemo); n2.setTipo("COMENTARIO");
        n2.setTexto("Mario Fernandez Lago ha dejado una observacion sobre ti.");
        n2.setLink("/alumnos/progreso"); n2.setLeido(false);
        notificacionRepository.save(n2);

        Notificacion n3 = new Notificacion();
        n3.setUsuario(alumnoDemo); n3.setTipo("MENSAJE");
        n3.setTexto("Ana Rodriguez te ha enviado un mensaje: Reunion de seguimiento semanal");
        n3.setLink("/mensajes/conversacion/" + conv2); n3.setLeido(true);
        notificacionRepository.save(n3);

        Notificacion n4 = new Notificacion();
        n4.setUsuario(p1); n4.setTipo("ACTIVIDAD");
        n4.setTexto("Sara Lorenzo ha registrado una nueva actividad: Optimizacion de consultas SQL");
        n4.setLink("/alumnos/" + alumnoDemo.getId()); n4.setLeido(false);
        notificacionRepository.save(n4);

        Notificacion n5 = new Notificacion();
        n5.setUsuario(p1); n5.setTipo("MENSAJE");
        n5.setTexto("Sara Lorenzo ha respondido a: Duda sobre la actividad de testing");
        n5.setLink("/mensajes/conversacion/" + conv1); n5.setLeido(false);
        notificacionRepository.save(n5);

        Notificacion n6 = new Notificacion();
        n6.setUsuario(e1); n6.setTipo("ACTIVIDAD");
        n6.setTexto("Carlos Martinez Lopez ha registrado una nueva actividad: Revision de codigo");
        n6.setLink("/empresas/alumnos/" + a1.getId()); n6.setLeido(false);
        notificacionRepository.save(n6);

        System.out.println("\n==================================");
        System.out.println("  DATOS DEMO CARGADOS");
        System.out.println("==================================");
        System.out.println("  ADMIN:    admin@web2work.es     / admin123");
        System.out.println("  ALUMNO:   alumno@web2work.es    / alumno123");
        System.out.println("  PROFESOR: mario.fernandez@...   / 1234");
        System.out.println("  EMPRESA:  contacto@techsolutions / empresa123");
        System.out.println("==================================\n");
    }
}
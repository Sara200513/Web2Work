package com.Web2Work.demo.config;

import com.Web2Work.demo.model.Administrador;
import com.Web2Work.demo.model.Alumno;
import com.Web2Work.demo.model.Empresa;
import com.Web2Work.demo.model.Profesor;
import com.Web2Work.demo.repository.AdministradorRepository;
import com.Web2Work.demo.repository.AlumnoRepository;
import com.Web2Work.demo.repository.EmpresaRepository;
import com.Web2Work.demo.repository.ProfesorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // =========================
        // ALUMNOS
        // =========================

        if (alumnoRepository.count() == 0) {
        	 Alumno alumno = new Alumno();
             alumno.setNombre("Sara");
             alumno.setApellidos("Lorenzo");
             alumno.setDni("22222222C");
             alumno.setEmail("alumno@web2work.es");
             alumno.setPasswordHash(passwordEncoder.encode("alumno123"));
             alumno.setTelefono("622222222");
             alumno.setRol("alumno");
             alumno.setCuentaActiva(true);
             alumno.setCurso("2 DAW");
             alumno.setGrupo("A");
             alumno.setFechaNacimiento(LocalDate.of(2000, 1, 1));
             alumnoRepository.save(alumno);

            Alumno a1 = new Alumno();
            a1.setNombre("Carlos");
            a1.setApellidos("Martinez Lopez");
            a1.setDni("22222222A");
            a1.setEmail("carlos.martinez@web2work.es");
            a1.setPasswordHash(passwordEncoder.encode("1234"));
            a1.setTelefono("622111111");
            a1.setRol("alumno");
            a1.setCuentaActiva(true);
            a1.setCurso("2 DAW");
            a1.setGrupo("A");
            a1.setFechaNacimiento(LocalDate.of(2003, 5, 10));
            alumnoRepository.save(a1);

            Alumno a2 = new Alumno();
            a2.setNombre("Lucia");
            a2.setApellidos("Fernandez Garcia");
            a2.setDni("22222222B");
            a2.setEmail("lucia.fernandez@web2work.es");
            a2.setPasswordHash(passwordEncoder.encode("1234"));
            a2.setTelefono("622222222");
            a2.setRol("alumno");
            a2.setCuentaActiva(true);
            a2.setCurso("2 DAW");
            a2.setGrupo("A");
            a2.setFechaNacimiento(LocalDate.of(2004, 3, 15));
            alumnoRepository.save(a2);

            Alumno a3 = new Alumno();
            a3.setNombre("Alejandro");
            a3.setApellidos("Ruiz Perez");
            a3.setDni("22222222C");
            a3.setEmail("alejandro.ruiz@web2work.es");
            a3.setPasswordHash(passwordEncoder.encode("1234"));
            a3.setTelefono("622333333");
            a3.setRol("alumno");
            a3.setCuentaActiva(true);
            a3.setCurso("2 DAW");
            a3.setGrupo("B");
            a3.setFechaNacimiento(LocalDate.of(2002, 11, 20));
            alumnoRepository.save(a3);

            Alumno a4 = new Alumno();
            a4.setNombre("Marta");
            a4.setApellidos("Sanchez Diaz");
            a4.setDni("22222222D");
            a4.setEmail("marta.sanchez@web2work.es");
            a4.setPasswordHash(passwordEncoder.encode("1234"));
            a4.setTelefono("622444444");
            a4.setRol("alumno");
            a4.setCuentaActiva(true);
            a4.setCurso("2 DAW");
            a4.setGrupo("B");
            a4.setFechaNacimiento(LocalDate.of(2003, 8, 2));
            alumnoRepository.save(a4);

            Alumno a5 = new Alumno();
            a5.setNombre("David");
            a5.setApellidos("Gomez Alvarez");
            a5.setDni("22222222E");
            a5.setEmail("david.gomez@web2work.es");
            a5.setPasswordHash(passwordEncoder.encode("1234"));
            a5.setTelefono("622555555");
            a5.setRol("alumno");
            a5.setCuentaActiva(true);
            a5.setCurso("2 DAW");
            a5.setGrupo("A");
            a5.setFechaNacimiento(LocalDate.of(2004, 1, 5));
            alumnoRepository.save(a5);

            System.out.println("Alumnos demo creados");
        }

        // =========================
        // PROFESORES
        // =========================

        if (profesorRepository.count() == 0) {

            Profesor p1 = new Profesor();
            p1.setNombre("Mario");
            p1.setApellidos("Fernandez Lago");
            p1.setDni("11111111A");
            p1.setEmail("mario.fernandez@web2work.es");
            p1.setPasswordHash(passwordEncoder.encode("1234"));
            p1.setTelefono("611111111");
            p1.setRol("profesor");
            p1.setCuentaActiva(true);
            p1.setDepartamento("Informatica");
            p1.setEspecialidad("Desarrollo Web");
            profesorRepository.save(p1);

            Profesor p2 = new Profesor();
            p2.setNombre("Laura");
            p2.setApellidos("Jimenez Torres");
            p2.setDni("11111111B");
            p2.setEmail("laura.jimenez@web2work.es");
            p2.setPasswordHash(passwordEncoder.encode("1234"));
            p2.setTelefono("611222222");
            p2.setRol("profesor");
            p2.setCuentaActiva(true);
            p2.setDepartamento("Informatica");
            p2.setEspecialidad("Bases de Datos");
            profesorRepository.save(p2);

            Profesor p3 = new Profesor();
            p3.setNombre("Javier");
            p3.setApellidos("Morales Ruiz");
            p3.setDni("11111111C");
            p3.setEmail("javier.morales@web2work.es");
            p3.setPasswordHash(passwordEncoder.encode("1234"));
            p3.setTelefono("611333333");
            p3.setRol("profesor");
            p3.setCuentaActiva(true);
            p3.setDepartamento("Informatica");
            p3.setEspecialidad("Java");
            profesorRepository.save(p3);

            System.out.println("Profesores demo creados");
        }

        // =========================
        // EMPRESAS
        // =========================

        if (empresaRepository.count() == 0) {

            Empresa e1 = new Empresa();
            e1.setNombre("Ana");
            e1.setApellidos("Rodriguez");
            e1.setDni("33333333A");
            e1.setEmail("contacto@techsolutions.es");
            e1.setPasswordHash(passwordEncoder.encode("1234"));
            e1.setTelefono("633111111");
            e1.setRol("empresa");
            e1.setCuentaActiva(true);
            e1.setCif("B12345678");
            e1.setNombreEmpresa("Tech Solutions S.L.");
            e1.setDireccion("Gran Via 25, Madrid");
            e1.setSector("Tecnologia");
            e1.setRazonSocial("Tech Solutions S.L.");
            e1.setContactoNombre("Ana Rodriguez");
            e1.setContactoTelefono("633111111");
            empresaRepository.save(e1);

            Empresa e2 = new Empresa();
            e2.setNombre("Roberto");
            e2.setApellidos("Perez");
            e2.setDni("33333333B");
            e2.setEmail("rrhh@innovasoft.es");
            e2.setPasswordHash(passwordEncoder.encode("1234"));
            e2.setTelefono("633222222");
            e2.setRol("empresa");
            e2.setCuentaActiva(true);
            e2.setCif("B87654321");
            e2.setNombreEmpresa("InnovaSoft");
            e2.setDireccion("Calle Serrano 18, Madrid");
            e2.setSector("Software");
            e2.setRazonSocial("InnovaSoft Consulting");
            e2.setContactoNombre("Roberto Perez");
            e2.setContactoTelefono("633222222");
            empresaRepository.save(e2);

            Empresa e3 = new Empresa();
            e3.setNombre("Elena");
            e3.setApellidos("Garcia");
            e3.setDni("33333333C");
            e3.setEmail("info@digitalfactory.es");
            e3.setPasswordHash(passwordEncoder.encode("1234"));
            e3.setTelefono("633333333");
            e3.setRol("empresa");
            e3.setCuentaActiva(true);
            e3.setCif("B11223344");
            e3.setNombreEmpresa("Digital Factory");
            e3.setDireccion("Avenida Europa 12, Barcelona");
            e3.setSector("Marketing Digital");
            e3.setRazonSocial("Digital Factory Group");
            e3.setContactoNombre("Elena Garcia");
            e3.setContactoTelefono("633333333");
            empresaRepository.save(e3);
            
            
            
                Empresa empresa = new Empresa();
                empresa.setNombre("Tutor");
                empresa.setApellidos("Empresarial");
                empresa.setDni("33333333D");
                empresa.setEmail("empresa@web2work.es");
                empresa.setPasswordHash(passwordEncoder.encode("empresa123"));
                empresa.setTelefono("633333333");
                empresa.setRol("empresa");
                empresa.setCuentaActiva(true);
                empresa.setCif("B12345678");
                empresa.setNombreEmpresa("Empresa Demo S.L.");
                empresa.setDireccion("Calle Mayor 1, Madrid");
                empresa.setSector("Tecnologia");
                empresa.setRazonSocial("Empresa Demo S.L.");
                empresa.setContactoNombre("Tutor Empresarial");
                empresa.setContactoTelefono("633333333");
                empresaRepository.save(empresa);

                
            
            System.out.println("Empresas demo creadas");
        }

        // =========================
        // ADMIN
        // =========================

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

            System.out.println("Administrador creado");
        }

        System.out.println("==================================");
        System.out.println("DATOS DEMO CARGADOS");
        System.out.println("==================================");
        System.out.println("ADMIN:");
        System.out.println("admin@web2work.es / admin123");
        System.out.println();
        System.out.println("ALUMNO:");
        System.out.println("carlos.martinez@web2work.es / 1234");
        System.out.println();
        System.out.println("PROFESOR:");
        System.out.println("mario.fernandez@web2work.es / 1234");
        System.out.println();
        System.out.println("EMPRESA:");
        System.out.println("contacto@techsolutions.es / 1234");
        System.out.println("==================================");
    }
}
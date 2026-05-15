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
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AdministradorRepository administradorRepository;

    @Override
    public void run(String... args) throws Exception {
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

            System.out.println("Usuario alumno creado: alumno@web2work.es / alumno123");
        }
        
     // CREAR PROFESOR
        if (profesorRepository.count() == 0) {
            Profesor profesor = new Profesor();
            profesor.setNombre("Mario");
            profesor.setApellidos("Fernandez Lago");
            profesor.setDni("11111111B");
            profesor.setEmail("profesor@web2work.es");
            profesor.setPasswordHash(passwordEncoder.encode("profesor123"));
            profesor.setTelefono("611111111");
            profesor.setRol("profesor");
            profesor.setCuentaActiva(true);
            profesor.setDepartamento("Informatica");
            profesor.setEspecialidad("Desarrollo Web");
            profesorRepository.save(profesor);

            System.out.println("Usuario profesor creado: profesor@web2work.es / profesor123");
        }

        // CREAR EMPRESA
        if (empresaRepository.count() == 0) {
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

            System.out.println("Usuario empresa creado: empresa@web2work.es / empresa123");
        }
        
     // CREAR ADMINISTRADOR
        if (administradorRepository.count() == 0) {
            Administrador admin = new Administrador();
            admin.setNombre("Admin");
            admin.setApellidos("Sistema");
            admin.setDni("00000000A");
            admin.setEmail("admin@web2work.es");
            admin.setPasswordHash(passwordEncoder.encode("admin123"));
            admin.setTelefono("600000000");
            admin.setRol("admin");
            admin.setCuentaActiva(true);
            admin.setNivelAcceso("total");
            admin.setDepartamento("TI");
            administradorRepository.save(admin);

            System.out.println("Usuario admin creado: admin@web2work.es / admin123");
        }
    }
}

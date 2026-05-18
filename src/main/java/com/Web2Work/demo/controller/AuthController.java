package com.Web2Work.demo.controller;

import com.Web2Work.demo.model.*;
import com.Web2Work.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenRecuperacionService tokenRecuperacionService;

    @Autowired
    private EmailService emailService;

    // LOGIN
    @GetMapping("/login")
    public String loginForm() {
        return "auth/login";
    }

    // REGISTRO GET
    @GetMapping("/registro")
    public String registroForm() {
        return "auth/registro";
    }

    // REGISTRO POST
    @PostMapping("/registro")
    public String registro(@RequestParam String rol,
                           @RequestParam String password,
                           @RequestParam String confirmarPassword,
                           @ModelAttribute Alumno alumno,
                           @ModelAttribute Profesor profesor,
                           @ModelAttribute Empresa empresa,
                           Model model) {

        if (!password.equals(confirmarPassword)) {
            model.addAttribute("mensajeError", "Las contraseñas no coinciden.");
            return "auth/registro";
        }

        String emailUsuario = rol.equals("alumno") ? alumno.getEmail() :
                              rol.equals("profesor") ? profesor.getEmail() :
                              empresa.getEmail();

        String nombreUsuario = rol.equals("alumno") ? alumno.getNombre() :
                               rol.equals("profesor") ? profesor.getNombre() :
                               empresa.getNombre();

        String dniUsuario = rol.equals("alumno") ? alumno.getDni() :
                            rol.equals("profesor") ? profesor.getDni() :
                            empresa.getDni();

        if (usuarioService.existsByEmail(emailUsuario)) {
            model.addAttribute("mensajeError",
                "El correo electrónico ya está registrado.");
            return "auth/registro";
        }

        if (usuarioService.existsByDni(dniUsuario)) {
            model.addAttribute("mensajeError", "El DNI ya está registrado.");
            return "auth/registro";
        }

        Usuario usuarioGuardado = null;

        switch (rol) {
            case "alumno":
                alumno.setRol("alumno");
                alumno.setCuentaActiva(false);
                alumno.setPasswordHash(passwordEncoder.encode(password));
                usuarioGuardado = alumnoService.save(alumno);
                break;
            case "profesor":
                profesor.setRol("profesor");
                profesor.setCuentaActiva(false);
                profesor.setPasswordHash(passwordEncoder.encode(password));
                usuarioGuardado = profesorService.save(profesor);
                break;
            case "empresa":
                empresa.setRol("empresa");
                empresa.setCuentaActiva(false);
                empresa.setPasswordHash(passwordEncoder.encode(password));
                usuarioGuardado = empresaService.save(empresa);
                break;
            default:
                model.addAttribute("mensajeError", "Rol no válido.");
                return "auth/registro";
        }

        TokenRecuperacion token = tokenRecuperacionService
                .crearTokenVerificacion(usuarioGuardado);
        emailService.enviarEmailVerificacion(
                emailUsuario, nombreUsuario, token.getToken());

        model.addAttribute("mensajeExito",
            "Cuenta creada. Revisa tu correo para activarla.");
        return "auth/registro";
    }

    // VERIFICAR CUENTA
    @GetMapping("/verificar")
    public String verificarCuenta(@RequestParam String token, Model model) {
        tokenRecuperacionService.findByToken(token).ifPresentOrElse(
            t -> {
                if (tokenRecuperacionService.isValido(t)
                        && t.getTipo().equals("verificacion")) {
                    Usuario usuario = t.getUsuario();
                    usuario.setCuentaActiva(true);
                    usuarioService.save(usuario);
                    tokenRecuperacionService.marcarComoUsado(t);
                    emailService.enviarEmailBienvenida(
                            usuario.getEmail(), usuario.getNombre());
                    model.addAttribute("mensajeExito",
                        "Cuenta activada correctamente. Ya puedes iniciar sesión.");
                } else {
                    model.addAttribute("mensajeError",
                        "El enlace ha expirado o no es válido.");
                }
            },
            () -> model.addAttribute("mensajeError", "Token no válido.")
        );
        return "auth/verificacion";
    }

    // RECUPERAR CONTRASEÑA GET
    @GetMapping("/recuperar")
    public String recuperarForm() {
        return "auth/recuperar";
    }

    // RECUPERAR CONTRASEÑA POST
    @PostMapping("/recuperar")
    public String recuperar(@RequestParam String email, Model model) {
        usuarioService.findByEmail(email).ifPresent(usuario -> {
            TokenRecuperacion token = tokenRecuperacionService.crearToken(usuario);
            emailService.enviarEmailRecuperacion(
                    usuario.getEmail(), usuario.getNombre(), token.getToken());
        });

        model.addAttribute("mensajeExito",
            "Si el correo existe, recibirás un enlace de recuperación.");
        return "auth/recuperar";
    }

    // NUEVA CONTRASEÑA GET
    @GetMapping("/recuperar/nueva-password")
    public String nuevaPasswordForm(@RequestParam String token, Model model) {
        tokenRecuperacionService.findByToken(token).ifPresentOrElse(
            t -> {
                if (tokenRecuperacionService.isValido(t)) {
                    model.addAttribute("token", token);
                } else {
                    model.addAttribute("mensajeError", "El enlace ha expirado.");
                }
            },
            () -> model.addAttribute("mensajeError", "Token no válido.")
        );
        return "auth/nueva-password";
    }

    // NUEVA CONTRASEÑA POST
    @PostMapping("/recuperar/nueva-password")
    public String nuevaPassword(@RequestParam String token,
                                @RequestParam String password,
                                @RequestParam String confirmarPassword,
                                Model model) {
        if (!password.equals(confirmarPassword)) {
            model.addAttribute("mensajeError", "Las contraseñas no coinciden.");
            model.addAttribute("token", token);
            return "auth/nueva-password";
        }

        tokenRecuperacionService.findByToken(token).ifPresent(t -> {
            if (tokenRecuperacionService.isValido(t)) {
                Usuario usuario = t.getUsuario();
                usuario.setPasswordHash(passwordEncoder.encode(password));
                usuarioService.save(usuario);
                tokenRecuperacionService.marcarComoUsado(t);
            }
        });

        return "redirect:/login?passwordCambiada=true";
    }

    // EDITAR PERFIL GET
    @GetMapping("/perfil")
    public String perfilForm(Model model,
                             org.springframework.security.core.Authentication auth) {
        usuarioService.findByEmail(auth.getName())
                      .ifPresent(u -> model.addAttribute("usuario", u));
        return "usuarios/perfil";
    }

    // EDITAR PERFIL POST
    @PostMapping("/perfil")
    public String perfilGuardar(
                    @ModelAttribute Usuario usuario,
                    @RequestParam(required = false) String passwordActual,
                    @RequestParam(required = false) String passwordNueva,
                    org.springframework.security.core.Authentication auth,
                    Model model) {
        usuarioService.findByEmail(auth.getName()).ifPresent(u -> {
            u.setNombre(usuario.getNombre());
            u.setApellidos(usuario.getApellidos());
            u.setTelefono(usuario.getTelefono());

            if (passwordActual != null && !passwordActual.isEmpty() &&
                passwordNueva != null && !passwordNueva.isEmpty()) {
                if (passwordEncoder.matches(passwordActual, u.getPasswordHash())) {
                    u.setPasswordHash(passwordEncoder.encode(passwordNueva));
                } else {
                    model.addAttribute("mensajeError",
                        "La contraseña actual no es correcta.");
                }
            }
            usuarioService.save(u);
        });

        model.addAttribute("mensajeExito", "Perfil actualizado correctamente.");
        return "usuarios/perfil";
    }
}
package com.Web2Work.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }

        if (authentication.getAuthorities().contains(
                new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/admin/dashboard";
        }
        if (authentication.getAuthorities().contains(
                new SimpleGrantedAuthority("ROLE_PROFESOR"))) {
            return "redirect:/profesores/dashboard";
        }
        if (authentication.getAuthorities().contains(
                new SimpleGrantedAuthority("ROLE_EMPRESA"))) {
            return "redirect:/empresas/dashboard";
        }
        if (authentication.getAuthorities().contains(
                new SimpleGrantedAuthority("ROLE_ALUMNO"))) {
            return "redirect:/alumnos/dashboard";
        }

        return "redirect:/login";
    }
}


package com.driagon.springboot.form.app.controllers;

import com.driagon.springboot.form.app.editors.NombreMayusculaEditor;
import com.driagon.springboot.form.app.editors.PaisPropertyEditor;
import com.driagon.springboot.form.app.editors.RolesEditor;
import com.driagon.springboot.form.app.models.domain.Pais;
import com.driagon.springboot.form.app.models.domain.Role;
import com.driagon.springboot.form.app.models.domain.Usuario;
import com.driagon.springboot.form.app.services.PaisService;
import com.driagon.springboot.form.app.services.RoleService;
import com.driagon.springboot.form.app.validation.UsuarioValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@SessionAttributes("usuario")
public class FormController {

    @Autowired
    private UsuarioValidador validador;

    @Autowired
    private PaisService paisService;

    @Autowired
    private PaisPropertyEditor paisEditor;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolesEditor rolesEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(this.validador);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
        binder.registerCustomEditor(Pais.class, "pais", paisEditor);
        binder.registerCustomEditor(Role.class, "roles", rolesEditor);
    }

    @GetMapping("/form")
    public String form(Model model) {
        Usuario usuario = new Usuario();
        usuario.setIdentificador("12.456.789-K");
        usuario.setNombre("John");
        usuario.setApellido("Doe");
        usuario.setHabilitar(true);
        usuario.setValorSecreto("Algún valor secreto ****");
        usuario.setPais(new Pais(5, "MX", "México"));
        usuario.setRoles(Arrays.asList(new Role(2, "Usuario", "ROLE_USER"), new Role(3, "Moderador", "ROLE_MODERATOR")));

        model.addAttribute("titulo", "Formulario Usuarios");
        model.addAttribute("usuario", usuario);

        return "form";
    }

    @PostMapping("/form")
    public String procesar(@Valid Usuario usuario, BindingResult result, Model model) {

        // validador.validate(usuario, result);

        if (result.hasErrors()) {
            /*Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
            });
            model.addAttribute("error", errores);*/
            model.addAttribute("titulo", "Resultado form");
            return "form";
        }

        return "redirect:/ver";
    }

    @GetMapping("ver")
    public String ver(@SessionAttribute(name = "usuario", required = false) Usuario usuario, Model model, SessionStatus status) {
        if (usuario == null) {
            return "redirect:/form";
        }

        model.addAttribute("titulo", "Resultado form");
        status.setComplete();
        return "resultado";
    }

    @ModelAttribute("paises")
    public List<String> paises() {
        return Arrays.asList("Costa Rica", "Estados Unidos de América", "España", "Chile", "México", "Argentina", "Perú", "Colombia", "Venezuela");
    }

    @ModelAttribute("paisesMap")
    public Map<String, String> paisesMap() {
        Map<String, String> paises = new HashMap<>();
        paises.put("ES", "España");
        paises.put("CL", "Chile");
        paises.put("AR", "Argentina");
        paises.put("PE", "Perú");
        paises.put("CO", "Colombia");
        paises.put("MX", "México");
        paises.put("VE", "Venezuela");
        paises.put("EU", "Estados Unidos de América");
        paises.put("CR", "Costa Rica");
        return paises;
    }

    @ModelAttribute("listaPaises")
    public List<Pais> listaPaises() {
        return this.paisService.listar();
    }

    @ModelAttribute("listaRolesString")
    public List<String> listaRolesString() {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");
        roles.add("ROLE_MODERATOR");

        return roles;
    }

    @ModelAttribute("listaRolesMap")
    public Map<String, String> listaRolesMap() {
        Map<String, String> roles = new HashMap<>();
        roles.put("ROLE_ADMIN", "Administrador");
        roles.put("ROLE_USER", "Usuario");
        roles.put("ROLE_MODERATOR", "Moderador");
        return roles;
    }

    @ModelAttribute("listaRoles")
    public List<Role> listaRoles() {
        return this.roleService.listar();
    }

    @ModelAttribute("genero")
    public List<String> genero() {
        return Arrays.asList("Hombre", "Mujer");
    }
}
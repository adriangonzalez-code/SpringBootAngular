package com.driagon.springboot.app.controllers;

import com.driagon.springboot.app.services.IServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    @Qualifier("miServicioSimple")
    private IServicio servicio;

    /*@Autowired
    public IndexController(IServicio servicio) {
        this.servicio = servicio;
    }*/

    @GetMapping({"/", "", "/index"})
    public String index(Model model) {

        model.addAttribute("objeto", servicio.operacion());
        return "index";
    }

    /*@Autowired
    public void setServicio(IServicio servicio) {
        this.servicio = servicio;
    }*/
}
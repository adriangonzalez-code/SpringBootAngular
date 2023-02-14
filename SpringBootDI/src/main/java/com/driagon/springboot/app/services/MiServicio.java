package com.driagon.springboot.app.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Primary
//@Component("miServicioPrincipal")
public class MiServicio implements IServicio {

    @Override
    public String operacion() {
        return "Ejecutando algún proceso importante simple...";
    }
}
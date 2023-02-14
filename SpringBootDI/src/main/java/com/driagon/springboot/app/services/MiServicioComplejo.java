package com.driagon.springboot.app.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component("miServicioComplejo")
public class MiServicioComplejo implements IServicio {

    @Override
    public String operacion() {
        return "Ejecutando algún proceso importante complejo...";
    }
}
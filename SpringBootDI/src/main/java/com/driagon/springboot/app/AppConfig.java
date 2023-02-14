package com.driagon.springboot.app;

import com.driagon.springboot.app.models.domain.ItemFactura;
import com.driagon.springboot.app.models.domain.Producto;
import com.driagon.springboot.app.services.IServicio;
import com.driagon.springboot.app.services.MiServicio;
import com.driagon.springboot.app.services.MiServicioComplejo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean("miServicioSimple")
    public IServicio registrarMiServicio() {
        return new MiServicio();
    }

    @Primary
    @Bean("miServicioComplejo")
    public IServicio registrarMiServicioComplejo() {
        return new MiServicioComplejo();
    }

    @Primary
    @Bean("itemsFactura")
    public List<ItemFactura> registrarItems() {
        Producto producto1 = new Producto("Camara Sony", 100);
        Producto producto2 = new Producto("Bicicleta Bianchi", 200);

        ItemFactura linea1 = new ItemFactura(producto1, 2);
        ItemFactura linea2 = new ItemFactura(producto2, 4);

        return Arrays.asList(linea1, linea2);
    }

    @Bean("itemsFacturaOficina")
    public List<ItemFactura> registrarItemsOficina() {
        Producto producto1 = new Producto("Monitor LG LCD 24", 250);
        Producto producto2 = new Producto("Notebook Asus", 500);
        Producto producto3 = new Producto("Impresora multifuncional", 80);
        Producto producto4 = new Producto("Escritorio Oficina", 300);

        ItemFactura linea1 = new ItemFactura(producto1, 2);
        ItemFactura linea2 = new ItemFactura(producto2, 1);
        ItemFactura linea3 = new ItemFactura(producto3, 1);
        ItemFactura linea4 = new ItemFactura(producto4, 3);

        return Arrays.asList(linea1, linea2, linea3, linea4);
    }
}
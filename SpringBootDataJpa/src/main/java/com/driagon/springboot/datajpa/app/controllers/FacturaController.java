package com.driagon.springboot.datajpa.app.controllers;

import com.driagon.springboot.datajpa.app.models.entity.Cliente;
import com.driagon.springboot.datajpa.app.models.entity.Factura;
import com.driagon.springboot.datajpa.app.models.entity.ItemFactura;
import com.driagon.springboot.datajpa.app.models.entity.Producto;
import com.driagon.springboot.datajpa.app.models.service.IClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

    private final Logger log = LoggerFactory.getLogger(FacturaController.class);

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/form/{cliente_id}")
    public String crear(@PathVariable("cliente_id") Long clienteId, Map<String, Object> model, RedirectAttributes flash, Locale locale) {

        Cliente cliente = this.clienteService.findOne(clienteId);

        if (cliente == null) {
            flash.addFlashAttribute("error", messageSource.getMessage("text.not-found.cliente.error", null, locale));
            return "redirect:/listar";
        }

        Factura factura = new Factura();
        factura.setCliente(cliente);

        model.put("factura", factura);
        model.put("titulo", messageSource.getMessage("text.facura.crear.titulo", null, locale));

        return "factura/form";
    }

    @GetMapping(value = "/cargar-productos/{term}", produces = {"application/json"})
    public @ResponseBody List<Producto> cargarProductos(@PathVariable String term) {
        return this.clienteService.findByNombre(term);
    }

    @PostMapping("/form")
    public String guardar(@Valid Factura factura, BindingResult result, Model model, @RequestParam(name = "item_id[]", required = false) Long[] itemId, @RequestParam(name = "cantidad[]", required = false) Integer[] cantidad, RedirectAttributes flash, SessionStatus status, Locale locale) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", messageSource.getMessage("text.facura.crear.titulo", null, locale));
            return "factura/form";
        }

        if (itemId == null || itemId.length == 0) {
            model.addAttribute("error", messageSource.getMessage("text.factura.sin-lineas.error", null, locale));
            model.addAttribute("titulo", messageSource.getMessage("text.facura.crear.titulo", null, locale));
            return "factura/form";
        }

        for (int i = 0; i < itemId.length; ++i) {
            Producto producto = this.clienteService.findProductoById(itemId[i]);

            ItemFactura linea = new ItemFactura();
            linea.setCantidad(cantidad[i]);
            linea.setProducto(producto);
            factura.addItemFactura(linea);

            log.info("ID: {}, cantidad: {}", itemId[i], cantidad[i]);
        }

        this.clienteService.saveFactura(factura);
        status.setComplete();
        flash.addFlashAttribute("success", messageSource.getMessage("text.factura.creada.success", null, locale));

        return "redirect:/ver/" + factura.getCliente().getId();
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash, Locale locale) {
        // Factura factura = this.clienteService.findFacturaById(id);
        Factura factura = this.clienteService.fetchFacturaByIdWithClienteWithItemFacturaWithProducto(id);

        if (factura == null) {
            flash.addFlashAttribute("error", messageSource.getMessage("text.factura.not-found.error", null, locale));
            return "redirect:/listar";
        }

        model.addAttribute("factura", factura);
        model.addAttribute("titulo", messageSource.getMessage("text.factura", null, locale).concat(" ").concat(factura.getDescripcion()));

        return "factura/ver";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash, Locale locale) {

        Factura factura = this.clienteService.findFacturaById(id);

        if (factura != null) {
            this.clienteService.deleteFactura(id);
            flash.addFlashAttribute("success", messageSource.getMessage("text.factura.eliminada.success", null, locale));
            return "redirect:/ver/" + factura.getCliente().getId();
        }

        flash.addFlashAttribute("error", messageSource.getMessage("text.factura.not-found.error", null, locale));
        return "redirect:/listar";
    }
}
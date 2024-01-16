package org.iesvdm.controller;

import org.iesvdm.domain.Comercial;
import org.iesvdm.service.ComercialService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class ComercialController {

    private ComercialService comercialService;

    // Inyeccion por constructor, se omite @Autowired
    public ComercialController(ComercialService comercialService){

        this.comercialService = comercialService;
    }

    @GetMapping("/comerciales")
    public String listar(Model model){

        List<Comercial> listaComerciales = comercialService.listAll();
        model.addAttribute("listaComerciales", listaComerciales);

        return "comerciales";
    }
}

package org.iesvdm.controller;

import org.iesvdm.domain.Cliente;
import org.iesvdm.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    public IndexController() {};

    @GetMapping("/index")
    public String inicio(Model model) {

        return "index";
    }

    @GetMapping("/")
    public String localHost(Model model) {

        return "index";
    }
}

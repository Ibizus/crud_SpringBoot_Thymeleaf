package org.iesvdm.controller;

import jakarta.validation.Valid;
import org.iesvdm.dao.PedidoDAOImpl;
import org.iesvdm.domain.Comercial;
import org.iesvdm.domain.Pedido;
import org.iesvdm.dto.ClientBillingDTO;
import org.iesvdm.dto.PedidoDetailsDTO;
import org.iesvdm.service.ComercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ComercialController {

    @Autowired
    private PedidoDAOImpl pedidoDAO;
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

    @GetMapping("/comerciales/{id}")
    public String detalle(Model model, @PathVariable Integer id ) {

        Comercial comercial = comercialService.one(id);
        List<Pedido> pedidosComercial = comercialService.pedidosComercial(id);
        PedidoDetailsDTO detallesPedidos = comercialService.infoPedidosComercial(id);
        List<ClientBillingDTO> ListaFacturacionClientesDTO = pedidoDAO.facturacionClientesPorIdComercial(id).get();

        model.addAttribute("comercial", comercial);
        model.addAttribute("listaPedidos", pedidosComercial);
        model.addAttribute("detallesPedidos", detallesPedidos);
        model.addAttribute("listaFacturacionClientesDTO", ListaFacturacionClientesDTO);

        return "detalleComercial";
    }

    @GetMapping("/comerciales/crear")
    public String crear(Model model) {

        Comercial comercial = new Comercial();
        model.addAttribute("comercial", comercial);

        return "crearComercial";
    }

    @PostMapping("/comerciales/crear")
    public String submitCrear(@Valid @ModelAttribute("comercial") Comercial comercial, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){

            model.addAttribute("comercial", comercial);
            return "crearComercial";
        }

        comercialService.newComercial(comercial);
        return "redirect:/comerciales?new="+comercial.getId();
    }

    @GetMapping("/comerciales/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);

        return "editarComercial";
    }

    @PostMapping("/comerciales/editar/{id}")
    public String submitEditar(@Valid @ModelAttribute("comercial") Comercial comercial, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){

            model.addAttribute("comercial", comercial);
            return "crearComercial";
        }

        comercialService.replaceComercial(comercial);
        return "redirect:/comerciales?new="+comercial.getId();
    }

    @PostMapping("/comerciales/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {

        comercialService.deleteComercial(id);

        return new RedirectView("/comerciales");
    }


}

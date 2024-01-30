package org.iesvdm.controller;

import jakarta.validation.Valid;
import org.iesvdm.domain.Pedido;
import org.iesvdm.service.PedidoService;
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
public class PedidoController {

    private PedidoService pedidoService;
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/pedidos")
    public String listar(Model model) {

        List<Pedido> listaPedidos =  pedidoService.listAll();
        model.addAttribute("listaPedidos", listaPedidos);

        return "pedidos";
    }

    @GetMapping("/pedidos/{id}")
    public String detalle(Model model, @PathVariable Integer id ) {

        Pedido pedido = pedidoService.one(id);
        model.addAttribute("pedido", pedido);

        /*
        List<AgentStatisticsDTO> listaFacturacionComercialesDTO = pedidoService.estadisticasComercialAsociado(id);
        model.addAttribute("listaFacturacionComercialesDTO", listaFacturacionComercialesDTO);
        */
        return "detallePedido";
    }

    @GetMapping("/pedidos/crear")
    public String crear(Model model) {

        Pedido pedido = new Pedido();
        model.addAttribute("pedido", pedido);

        return "crearPedido";
    }

    @PostMapping("/pedidos/crear")
    public String submitCrear(@Valid @ModelAttribute("pedido") Pedido pedido, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){

            model.addAttribute("pedido", pedido);
            return "crearPedido";
        }

        pedidoService.newPedido(pedido);
        return "redirect:/pedidos?new="+pedido.getId();
    }

    @GetMapping("/pedidos/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        Pedido pedido = pedidoService.one(id);
        model.addAttribute("pedido", pedido);

        return "editarPedido";
    }

    @PostMapping("/pedidos/editar/{id}")
    public String submitEditar(@Valid @ModelAttribute("pedido") Pedido pedido, BindingResult bindingResult, Model model) {


        if(bindingResult.hasErrors()){

            model.addAttribute("pedido", pedido);

            return "editarPedido";
        }

        pedidoService.replacePedido(pedido);
        return "redirect:/pedidos?new="+pedido.getId();
    }

    @PostMapping("/pedidos/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {

        pedidoService.deletePedido(id);

        return new RedirectView("/pedidos");
    }
}

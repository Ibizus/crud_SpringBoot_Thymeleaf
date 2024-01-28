package org.iesvdm.controller;

import jakarta.validation.Valid;
import org.iesvdm.domain.Cliente;
import org.iesvdm.dto.AgentStatisticsDTO;
import org.iesvdm.service.ClienteService;
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
public class ClienteController {

    private ClienteService clienteService;

    //Se utiliza inyección automática por constructor del framework Spring.
    //Por tanto, se puede omitir la anotación Autowired
    //@Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    //@RequestMapping(value = "/clientes", method = RequestMethod.GET)
    //equivalente a la siguiente anotación
    @GetMapping("/clientes") //Al no tener ruta base para el controlador, cada método tiene que tener la ruta completa
    public String listar(Model model) {

        List<Cliente> listaClientes =  clienteService.listAll();
        model.addAttribute("listaClientes", listaClientes);

        return "clientes";
    }

    @GetMapping("/clientes/{id}")
    public String detalle(Model model, @PathVariable Integer id ) {

        Cliente cliente = clienteService.one(id);
        model.addAttribute("cliente", cliente);

        List<AgentStatisticsDTO> listaFacturacionComercialesDTO = clienteService.estadisticasComercialAsociado(id);
        model.addAttribute("listaFacturacionComercialesDTO", listaFacturacionComercialesDTO);

        return "detalleCliente";
    }

    @GetMapping("/clientes/crear")
    public String crear(Model model) {

        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);

        return "crearCliente";
    }

    @PostMapping("/clientes/crear")
    public String submitCrear(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){

            model.addAttribute("cliente", cliente);
            return "crearCliente";
        }

        clienteService.newCliente(cliente);
        return "redirect:/clientes?new="+cliente.getId();
    }

    @GetMapping("/clientes/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        Cliente cliente = clienteService.one(id);
        model.addAttribute("cliente", cliente);

        return "editarCliente";
    }

    @PostMapping("/clientes/editar/{id}")
    public String submitEditar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult bindingResult, Model model) {


        if(bindingResult.hasErrors()){

            model.addAttribute("cliente", cliente);

            return "crearCliente";
        }

        clienteService.replaceCliente(cliente);
        return "redirect:/clientes?new="+cliente.getId();
    }

    @PostMapping("/clientes/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {

        clienteService.deleteCliente(id);

        return new RedirectView("/clientes");
    }

}

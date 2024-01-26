package org.iesvdm.service;

import org.iesvdm.dao.ClienteDAO;
import org.iesvdm.dao.ClienteDAOImpl;
import org.iesvdm.dao.ComercialDAOImpl;
import org.iesvdm.domain.Cliente;
import org.iesvdm.domain.Comercial;
import org.iesvdm.dto.AgentStatisticsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteDAO clienteDAO;
    @Autowired
    private ClienteDAOImpl clienteDAOImpl;
    @Autowired
    private ComercialDAOImpl comercialDAO;

    public List<Cliente> listAll() {
        return clienteDAO.getAll();
    }

    public Cliente one(Integer id) {
        Optional<Cliente> optFab = clienteDAO.find(id);
        if (optFab.isPresent())
            return optFab.get();
        else
            return null;
    }

    public void newCliente(Cliente cliente) {

        clienteDAO.create(cliente);
    }

    public void replaceCliente(Cliente cliente) {

        clienteDAO.update(cliente);
    }

    public void deleteCliente(int id) {

        clienteDAO.delete(id);
    }


    public List<AgentStatisticsDTO> estadisticasComercialAsociado(int idCliente){

        List<AgentStatisticsDTO> listaResultados = new ArrayList<>();
        List<Comercial> comercialesAsociados = clienteDAOImpl.comercialesAsociados(idCliente).get();

        if(comercialesAsociados != null){

            for(Comercial comercial :comercialesAsociados) {

                listaResultados.add(new AgentStatisticsDTO(
                        comercial,
                        comercialDAO.numPedidosTotales(comercial.getId()),
                        comercialDAO.numPedidosTrimestre(comercial.getId()),
                        comercialDAO.numPedidosSemestre(comercial.getId()),
                        comercialDAO.numPedidosAnual(comercial.getId()),
                        comercialDAO.numPedidosLustro(comercial.getId())
                        ));
            }
            return listaResultados;

        }else return null;
    }



}
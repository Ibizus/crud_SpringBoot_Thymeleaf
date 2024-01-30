package org.iesvdm.service;

import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.domain.Pedido;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private PedidoDAO pedidoDAO;

    // Al inyectar en el constructor evitamos AUTOWIRED:
    public PedidoService(PedidoDAO pedidoDAO){

        this.pedidoDAO = pedidoDAO;
    }

    public List<Pedido> listAll(){
        return pedidoDAO.getAll();
    }

    public Pedido one(Integer id) {
        Optional<Pedido> optPedido = pedidoDAO.find(id);
        if (optPedido.isPresent())
            return optPedido.get();
        else
            return null;
    }

    public void newPedido(Pedido pedido) {

        pedidoDAO.create(pedido);
    }

    public void replacePedido(Pedido pedido) {

        pedidoDAO.update(pedido);
    }

    public void deletePedido(int id) {

        pedidoDAO.delete(id);
    }

}

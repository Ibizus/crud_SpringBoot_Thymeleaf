package org.iesvdm.service;

import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.domain.Comercial;
import org.iesvdm.domain.Pedido;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComercialService {

    private ComercialDAO comercialDAO;
    private PedidoDAO pedidoDAO;

    // Al inyectar en el constructor evitamos AUTOWIRED:
    public ComercialService(ComercialDAO comercialDAO, PedidoDAO pedidoDAO){

        this.comercialDAO = comercialDAO;
        this.pedidoDAO = pedidoDAO;
    }

    public List<Comercial> listAll(){
        return comercialDAO.getAll();
    }


    public Comercial one(Integer id) {
        Optional<Comercial> optComercial = comercialDAO.find(id);
        if (optComercial.isPresent())
            return optComercial.get();
        else
            return null;
    }

    public List<Pedido> pedidosComercial(Integer id){
        Optional<List<Pedido>> optPedidos = pedidoDAO.findPedidoByComercialId(id);
        if (optPedidos.isPresent())
            return optPedidos.get();
        else
            return null;
    }

    public void newComercial(Comercial comercial) {

        comercialDAO.create(comercial);
    }

    public void replaceComercial(Comercial comercial) {

        comercialDAO.update(comercial);
    }

    public void deleteComercial(int id) {

        comercialDAO.delete(id);
    }

}

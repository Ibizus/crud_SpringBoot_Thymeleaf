package org.iesvdm.service;

import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.domain.Comercial;
import org.iesvdm.domain.Pedido;
import org.iesvdm.dto.PedidoDetailsDTO;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

import static java.util.stream.Collectors.summarizingDouble;

@Service
public class ComercialService {

    private ComercialDAO comercialDAO;
    private PedidoDAO pedidoDAO;
    private PedidoDetailsDTO pedidoDTO;

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

    public void newComercial(Comercial comercial) {

        comercialDAO.create(comercial);
    }

    public void replaceComercial(Comercial comercial) {

        comercialDAO.update(comercial);
    }

    public void deleteComercial(int id) {

        comercialDAO.delete(id);
    }

    public List<Pedido> pedidosComercial(Integer id){
        Optional<List<Pedido>> optPedidos = pedidoDAO.findPedidoByComercialId(id);
        if (optPedidos.isPresent())
            return optPedidos.get();
        else
            return null;
    }

    public PedidoDetailsDTO infoPedidosComercial(List<Pedido> pedidosComercial){

        /*
        double suma = pedidosComercial.stream()
                .map(Pedido::getTotal)
                .reduce(0.0, Double::sum);

        double media = pedidosComercial.stream()
                .map(Pedido::getTotal)
                .reduce(0.0, Double::sum)/pedidosComercial.size();*/

        DoubleSummaryStatistics pedidoStatics = pedidosComercial.stream()
                .collect(summarizingDouble(Pedido::getTotal));

        PedidoDetailsDTO pedidoDTO = new PedidoDetailsDTO(
                pedidoStatics.getCount(),
                pedidoStatics.getSum(),
                pedidoStatics.getAverage(),
                pedidoStatics.getMax(),
                pedidoStatics.getMin());

        return pedidoDTO;
    }

}

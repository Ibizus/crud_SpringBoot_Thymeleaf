package org.iesvdm.dao;

import org.iesvdm.domain.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoDAO {

    public Optional<List<Pedido>> findPedidoByComercialId(int id);

}




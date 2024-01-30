package org.iesvdm.dao;

import org.iesvdm.domain.Pedido;
import org.iesvdm.domain.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoDAO {

    public void create(Pedido pedido);

    public List<Pedido> getAll();

    public Optional<Pedido> find(int id);

    public void update(Pedido pedido);

    public void delete(int id);

    public Optional<List<Pedido>> findPedidoByComercialId(int id);
}




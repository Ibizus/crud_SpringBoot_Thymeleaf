package org.iesvdm.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
// Anotación de Lombok para poder usar el objeto LOG y guardar una traza con diferentes niveles (info, error, debug)
@Repository // Componente de Spring de la capa de persistencia
public class PedidoDAOImpl implements PedidoDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Pedido pedido) {

    }

    @Override
    public List<Pedido> getAll() {
        return null;
    }

    @Override
    public Optional<Pedido> find(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Pedido pedido) {

    }

    @Override
    public void delete(int id) {

    }
}

package org.iesvdm.crud_springboot_thymeleaf.dao;

import org.iesvdm.crud_springboot_thymeleaf.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteDAOImpl implements ClienteDAO{


    private JdbcTemplate jdbcTemplate;

    /* OTRA FORMA DE INYECTAR LA JDBC EN EL CONSTRUCTOR (sin usar Autowired):
    public ClienteDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }*/

    @Override
    public void create(Cliente cliente) {

    }

    @Override
    public List<Cliente> getAll() {
        return null;
    }

    @Override
    public Optional<Cliente> find(int id) {
        return null;
    }

    @Override
    public void update(Cliente cliente) {

    }
}

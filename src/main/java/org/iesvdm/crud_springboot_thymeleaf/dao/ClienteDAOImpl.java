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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /* OTRA FORMA DE INYECTAR LA JDBC EN EL CONSTRUCTOR (sin usar Autowired):
    public ClienteDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }*/

    @Override
    public void create(Cliente cliente) {
        jdbcTemplate.update("INSERT INTO cliente (nombre, apellido1, apellido2, ciudad, categoría) VALUES (?, ?, ?, ?, ?)",
                cliente.getNombre(),
                cliente.getApellido1(),
                cliente.getApellido2(),
                cliente.getCiudad(),
                cliente.getCategoria());
    }

    @Override
    public List<Cliente> getAll() {

        List<Cliente> clienteList = jdbcTemplate.query(
                "SELECT * FROM cliente",
                (rs, rowNum -> new Cliente(rs.getInt("id")))
        )
    }
5e
    @Override
    public Optional<Cliente> find(int id) {
        return null;
    }

    @Override
    public void update(Cliente cliente) {

    }

    @Override
    public void delete(int id) {

    }
}

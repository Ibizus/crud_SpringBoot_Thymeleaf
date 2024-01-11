package org.iesvdm.crud_springboot_thymeleaf.dao;

import org.iesvdm.crud_springboot_thymeleaf.domain.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteDAO {

    public void create(Cliente cliente);

    public List<Cliente> getAll();

    public Optional<Cliente> find(int id);

    public void update(Cliente cliente);

    public void delete(int id);
}

package org.iesvdm.crud_springboot_thymeleaf.dao;

import org.iesvdm.crud_springboot_thymeleaf.domain.Comercial;

import java.util.List;
import java.util.Optional;

public interface ComercialDAO {

    public void create(Comercial comercial);

    public List<Comercial> getAll();

    public Optional<Comercial> find(int id);

    public void update(Comercial comercial);

    public void delete(int id);
}

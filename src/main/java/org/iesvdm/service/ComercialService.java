package org.iesvdm.service;

import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.domain.Comercial;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComercialService {

    private ComercialDAO comercialDAO;

    // Al inyectar en el constructor evitamos AUTOWIRED:
    public ComercialService(ComercialDAO comercialDAO){
        this.comercialDAO = comercialDAO;
    }

    public List<Comercial> listAll(){
        return comercialDAO.getAll();
    }
}

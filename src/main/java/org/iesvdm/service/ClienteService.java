package org.iesvdm.service;

import org.iesvdm.dao.ClienteDAO;
import org.iesvdm.domain.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private ClienteDAO clienteDAO;

    // Al inyectar en el constructor evitamos AUTOWIRED:
    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public List<Cliente> listAll() {
        return clienteDAO.getAll();
    }

}

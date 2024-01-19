package org.iesvdm.service;

import org.iesvdm.dao.ClienteDAO;
import org.iesvdm.domain.Cliente;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    public Cliente one(Integer id) {
        Optional<Cliente> optFab = clienteDAO.find(id);
        if (optFab.isPresent())
            return optFab.get();
        else
            return null;
    }

    public void newCliente(Cliente cliente) {

        clienteDAO.create(cliente);
    }

    public void replaceCliente(Cliente cliente) {

        clienteDAO.update(cliente);
    }

    public void deleteCliente(int id) {

        clienteDAO.delete(id);
    }


}

package Database;

import Entity.Cliente;

import java.util.List;

public interface ClienteCRUD {

    Cliente create(Cliente cliente);

    List<Cliente> findAll();

    List<Cliente> findByFilter(String filter, String value);

    void update(Cliente cliente);

    void delete(Integer id);
}

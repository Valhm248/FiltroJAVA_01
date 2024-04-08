package Database;

import Entity.Cliente;
import Entity.Compra;

import java.util.List;

public interface CompraCRUD {

    Compra create(Compra compra);

    List<Compra> findAll();

    List<Compra> findByFilter(String filter, String value);

    void update(Compra compra);

    void delete(Integer id);
}

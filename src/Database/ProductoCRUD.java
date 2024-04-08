package Database;

import Entity.Cliente;
import Entity.Producto;

import java.util.List;

public interface ProductoCRUD {

    Producto create(Producto producto);

    List<Producto> findAll();

    List<Producto> findByFilter(String filter, String value);

    void update(Producto producto);

    void delete(Integer id);
}

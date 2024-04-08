package Database;

import Entity.Tienda;

import java.util.List;

public interface TiendaCRUD {

    List<Tienda> findAll();

    void delete(Integer id);
}

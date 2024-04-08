package Model;

import Database.ConfigDB;
import Database.TiendaCRUD;
import Entity.Tienda;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TiendaModel implements TiendaCRUD {

    @Override
    public List<Tienda> findAll() {

        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        List<Tienda> tiendaList = new ArrayList<>();

        try {
            //3. Crear SQL
            String sql = "SELECT * FROM tiendas;";

            //4.Prepara el statement
            PreparedStatement prepare = (PreparedStatement) connection.prepareStatement(sql);

            //5. Ejecutamos el query
            ResultSet resultSet = prepare.executeQuery();

            while (resultSet.next()){

                //creamos instancias
                Tienda tienda = new Tienda();

                //Llenamos nuestros objeto con lo que devuelve la base de datos
                tienda.setId(resultSet.getInt("id"));
                tienda.setNombre(resultSet.getString("nombre"));
                tienda.setUbicacion(resultSet.getString("ubicacion"));


                //Agregamos a la lista
                tiendaList.add(tienda);
            }

            //8. Cerramos el prepare
            prepare.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error to show list of tiendas");
        }

        //9. Cerrar conexión
        ConfigDB.closeConnection();

        return tiendaList;
    }

    @Override
    public void delete(Integer id) {

    }
}

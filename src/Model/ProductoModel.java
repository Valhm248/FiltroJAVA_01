package Model;

import Database.ConfigDB;
import Database.ProductoCRUD;
import Entity.Producto;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductoModel implements ProductoCRUD {

    TiendaModel tiendaModel = new TiendaModel();
    @Override
    public Producto create(Producto producto) {

        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "INSERT INTO productos(nombre, precio, id_tienda) VALUE ( ?, ?, ?);";

            PreparedStatement prepareCall = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);


            prepareCall.setString(1, producto.getNombre());
            prepareCall.setDouble(2, producto.getPrecio());
            prepareCall.setInt(3, producto.getIdTienda());

            prepareCall.execute();

            ResultSet result = prepareCall.getGeneratedKeys();

            while (result.next()) {
                producto.setId(result.getInt(1));
            }


            JOptionPane.showMessageDialog(null, "created correctly\n" + producto);

            prepareCall.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error creating\n " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return producto;
    }

    @Override
    public List<Producto> findAll() {

        Connection connection = ConfigDB.openConnection();
        List<Producto> productoList = new ArrayList<>();

        try {

            String sql = "SELECT * FROM productos;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            ResultSet result = prepareCall.executeQuery();


            while (result.next()) {

                Producto producto = new Producto();

                producto.setId(result.getInt("id"));
                producto.setNombre(result.getString("nombre"));
                producto.setPrecio(result.getDouble("precio"));
                producto.setIdTienda(result.getInt("id_tienda"));



                productoList.add(producto);
            }

            prepareCall.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error show the list" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return productoList;
    }

    @Override
    public List<Producto> findByFilter(String filter, String value) {
        String sql;

        List<Producto> productoList = new ArrayList<>();

        try {

            if (Objects.equals(filter, "ID")) {
                sql = "SELECT * FROM productos WHERE id = ?;";

                productoList = findById(sql, value);

            }
            if (Objects.equals(filter, "Nombre")) {

                sql = "SELECT * FROM productos WHERE nombre LIKE ?;";

                productoList = findByName(sql, value);
            }


            if (Objects.equals(filter, "Tienda")) {

                sql = "SELECT * FROM productos f JOIN tiendas a ON f.id_tienda = a.id WHERE a.id = ?;";

                productoList = findByStore(sql, value);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error application filter " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return productoList;
    }

    public List<Producto> findById(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Producto> productoList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Producto producto = new Producto();

                producto.setId(result.getInt("id"));
                producto.setNombre(result.getString("nombre"));
                producto.setPrecio(result.getDouble("precio"));
                producto.setIdTienda(result.getInt("id_tienda"));


                productoList.add(producto);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, productoList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error to show filter" + e.getMessage());
        }

        return productoList;

    }
    public List<Producto> findByName(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Producto> productoList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Producto producto = new Producto();

                producto.setId(result.getInt("id"));
                producto.setNombre(result.getString("nombre"));
                producto.setPrecio(result.getDouble("precio"));
                producto.setIdTienda(result.getInt("id_tienda"));



                productoList.add(producto);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, productoList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error to show filter" + e.getMessage());
        }

        return productoList;

    }

    public List<Producto> findByStore(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Producto> productoList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));

            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Producto producto = new Producto();

                producto.setId(result.getInt("id"));
                producto.setNombre(result.getString("destination"));
                producto.setPrecio(result.getDouble("departure_date"));
                producto.setIdTienda(result.getInt("id_tienda"));



                productoList.add(producto);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, productoList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error to show filter" + e.getMessage());
        }

        return productoList;

    }

    @Override
    public void update(Producto producto) {
        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "UPDATE productos SET nombre = ?,  precio = ?, id_tienda = ? WHERE id =?;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            prepareCall.setString(1, producto.getNombre());
            prepareCall.setDouble(2, Double.valueOf(producto.getPrecio()));
            prepareCall.setInt(3, producto.getIdTienda());
            prepareCall.setInt(4, producto.getId());

            prepareCall.execute();

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "updated.\n" + producto);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error \n " + e.getMessage());
        }

        ConfigDB.closeConnection();
    }

    @Override
    public void delete(Integer id) {
        Connection connection = ConfigDB.openConnection();

        try{
            String sql = "DELETE FROM productos WHERE id = ?;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, id);
            prepareCall.execute();
            prepareCall.close();

            JOptionPane.showMessageDialog(null, "eliminated\n");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error deleting\n" + e.getMessage());
        }

        ConfigDB.closeConnection();

    }
}

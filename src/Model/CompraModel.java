package Model;

import Database.CompraCRUD;
import Database.ConfigDB;
import Entity.Compra;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompraModel  implements CompraCRUD {
    @Override
    public Compra create(Compra compra) {

        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "INSERT INTO compras (fecha_compra, cantidad, id_cliente, id_producto) VALUE ( ?, ?, ?, ?);";

            PreparedStatement prepareCall = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            prepareCall.setDate(1, Date.valueOf(compra.getFechaCompra()));
            prepareCall.setInt(2, compra.getCantidad());
            prepareCall.setInt(3, compra.getIdCliente());
            prepareCall.setInt(4, compra.getIdProducto());

            prepareCall.execute();

            ResultSet result = prepareCall.getGeneratedKeys();

            while (result.next()) {
                compra.setId(result.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Reserve created correctly\n" + compra);

            prepareCall.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error creating compra\n " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return compra;
    }

    @Override
    public List<Compra> findAll() {

        Connection connection = ConfigDB.openConnection();

        List<Compra> compraList = new ArrayList<>();

        try {

            String sql = "SELECT * FROM compras;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            ResultSet result = prepareCall.executeQuery();


            while (result.next()) {

                Compra compras = new Compra();

                compras.setId(result.getInt("id"));
                compras.setFechaCompra(result.getString("fecha_compra"));
                compras.setCantidad(result.getInt("cantidad"));
                compras.setIdCliente(result.getInt("id_passengers"));
                compras.setIdProducto(result.getInt("id_flight"));



                compraList.add(compras);
            }

            prepareCall.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error show the list" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return compraList;
    }

    @Override
    public List<Compra> findByFilter(String filter, String value) {
        String sql;

        List<Compra> compraList = new ArrayList<>();

        try {

            if (Objects.equals(filter, "ID")) {
                sql = "SELECT * FROM compras WHERE id = ?;";

                compraList = findById(sql, value);

            }

            if (Objects.equals(filter, "Producto")) {

                sql = "SELECT * FROM compras r JOIN productos p ON r.id_productos = p.id WHERE p.id  = ?;";

                compraList = findByProduct(sql, value);
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error application filter " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return compraList;
    }

    public List<Compra> findById(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Compra> compraList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Compra compras = new Compra();

                compras.setId(result.getInt("id"));
                compras.setFechaCompra(result.getString("fecha_compra"));
                compras.setCantidad(result.getInt("cantidad"));
                compras.setIdCliente(result.getInt("id_cliente"));
                compras.setIdProducto(result.getInt("id_producto"));

                compraList.add(compras);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, compraList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error to show filter" + e.getMessage());
        }

        return compraList;

    }

    public List<Compra> findByProduct(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Compra> compraList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Compra compras = new Compra();

                compras.setId(result.getInt("id"));
                compras.setFechaCompra(result.getString("fehca_compra"));
                compras.setCantidad(result.getInt("cantidad"));
                compras.setIdCliente(result.getInt("id_cliente"));
                compras.setIdProducto(result.getInt("id_producto"));

                compraList.add(compras);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, compraList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error to show filter" + e.getMessage());
        }

        return compraList;

    }
    @Override
    public void update(Compra compra) {

        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "UPDATE compras SET fecha_compra = ?,  cantidad = ?, id_cliente = ?, id_producto = ? WHERE id =?;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            prepareCall.setDate(1, Date.valueOf(compra.getFechaCompra()));
            prepareCall.setInt(2, compra.getCantidad());
            prepareCall.setInt(3, compra.getIdCliente());
            prepareCall.setInt(4, compra.getIdProducto());
            prepareCall.setInt(5, compra.getId());
            prepareCall.execute();

            prepareCall.close();
            JOptionPane.showMessageDialog(null, " updated.\n" + compra);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error \n " + e.getMessage());
        }

        ConfigDB.closeConnection();
    }

    @Override
    public void delete(Integer id) {

        Connection connection = ConfigDB.openConnection();

        try{
            String sql = "DELETE FROM compras WHERE id = ?;";

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

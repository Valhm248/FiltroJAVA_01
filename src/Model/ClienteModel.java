package Model;

import Database.ClienteCRUD;
import Database.ConfigDB;
import Entity.Cliente;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClienteModel implements ClienteCRUD {


    @Override
    public Cliente create(Cliente cliente) {

        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "INSERT INTO clientes(nombre, apellido, email) VALUE ( ?, ?, ?);";

            PreparedStatement prepareCall = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            prepareCall.setString(1, cliente.getNombre());
            prepareCall.setString(2, cliente.getApellido());
            prepareCall.setString(3, cliente.getEmail());

            prepareCall.execute();

            ResultSet result = prepareCall.getGeneratedKeys();

            while (result.next()) {
                cliente.setId(result.getInt(1));
            }


            JOptionPane.showMessageDialog(null, "Creado correctamente\n" + cliente);

            prepareCall.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear\n " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return cliente;
    }

    @Override
    public List<Cliente> findAll() {

        Connection connection = ConfigDB.openConnection();
        List<Cliente> clienteList = new ArrayList<>();

        try {

            String sql = "SELECT * FROM clientes;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            ResultSet result = prepareCall.executeQuery();


            while (result.next()) {

                Cliente clientes = new Cliente();

                clientes.setId(result.getInt("id"));
                clientes.setNombre(result.getString("nombre"));
                clientes.setApellido(result.getString("apellido"));
                clientes.setEmail(result.getString("email"));

                clienteList.add(clientes);
            }

            prepareCall.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error show the list" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return clienteList;
    }

    @Override
    public List<Cliente> findByFilter(String filter, String value) {

        String sql;

        List<Cliente> clienteList = new ArrayList<>();

        try {

            if (Objects.equals(filter, "ID")) {
                sql = "SELECT * FROM clientes WHERE id = ?;";

                clienteList = findById(sql, value);

            }
            if (Objects.equals(filter, "nombre")) {

                sql = "SELECT * FROM clientes WHERE nombre = ?;";

                clienteList = findByName(sql, value);
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error application filter " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return clienteList;
    }

    public List<Cliente> findById(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Cliente> clienteList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Cliente clientes = new Cliente();

                clientes.setId(result.getInt("id"));
                clientes.setNombre(result.getString("nombre"));
                clientes.setApellido(result.getString("apellido"));
                clientes.setEmail(result.getString("email"));


                clienteList.add(clientes);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, clienteList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }

        return clienteList;

    }

    private List<Cliente> findByName(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Cliente> clienteList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet objResult = prepareCall.executeQuery();

            while (objResult.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(objResult.getInt("id"));
                cliente.setNombre(objResult.getString("nombre"));
                cliente.setApellido(objResult.getString("apellido"));
                cliente.setEmail(objResult.getString("email"));


                clienteList.add(cliente);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista: " + clienteList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());

        }

        return clienteList;

    }

    @Override
    public void update(Cliente cliente) {

        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "UPDATE clientes SET nombre = ?,  apellido = ?, email = ? WHERE id =?;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            prepareCall.setString(1, cliente.getNombre());
            prepareCall.setString(2, cliente.getApellido());
            prepareCall.setString(3, cliente.getEmail());
            prepareCall.setInt(4, cliente.getId());
            prepareCall.execute();

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "updated.\n" + cliente);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error \n " + e.getMessage());
        }

        ConfigDB.closeConnection();
    }

    @Override
    public void delete(Integer id) {
        Connection connection = ConfigDB.openConnection();

        try{
            String sql = "DELETE FROM clientes WHERE id = ?;";

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

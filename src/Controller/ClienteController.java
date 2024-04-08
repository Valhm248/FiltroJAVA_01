package Controller;

import Entity.Cliente;
import Model.ClienteModel;
import Model.CompraModel;
import Model.ProductoModel;

import javax.swing.*;
import java.util.List;

public class ClienteController {

    ClienteModel clienteModel;



    public ClienteController(ClienteModel clienteModel){

        this.clienteModel = clienteModel;
    }

    public void delete(){
        JOptionPane.showMessageDialog(null, "List: " + clienteModel.findAll());

        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert the id of passenger to delete"));

        this.clienteModel.delete(id);
    }

    public void update(){
        JOptionPane.showMessageDialog(null, "List: " + clienteModel.findAll());

        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert the id: "));

        String update_nombre = JOptionPane.showInputDialog(null, "Insert new name: ");
        String update_apellido = JOptionPane.showInputDialog(null, "Insert new last name: ");
        String update_email = JOptionPane.showInputDialog(null, "Insert new identity card: ");

        Cliente cliente = new Cliente();

        cliente.setNombre(update_nombre);
        cliente.setApellido(update_apellido);
        cliente.setEmail(update_email);
        cliente.setId(id);

        this.clienteModel.update(cliente);

        JOptionPane.showMessageDialog(null, "Updated successfully");
    }

    public void findByFilter(){

        String[] options = {"ID" ,"Nombre", "Apellido", "Email" };

        String selectedFilter = (String) JOptionPane.showInputDialog(null, "Select the filter\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        String valueFilter = JOptionPane.showInputDialog(null, "Insert necccesary information\n" + "(ID cliente, nombre, apellido, email");

        List<Cliente> listCliente = this.clienteModel.findByFilter(selectedFilter, valueFilter);
    }

    public void findAll(){


        List<Cliente> clienteList;

        clienteList = this.clienteModel.findAll();

        JOptionPane.showMessageDialog(null, "List: \n" + clienteList);
    }

    public void create() {

        Cliente cliente = new Cliente();

        String nombre = JOptionPane.showInputDialog(null, "Insert name: ");
        String apellido = JOptionPane.showInputDialog(null, "Insert last name: ");
        String email = JOptionPane.showInputDialog(null, "Insert email: ");

        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setEmail(email);

        cliente = this.clienteModel.create(cliente);

        JOptionPane.showMessageDialog(null, "Created succesfully \n" + cliente.toString());

    }

}

package Controller;


import Entity.Producto;
import Entity.Tienda;
import Model.ProductoModel;
import Model.TiendaModel;

import javax.swing.*;


import java.util.List;

public class ProductoController {

    ProductoModel productoModel;
    TiendaModel tiendaModel;

    public ProductoController(ProductoModel productoModel, TiendaModel tiendaModel){
        this.productoModel = new ProductoModel();
        this.tiendaModel = new TiendaModel();
    }

    public void delete(){

        List<Producto> productoList = productoModel.findAll();

        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "List of the producto:\n" + productoList + "\nEnter the producto ID to delete\n"));
        productoList.stream().filter(producto -> producto.getId().equals(id)).findFirst().get();

        this.tiendaModel.delete(id);

    }

    public void update(){

        List<Producto> productoList = productoModel.findAll();

        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "List of the producto:\n" + productoList + "\nEnter the producto ID to update\n"));
        Producto productoFilter = productoList.stream().filter(producto -> producto.getId().equals(id)).findFirst().get();



        String nombre = JOptionPane.showInputDialog(null, "Enter the new nombre\n: ", productoFilter.getNombre());
        Double precio = Double.valueOf(JOptionPane.showInputDialog(null, "Enter the price\n", productoFilter.getPrecio()));

        List<Tienda> tiendaList = tiendaModel.findAll();

        Object[] options = tiendaList.stream().map(Tienda::getNombre).toArray();
        String selectedFilter =
                (String) JOptionPane.showInputDialog(null, "Select of the producto\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        Tienda tienda = tiendaList.stream().filter(tiendaFilter -> tiendaFilter.getNombre().equals(selectedFilter)).findFirst().get();

        Producto producto = new Producto();

        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setIdTienda(tienda.getId());
        producto.setId(id);

        this.productoModel.update(producto);

        JOptionPane.showMessageDialog(null, "Producto data was updated correctly");

    }

    public void findByFilters() {

        String[] options = {"ID", "Nombre", "Precio", "Tienda"};

        String selectedFilter = (String) JOptionPane.showInputDialog(null, "Select filter\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (selectedFilter == "Tienda") {
            List<Tienda> tiendaList = tiendaModel.findAll();
            Object[] optionsTienda = tiendaList.stream().map(Tienda::getNombre).toArray();
            String selectedFilterTienda = (String) JOptionPane.showInputDialog(null, "Select the tienda model\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsTienda, optionsTienda[0]);

            Tienda tienda = tiendaList.stream().filter(tiendaFilter -> tiendaFilter.getNombre().equals(selectedFilterTienda)).findFirst().get();

            List<Producto> productoList = this.productoModel.findByFilter(selectedFilter, String.valueOf(tienda.getId()));

        }else {

            String valueFilter = JOptionPane.showInputDialog(null, "Enter the requested data for the query\n");
            List<Producto> productoList = this.productoModel.findByFilter(selectedFilter, valueFilter);
        }



    }

    public void findAll() {

        List<Producto> productoList = this.productoModel.findAll();
        JOptionPane.showMessageDialog(null, "List of producto:\n" + productoList);
    }

    public void create() {

        Producto producto = new Producto();

        List<Tienda> tiendaList = tiendaModel.findAll();

        String nombre = JOptionPane.showInputDialog(null, "Enter name\n");
        Double precio = Double.valueOf(JOptionPane.showInputDialog(null, "Enter price\n"));

        Object[] options = tiendaList.stream().map(Tienda::getNombre).toArray();
        String selectedFilter =
                (String) JOptionPane.showInputDialog(null, "Select of the producto\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        Tienda tienda = tiendaList.stream().filter(tiendaFilter -> tiendaFilter.getNombre().equals(selectedFilter)).findFirst().get();

        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setIdTienda(tienda.getId());

        this.productoModel.create(producto);

    }
}

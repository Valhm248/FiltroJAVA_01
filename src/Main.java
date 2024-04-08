import Controller.ClienteController;
import Controller.CompraController;
import Controller.ProductoController;
import Database.ConfigDB;
import Model.ProductoModel;
import Model.ClienteModel;
import Model.CompraModel;
import Model.TiendaModel;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {


        ConfigDB.openConnection();

        ClienteModel clienteModel = new ClienteModel();
        ClienteController clienteController = new ClienteController(clienteModel);

        TiendaModel tiendaModel = new TiendaModel();

        ProductoModel productoModel = new ProductoModel();
        ProductoController productoController = new ProductoController(productoModel, tiendaModel);

        CompraModel compraModel = new CompraModel();
        CompraController compraController = new CompraController(compraModel, productoModel, clienteModel);



        String option;

        do {

            option = JOptionPane.showInputDialog("""
                    MENÚ
                                        
                    1. Menú Cliente 
                    2. Menú Producto 
                    3. Menú Compra 
                    4. Exit.
                    
                    """);

            switch (option) {
                case "1":
                    do {
                        option = JOptionPane.showInputDialog("""
                                
                                                                
                                Cliente Manager select the desired option:
                                                                
                                1. Crea un nuevo cliente.
                                2. Filtra cliente.
                                3. Actualiza cliente.
                                4. Elimina cliente.
                                5. Muestra todos los clientes.
                                6. Exit.
                                """);
                        switch (option) {
                            case "1":
                                clienteController.create();
                                break;
                            case "2":
                                clienteController.findByFilter();
                                break;
                            case "3":
                                clienteController.update();
                                break;
                            case "4":
                                clienteController.delete();
                                break;
                            case "5":
                                clienteController.findAll();
                                break;
                        }
                    } while (!option.equals("6"));
                    break;
                case "2":
                    do {
                        option = JOptionPane.showInputDialog("""
                                
                                                                
                                Producto Manager select the desired option:
                                                                
                                1. Crea un nuevo Producto.
                                2. Filtra Producto.
                                3. Actualiza Producto.
                                4. Elimina Producto.
                                5. Muestra todos los Productos.
                                6. Exit.
                                """);
                        switch (option) {
                            case "1":
                                productoController.create();
                                break;
                            case "2":
                                productoController.findByFilters();
                                break;
                            case "3":
                                productoController.update();
                                break;
                            case "4":
                                productoController.delete();
                                break;
                            case "5":
                                productoController.findAll();
                                break;
                        }
                    } while (!option.equals("6"));
                    break;
                case "3":
                    do {
                        option = JOptionPane.showInputDialog("""
                                
                                                                
                                Compra Administrator select the desired option:
                                                                
                                1. Crea una nueva Compra.
                                2. Filtra Compra.
                                3. Actualiza Compra.
                                4. Elimina Compra.
                                5. Muestra todas los Compras.
                                6. Exit.
                                """);
                        switch (option) {
                            case "1":
                                compraController.create();
                                break;
                            case "2":
                                compraController.findByFilter();
                                break;
                            case "3":
                                compraController.update();
                                break;
                            case "4":
                                compraController.delete();
                                break;
                            case "5":
                                compraController.findAll();
                                break;
                        }
                    } while (!option.equals("5"));
                    break;

            }

        } while (!option.equals("4"));

    }
}
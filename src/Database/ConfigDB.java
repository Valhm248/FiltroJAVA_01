package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    static Connection connection = null;

    //Método para abrir la conexión entre Java y la base de datos

    public static Connection openConnection(){

        try {

            // Class.forName permite obtener o implementar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Creamos variables con nuestras credenciales de la base de datos
            String url = "jdbc:mysql://bmtkunykponkqzac9wjz-mysql.services.clever-cloud.com:3306/bmtkunykponkqzac9wjz";
            String user = "ub8uuhvsyexuxfni";
            String password = "o8zc7gCLpsQTGuQw7m0m";


            //Establecemos la conexion
            connection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida");

        }catch (ClassNotFoundException e){
            System.out.println("Error >> Driver no instalados \n"+e.getMessage());
        }catch (SQLException e){
            System.out.println("Error >> La conexión a BD no fue establecida\n"+e.getMessage());
        }

        return connection;
    }


    public static void closeConnection(){

        //Si hay una conexión activa, la cerramos
        try{
            if (connection != null) connection.close();

        }catch (SQLException e){
            System.out.println("Error >> " + e.getMessage());
        }
    }
}

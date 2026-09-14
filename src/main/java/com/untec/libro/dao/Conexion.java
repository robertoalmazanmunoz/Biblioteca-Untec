package com.untec.libro.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <h2>Clase Conexion</h2>
 * <p>Clase que permite generar la conexión con la base de datos.</p>
 * @since 1.0
 * @author Roberto Almazán (Untec)
 */
public class Conexion {

    public static Conexion instance; //aplicar el patrón SINGLETON
    private Connection conexion;
    //Datos para acceder a la Base de Datos (Usuario, Contraseña, Ubicación y Nombre)
    private final String USER = "root";
    private final String PASSWORD = "1234";
    private final String SERVER = "localhost:3306";
    private final String BBDD = "bd_biblioteca_untec";
    
    //Genera la conexión
    private Conexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + SERVER + "/" + BBDD;
            conexion = DriverManager.getConnection(url, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException   ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Método que permite crear una instancia de conexión a la base de datos.
     * @return instance
     * @since 1.0
     * @author Roberto Almazán (Untec)
     */
    public synchronized static Conexion getEstado(){
        if(instance == null){
            instance = new Conexion();
        }
        return instance;
    }
    
    /**
     * Método que permite obtener una conexión.
     * @return conexion
     * @since 1.0
     * @author Roberto Almazán (Untec)
     */
    public Connection getConexion(){
        return conexion;
    }
    
    /**
     * Método que permite eliminar una conexión
     * @since 1.0
     * @author Roberto Almazán (Untec)
     */
    public void cerrarConexion(){
        instance = null;
        System.gc();
    }
    
}
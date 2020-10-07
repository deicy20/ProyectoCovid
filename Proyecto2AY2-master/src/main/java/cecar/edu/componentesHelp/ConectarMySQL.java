package cecar.edu.componentesHelp;

/**
 * Clase: ConectarMySQL
 *
 * Version: 1.0
 *
 * Fecha Creación: 2014-01-25
 *
 * Autor: Jhon Jaime Mendez Alandete
 *
 * Copyright: Jhon Jaime Mendez Alandete
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Esta clase permite conectar a una base de datos Mysql y ademas ejecutar
 * sentencias DML
 */
final public class ConectarMySQL {

    //** Declaracion de variables
    private static Connection conexion;

    /**
     * Constructor general que se conecta a la base de datos dependiendo de los
     * parametros
     *
     * @param servidorNombre Nombre del servidor o direccion IP
     * @param puerto puerto de conexion
     * @param nombreBD nombre de la base de datos
     * @param usuario Usuario autorizado
     * @param password
     *
     */
    public static String servidorNombre = "127.0.0.1";
    public static String puerto= "3306";
    public static String nombreBD = "scraper";
    public static String usuario = "root";
    public static String password = "";

    public static void getConectarMySQL() throws Exception {

        if (conexion == null) {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            // Se conecta a la base de datos
            // Se crea un URL hacia la maquina y la base de datos
            String url = "jdbc:mysql://" + servidorNombre +":"+puerto+"/" + nombreBD;

            //se crea la conexion a la base de datos
            conexion = DriverManager.getConnection(url, usuario, password);

            //conexion.setAutoCommit(false);
        }

    }

    /**
     * Devuelve el objeto que permite la conexion a la base de datos
     *
     * @return Connection Conexion a la base de datos
     *
     */
    public static Connection getConexion() {

        return conexion;

    }

    /**
     * Permite hacer los cambios permanentes en la BD
     *
     */
    public void commit() throws Exception {

        conexion.commit();

    }

    /**
     * Permite deshacer cambios en la BD antes del ultimo commit
     *
     */
    public void rollback() throws Exception {

        conexion.rollback();

    }

}

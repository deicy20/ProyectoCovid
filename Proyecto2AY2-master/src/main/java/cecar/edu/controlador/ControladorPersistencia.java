/*
* Clase: ControladorPersistencia
*
* Version: 1.0
*
* Fecha de creación: 2020-09-04
*
* Autor: Angie Benavides y Deicy León
*/
package cecar.edu.controlador;

import cecar.edu.componentesHelp.ConectarMySQL;
import cecar.edu.modelo.Articulo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

/* 
*Esta clase permite mediante funciones tener CRUD
 */
public class ControladorPersistencia {

    //ArrayList global para tener en memoria principal los registros hechos a la BD
    public static ArrayList<String> consulta = new ArrayList<>();

    //Guardar url en la BD
    public static String guardarUrl(String url) {
        String resultado = "OK";

        try {
            System.out.println("Insertando datos a BD...");

            PreparedStatement preparedStatement = ConectarMySQL.getConexion().prepareStatement("insert into urls values (?)");

            preparedStatement.setString(1, url);
            preparedStatement.execute();

        } catch (SQLException e) {

            resultado = e.getMessage();
            System.out.println("Error 1: " + e);
            JOptionPane.showMessageDialog(null, "URL ya registrada");

        }

        return resultado;
    }

    //Eliminar url de la BD
    public static int eliminarUrl(String valor) {
        int n=0;
        try {
                    Connection cn = ConectarMySQL.getConexion();
                    String sql = "";
                    sql = "DELETE FROM urls WHERE url=?";
                    PreparedStatement pst = cn.prepareStatement(sql);
                    pst.setString(1, valor);
                    n = pst.executeUpdate();
                   
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Eliminado no exitoso" + ex);
                    
                }
        return n;

    }

    //Modifica url de la BD
    public static int modificarUrl(String urlNueva, String urlAntigua) {
        int n=0;
        try {
            Connection cn = ConectarMySQL.getConexion();
            String sql = "";
            sql = "UPDATE urls SET url=? WHERE url=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, urlNueva);
            pst.setString(2, urlAntigua);
            n = pst.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Modificación no exitosa " + ex);

        }
        return n;
    }

    //Consultar urls
    public static ArrayList<String> consulta(String valor) throws Exception {

        ConectarMySQL.getConectarMySQL();

        consulta.clear();

        String sql = "SELECT * FROM urls where CONCAT (url) LIKE '%" + valor + "%'";
        //Hacer conexion con la BD
        Connection cn = ConectarMySQL.getConexion();
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            //ciclo para recorrer los datos obtenidos de la BD
            while (rs.next()) {
                consulta.add(rs.getString("url"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error");
        }

        return consulta;
    }
    public static String guardarArticulo(Articulo art){
        String resultado = "OK";

        try {
            System.out.println("Insertando datos a BD...");

            PreparedStatement preparedStatement = ConectarMySQL.getConexion().prepareStatement("insert into articulo values (?,?,?)");

            preparedStatement.setString(1, art.getTitulo());
            preparedStatement.setString(2, art.getFechaDePublicacion());
            preparedStatement.setString(3, convertirArrayACadena(art.getContenido()));
            preparedStatement.execute();

        } catch (SQLException e) {

            resultado = e.getMessage();
            System.out.println("Error llave repetida: " + e);
           // JOptionPane.showMessageDialog(null, "Ocurrio un error");

        }

        return resultado;
    }
    public static String convertirArrayACadena(ArrayList<String> array) {
        String cadena = "";
        for (int i = 0; i < array.size(); i++) {
            cadena = cadena + " " + array.get(i).toUpperCase();
        }
        return cadena.toLowerCase();
    }
}

/*
* Clase: Articulo
*
* Version: 1.0
* 
* Fecha de creación: 2020-09-04
*
* Autor: Angie Benavides y Deicy León
 */
package cecar.edu.modelo;

import java.util.ArrayList;


public class Articulo {
    private String fechaDePublicacion;
    private String titulo;
    private ArrayList<String> contenido=new ArrayList<String>();

    public Articulo(String fechaDePublicacion, String titulo) {
        this.fechaDePublicacion = fechaDePublicacion;
        this.titulo = titulo;
    }
    public Articulo() {
        this.fechaDePublicacion = "";
        this.titulo = "";
    }

    public String getFechaDePublicacion() {
        return fechaDePublicacion;
    }

    public void setFechaDePublicacion(String fechaDePublicacion) {
        this.fechaDePublicacion = fechaDePublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<String> getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido.add(contenido);
    }

    
    
    
    
}

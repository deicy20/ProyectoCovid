/*
* Clase: Palabra
*
* Version: 1.0
* 
* Fecha de creación: 2020-09-04
*
* Autor: Angie Benavides y Deicy León
 */
package cecar.edu.modelo;


public class Palabra {
    
    private String palabra;
    private int pesoTotal;

    public Palabra() {
        this.palabra = "";
        this.pesoTotal = 0;
    }
    public Palabra(String palabra, int pesoTotal) {
        this.palabra = palabra;
        this.pesoTotal = pesoTotal;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public int getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(int pesoTotal) {
        this.pesoTotal = pesoTotal;
    }
}

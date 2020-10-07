/*
* Clase: ControladorTendencias
*
* Version: 1.0
* 
* Fecha de creación: 2020-09-04
*
* Autor: Angie Benavides y Deicy León
 */
package cecar.edu.controlador;

import cecar.edu.modelo.Palabra;
import java.util.ArrayList;

public class ControladorTendecias {

    public static ArrayList<Palabra> palabras = new ArrayList<Palabra>();

    public static Palabra tendencia(String textoTitulo, String textoContenido, String palabra, int pesoTitulo, int pesoContenido) {

        String arregloT[] = textoTitulo.split(" ");
        String arregloC[] = textoContenido.split(" ");
        Palabra tendencia;

        if (estaPalabra(palabra)) {
            tendencia = new Palabra();
            tendencia.setPalabra(palabra);
            for (int i = 0; i < arregloT.length; i++) {
                if (arregloT[i].equals(palabra)) {
                    int a = palabras.get(posicioPalabra(palabra)).getPesoTotal() + pesoTitulo;
                    tendencia.setPesoTotal(a);
                    palabras.set(posicioPalabra(palabra), tendencia);
                }
            }
            for (int i = 0; i < arregloT.length; i++) {
                if (arregloC[i].equals(palabra)) {
                    int a = palabras.get(posicioPalabra(palabra)).getPesoTotal() + pesoContenido;
                    tendencia.setPesoTotal(a);
                    palabras.set(posicioPalabra(palabra), tendencia);
                }
            }
        } else {
            tendencia = new Palabra();
            tendencia.setPalabra(palabra);
            for (int i = 0; i < arregloT.length; i++) {
                if (arregloT[i].equals(palabra)) {
                    System.out.println(arregloT[i]);
                    tendencia.setPesoTotal(tendencia.getPesoTotal() + pesoTitulo);
                }
            }
            for (int i = 0; i < arregloT.length; i++) {
                if (arregloC[i].equals(palabra)) {
                    System.out.println(arregloT[i]);
                    tendencia.setPesoTotal(tendencia.getPesoTotal() + pesoTitulo);
                }
            }
            palabras.add(tendencia);
        }
        
        Palabra temp = new Palabra();
        for (int i = 0; i < palabras.size(); i++) {
            if (palabras.get(i).getPesoTotal() > temp.getPesoTotal()) {
                temp = palabras.get(i);
            }
        }

        return temp;

    }

    public static boolean estaPalabra(String palabra) {
        for (int i = 0; i < palabras.size(); i++) {
            if (palabras.get(i).getPalabra().equals(palabra)) {
                return true;
            }
        }
        return false;
    }

    private static int posicioPalabra(String palabra) {
        for (int i = 0; i < palabras.size(); i++) {
            if (palabras.get(i).getPalabra().equals(palabra)) {
                return i;
            }
        }
        return 0;
    }
}

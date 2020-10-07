/*
* Clase: ControrScrapeo
*
* Version: 1.0
* 
* Fecha de creación: 2020-09-04
*
* Autor: Angie Benavides y Deicy León
 */
package cecar.edu.controlador;

import cecar.edu.modelo.Articulo;
import cecar.edu.vista.Scrapear;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.validator.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ControlScrapeo {

    public static ArrayList<Articulo> articulos = new ArrayList<Articulo>();

    public static void scrapearSitioEspectador(String url) {
        try {
            Document documentPrincipal = Jsoup.connect(url).get();
            Elements elementsArchi = documentPrincipal.select("a[href]");

            for (org.jsoup.nodes.Element text : elementsArchi) {
                String urlArti = text.attr("href");
                String titulo = text.text();

                if (urlValidator(urlArti) || titulo.equals("")) {

                } else {

                    System.out.println("Url: " + url + urlArti);
                    if (urlArti.contains("/")) {
                        Articulo art = new Articulo();

                        Document documentArticulo = Jsoup.connect(url + urlArti).get();
                        Elements elementoTitulo = documentArticulo.select("h1.Article-Title");

                        for (Element textTitulo : elementoTitulo) {
                            String urltitulo = textTitulo.text();
                            art.setTitulo(urltitulo);
                            System.out.println("TITULO: " + urltitulo);

                        }
                        Elements elementoFecha = documentArticulo.select("time.Article-Time");
                        for (Element textFecha : elementoFecha) {
                            String fecha = formatoFecha(textFecha.text());
                            art.setFechaDePublicacion(fecha);
                            System.out.println("Fecha: " + textFecha.text());
                            System.out.println("Fecha: " + fecha);

                        }
                        Elements elementoContenido = documentArticulo.select("p.font--secondary");
                        for (Element textContenido : elementoContenido) {
                            art.setContenido(textContenido.text());
                            System.out.println("Contenido: " + textContenido.text());
                        }
                        if (!art.getTitulo().equals("")) {
                            
                            String validoRegistroBD =ControladorPersistencia.guardarArticulo(art);
                            if(validoRegistroBD.equals("OK")){
                              articulos.add(art);  
                            }
                        }

                    }
                    // System.out.println("url:" + url+urlArti);
                    // System.out.println("titullo:" + titulo);

                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error, verifique su conexion a internet");

            System.out.println("Titulo: " + articulos.size());
        }

        //este for es para verificar lo guardado en ArrayList articulos
        for (int i = 0; i < articulos.size(); i++) {
            try {
                System.out.println("Titulo: " + articulos.get(i).getTitulo());
                System.out.println("Fecha: " + articulos.get(i).getFechaDePublicacion());
                for (int a = 0; a < articulos.get(i).getContenido().size(); a++) {
                    System.out.println("Contenido: " + articulos.get(i).getContenido().get(a));
                }
            } catch (IndexOutOfBoundsException e) {

            }
        }

    }

    public static void scrapearSitioElNuevoSiglo(String url) {
        try {
            Document documentPrincipal = Jsoup.connect(url).get();
            Elements elementsArchi = documentPrincipal.select("a[href]");

            for (org.jsoup.nodes.Element text : elementsArchi) {
                String urlArti = text.attr("href");
                String titulo = text.text();

                if (urlValidator(urlArti) || titulo.equals("")) {

                } else {

                    if (urlArti.contains("/") && urlArti.contains("-") && contieneNumero(urlArti)) {
                        Articulo art = new Articulo();
                        System.out.println("Url: " + url + urlArti);
                        Document documentArticulo = Jsoup.connect(url + urlArti).get();

                        Elements elementoTitulo = documentArticulo.select("span.field.field--name-title.field--type-string.field--label-hidden");

                        for (Element textTitulo : elementoTitulo) {
                            String urltitulo = textTitulo.text();
                            art.setTitulo(urltitulo);
                            System.out.println("TITULO: " + urltitulo);

                        }
                        Elements elementoFecha = documentArticulo.select("div.info-line");
                        for (Element textFecha : elementoFecha) {
                            String fecha = formatoFecha(textFecha.text());
                            art.setFechaDePublicacion(fecha);
                            System.out.println("Fecha: " + textFecha.text());
                            //System.out.println("Fecha: " + fecha);

                        }
                        Elements elementoContenido = documentArticulo.select("p");
                        for (Element textContenido : elementoContenido) {
                            art.setContenido(textContenido.text());
                            System.out.println("Contenido: " + textContenido.text());
                        }
                        if (!art.getTitulo().equals("")) {
                            articulos.add(art);
                            ControladorPersistencia.guardarArticulo(art);
                        }/**/

                    }
                   

                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error, verifique su conexion a internet");

            System.out.println("Titulo: " + articulos.size());
        }

        //este for es para verificar lo guardado en ArrayList articulos
        for (int i = 0; i < articulos.size(); i++) {
            try {
                System.out.println("Titulo: " + articulos.get(i).getTitulo());
                System.out.println("Fecha: " + articulos.get(i).getFechaDePublicacion());
                for (int a = 0; a < articulos.get(i).getContenido().size(); a++) {
                    System.out.println("Contenido: " + articulos.get(i).getContenido().get(a));
                }
            } catch (IndexOutOfBoundsException e) {

            }
        }
    }

    public static boolean contieneNumero(String cadena) {
        boolean a = false;

        char[] valor1 = cadena.toCharArray();
        for (int i = 0; i < valor1.length; i++) {
            if (Character.isDigit(valor1[i])) {
                a = true;
            }
        }
        return a;
    }

    //Funcion para verificar la sintaxis de una url
    public static boolean urlValidator(String url) {
        //validación de url

        UrlValidator defaultValidator = new UrlValidator();
        return defaultValidator.isValid(url);
    }

    private static String formatoFecha(String text) {
        String cadena[] = text.split(" ");

        String dia = "";
        String mes = "";
        String anio = "";

        String cadenaSalida = "";
        if (cadena.length == 7) {

            for (int i = 0; i < 3; i++) {
                if (contieneNumero(cadena[i]) && i == 0) {
                    dia = cadena[i];
                }
                if (cadena[i].toLowerCase().contains("sep") && i == 1) {
                    mes = "09";
                } else {
                    if (cadena[i].toLowerCase().contains("en") && i == 1) {
                        mes = "01";
                    } else {
                        if (cadena[i].toLowerCase().contains("feb") && i == 1) {
                            mes = "02";
                        } else {
                            if (cadena[i].toLowerCase().contains("mar") && i == 1) {
                                mes = "03";
                            } else {

                                if (cadena[i].toLowerCase().contains("abr") && i == 1) {
                                    mes = "04";
                                } else {
                                    if (cadena[i].toLowerCase().contains("may") && i == 1) {
                                        mes = "05";
                                    } else {
                                        if (cadena[i].toLowerCase().contains("jun") && i == 1) {
                                            mes = "06";
                                        } else {
                                            if (cadena[i].toLowerCase().contains("jul") && i == 1) {
                                                mes = "07";
                                            } else {
                                                if (cadena[i].toLowerCase().contains("ago") && i == 1) {
                                                    mes = "08";
                                                } else {
                                                    if (cadena[i].toLowerCase().contains("nov") && i == 1) {
                                                        mes = "11";
                                                    } else {
                                                        if (cadena[i].toLowerCase().contains("dic") && i == 1) {
                                                            mes = "12";
                                                        } else {
                                                            if (cadena[i].toLowerCase().contains("oct") && i == 1) {
                                                                mes = "10";
                                                            }
                                                        }
                                                    }
                                                }
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (contieneNumero(cadena[i]) && i == 2) {
                    anio = cadena[i];
                }
                cadenaSalida = mes + "/" + dia + "/" + anio;
            }
        } else {
            if (cadena.length == 6) {
                for (int i = 0; i < 3; i++) {
                    if (cadena[i].toLowerCase().contains("ene") && i == 0) {
                        mes = "1";
                    } else {
                        if (cadena[i].toLowerCase().contains("feb") && i == 0) {
                            mes = "2";
                        } else {
                            if (cadena[i].toLowerCase().contains("mar") && i == 0) {
                                mes = "3";
                            } else {
                                if (cadena[i].toLowerCase().contains("abr") && i == 0) {
                                    mes = "4";
                                } else {
                                    if (cadena[i].toLowerCase().contains("may") && i == 0) {
                                        mes = "5";
                                    } else {
                                        if (cadena[i].toLowerCase().contains("jun") && i == 0) {
                                            mes = "6";
                                        } else {
                                            if (cadena[i].toLowerCase().contains("jul") && i == 0) {
                                                mes = "7";
                                            } else {
                                                if (cadena[i].toLowerCase().contains("ago") && i == 0) {
                                                    mes = "8";
                                                } else {
                                                    if (cadena[i].toLowerCase().contains("sep") && i == 0) {
                                                        mes = "9";
                                                    } else {
                                                        if (cadena[i].toLowerCase().contains("oct") && i == 0) {
                                                            mes = "10";
                                                        } else {
                                                            if (cadena[i].toLowerCase().contains("nov") && i == 0) {
                                                                mes = "11";
                                                            } else {
                                                                if (cadena[i].toLowerCase().contains("dic") && i == 0) {
                                                                    mes = "12";
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(cadena[i].contains(",")&&i==1){
                        dia=cadena[i].replaceAll("[,]", "");
                    }
                    if(contieneNumero(cadena[i])&&i==2){
                        anio=cadena[i];
                    }
                    cadenaSalida = mes + "/" + dia + "/" + anio;
                }
            } else {
                Calendar fecha = new GregorianCalendar();
                int anioAct = fecha.get(Calendar.YEAR);
                int mesAct = fecha.get(Calendar.MONTH) + 1;
                int diaAct = fecha.get(Calendar.DAY_OF_MONTH);
                cadenaSalida = mesAct + "/" + diaAct + "/" + anioAct;
            }
        }

        return cadenaSalida;
    }

}

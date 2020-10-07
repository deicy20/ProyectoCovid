/*
* Clase: ControladorTendenciasTest
*
* Version: 1.0
* 
* Fecha de creación: 2020-09-09
*
* Autor: Angie Benavides y Deicy León
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecar.edu.controlador;

import static cecar.edu.controlador.ControladorTendecias.tendencia;
import cecar.edu.modelo.Palabra;
import org.junit.Test;
import static org.junit.Assert.*;


public class ControladorTendeciasTest {
    
   
    /**
     * Test of tendencia method, of class ControladorTendecias.
     */
    @Test
    public void testTendencia() {


        //Prueba para comprobar que se cree un objeto de tipo Palabra
            try {
             Palabra c =  tendencia("Yo amo cecar","Hola soy un texto","jhbfhhf",10,5);
             assertNotNull(c);
              
             assertTrue("Si se creó",c instanceof Palabra);
            } catch (Exception e) {
                fail("No  pasó la prueba");
            }

    }

   
}

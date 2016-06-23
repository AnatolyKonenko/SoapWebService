/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws14;

import javax.xml.ws.Endpoint;
import ws14.publish.WareHouse;

/**
 *
 * @author Linea
 */
public class WS14 {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        Endpoint.publish("http://localhost:8078/warehouse", new WareHouse());
    }
    
}

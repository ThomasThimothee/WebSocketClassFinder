/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websockettestweb;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thomasthimothee
 */
public class main {
    public static void main(String[] args) {
        try {
            MyClassFinder cf = new MyClassFinder();
        } catch (InstantiationException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   
}

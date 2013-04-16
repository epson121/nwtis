/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.web;

import javax.servlet.ServletException;

/**
 *
 * @author nwtis_2
 */
public class NeuspjesnaPrijava extends ServletException {

    public NeuspjesnaPrijava(String message) {
        super("NWTIS: " + message);
    }
    
}

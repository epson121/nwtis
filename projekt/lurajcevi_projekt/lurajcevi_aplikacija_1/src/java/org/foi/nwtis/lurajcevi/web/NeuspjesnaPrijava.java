
package org.foi.nwtis.lurajcevi.web;

import javax.servlet.ServletException;

/**
 * @document NeuspjesnaPrijava
 * @author Luka Rajcevic
 */
public class NeuspjesnaPrijava extends ServletException {

    public NeuspjesnaPrijava(String message) {
        super("NWTIS: " + message);
    }
    
}

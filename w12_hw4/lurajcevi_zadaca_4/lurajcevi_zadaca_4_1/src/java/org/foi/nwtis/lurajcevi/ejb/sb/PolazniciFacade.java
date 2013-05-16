/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.ejb.sb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.foi.nwtis.lurajcevi.ejb.eb.Polaznici;

/**
 *
 * @author nwtis_2
 */
@Stateless
public class PolazniciFacade extends AbstractFacade<Polaznici> {
    @PersistenceContext(unitName = "lurajcevi_zadaca_4_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PolazniciFacade() {
        super(Polaznici.class);
    }
    
}

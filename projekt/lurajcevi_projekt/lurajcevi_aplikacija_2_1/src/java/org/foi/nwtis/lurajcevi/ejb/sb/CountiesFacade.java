
package org.foi.nwtis.lurajcevi.ejb.sb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.foi.nwtis.lurajcevi.ejb.eb.Counties;

/**
 * @document CountiesFacade
 * @author Luka Rajcevic
 */
@Stateless
public class CountiesFacade extends AbstractFacade<Counties> {
    @PersistenceContext(unitName = "lurajcevi_aplikacija_2_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CountiesFacade() {
        super(Counties.class);
    }

}

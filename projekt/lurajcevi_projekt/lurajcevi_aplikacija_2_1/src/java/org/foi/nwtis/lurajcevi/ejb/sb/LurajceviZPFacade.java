
package org.foi.nwtis.lurajcevi.ejb.sb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.foi.nwtis.lurajcevi.ejb.eb.LurajceviZP;

/**
 * @document LurajceviZPFacade
 * @author Luka Rajcevic
 */
@Stateless
public class LurajceviZPFacade extends AbstractFacade<LurajceviZP> {
    @PersistenceContext(unitName = "lurajcevi_aplikacija_2_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LurajceviZPFacade() {
        super(LurajceviZP.class);
    }

}

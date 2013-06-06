
package org.foi.nwtis.lurajcevi.ejb.sb;

import java.util.List;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.foi.nwtis.lurajcevi.ejb.eb.LurajceviKorisnici;
import org.foi.nwtis.lurajcevi.ejb.eb.LurajceviPortfolio;

/**
 * @document LurajceviPortfolioFacade
 * @author Luka Rajcevic
 */
@Stateless
public class LurajceviPortfolioFacade extends AbstractFacade<LurajceviPortfolio> {
    @PersistenceContext(unitName = "lurajcevi_aplikacija_2_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LurajceviPortfolioFacade() {
        super(LurajceviPortfolio.class);
    }
    
    public List<LurajceviPortfolio> dohvatiPortfolie(String ime){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<LurajceviPortfolio> podaci = cq.from(LurajceviPortfolio.class);
        cq.select(podaci).where(cb.equal(podaci.get("vlasnik"), ime));
        return em.createQuery(cq).getResultList();
    }
    
    
    
         //TODO big transaction
         //TODO update portfolio and zp table
         /*
        LurajceviPortfolio lp = new LurajceviPortfolio();
        lp.setNaziv(lozinka);
        lp.setVlasnik(null);
        em.persist(lk);
        * */
    

}

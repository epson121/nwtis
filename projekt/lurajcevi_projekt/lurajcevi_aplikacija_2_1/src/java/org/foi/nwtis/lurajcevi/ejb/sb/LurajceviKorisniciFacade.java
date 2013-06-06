
package org.foi.nwtis.lurajcevi.ejb.sb;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.foi.nwtis.lurajcevi.ejb.eb.LurajceviKorisnici;

/**
 * @document LurajceviKorisniciFacade
 * @author Luka Rajcevic
 */
@Stateless
public class LurajceviKorisniciFacade extends AbstractFacade<LurajceviKorisnici> {
    @PersistenceContext(unitName = "lurajcevi_aplikacija_2_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LurajceviKorisniciFacade() {
        super(LurajceviKorisnici.class);
    }
    
    public void dodajKorisnika(String ime, String lozinka) throws NamingException{
        LurajceviKorisnici lk = new LurajceviKorisnici();
        lk.setIme(ime);
        lk.setLozinka(lozinka);
        lk.setTip(1);
        em.persist(lk);
    }
    
    public boolean provjeriAdministratora(String ime){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<LurajceviKorisnici> korisnici = cq.from(LurajceviKorisnici.class);
        cq.select(korisnici).where(cb.and(cb.equal(korisnici.get("ime"), ime), 
                                          cb.equal(korisnici.get("tip"), 0)
                ));
        if (em.createQuery(cq).getResultList().isEmpty())
            return false;
        else
            return true;
    }
    
    public boolean provjeriKorisnika(String ime){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<LurajceviKorisnici> korisnici = cq.from(LurajceviKorisnici.class);
        cq.select(korisnici).where(cb.equal(korisnici.get("ime"), ime));
        if (em.createQuery(cq).getResultList().isEmpty())
            return false;
        else
            return true;
    }
}

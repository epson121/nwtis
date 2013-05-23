
package org.foi.nwtis.lurajcevi.ejb.sb;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javassist.expr.Cast;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.foi.nwtis.lurajcevi.ejb.eb.ZipCodes;

/**
 *
 * @author nwtis_2
 */
@Stateless
public class ZipCodesFacade extends AbstractFacade<ZipCodes> {
    @PersistenceContext(unitName = "lurajcevi_zadaca_4_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ZipCodesFacade() {
        super(ZipCodes.class);
    }
    
   /**
    * Filtrira zip kodove prema državama i gradovima 
    * @param data - popis drzava i gradova za filtriranje
    * @return rezultate upita
    */
    public List<ZipCodes> filtrirajZipove(Set<String> data){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<ZipCodes> zipovi = cq.from(ZipCodes.class);
        cq.select(zipovi);
        List<String> gradovi = new ArrayList<String>();
        List<String> drzave = new ArrayList<String>();
        for (String s : data){
            gradovi.add(s.split("-")[2].trim());
            drzave.add(s.split("-")[0].trim());
        }
        cq.where(cb.and(zipovi.<String>get("cities").<String>get("citiesPK").<String>get("city").in(gradovi), 
                        zipovi.<String>get("cities").<String>get("citiesPK").<String>get("state").in(drzave)));
        return em.createQuery(cq).getResultList();
    }
    
    /**
     * Filtrira zip kodove prema državama i gradovima i dodatnim parametrom za filtriranje
     * @param data - popis drzava i gradova za filtriranje
     * @param filter - dodatni filter
     * @return rezultate upita
     */
     public List<ZipCodes> filtrirajZipove(Set<String> data, String filter){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<ZipCodes> zipovi = cq.from(ZipCodes.class);
        cq.select(zipovi);
        List<String> gradovi = new ArrayList<String>();
        List<String> drzave = new ArrayList<String>();
        for (String s : data){
            gradovi.add(s.split("-")[2].trim());
            drzave.add(s.split("-")[0].trim());
        }
        cq.where(cb.and(
                        zipovi.<String>get("cities").<String>get("citiesPK").<String>get("city").in(gradovi), 
                        zipovi.<String>get("cities").<String>get("citiesPK").<String>get("state").in(drzave), 
                        cb.like(zipovi.<String>get("zip"), filter + "%")
                       )
                );
        return em.createQuery(cq).getResultList();
    }
     
     public List<ZipCodes> filtrirajZipoveTest(Set<String> data, String filter){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<ZipCodes> zipovi = cq.from(ZipCodes.class);
        cq.select(zipovi.<String>get("zip"));
        return em.createQuery(cq).getResultList();
    }
     
      
    
}

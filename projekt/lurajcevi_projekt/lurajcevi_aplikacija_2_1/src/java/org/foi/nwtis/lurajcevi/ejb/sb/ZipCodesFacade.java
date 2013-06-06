
package org.foi.nwtis.lurajcevi.ejb.sb;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.foi.nwtis.lurajcevi.ejb.eb.ZipCodes;

/**
 * @document ZipCodesFacade
 * @author Luka Rajcevic
 */
@Stateless
public class ZipCodesFacade extends AbstractFacade<ZipCodes> {
    @PersistenceContext(unitName = "lurajcevi_aplikacija_2_1PU")
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
        int size = filter.length();
        String triMin = filter;
        String cetiriMin = filter;
        String petMin = filter;
        String triMax = filter;
        String cetiriMax = filter;
        String petMax = filter;
        for (String s : data){
            gradovi.add(s.split("-")[2].trim());
            drzave.add(s.split("-")[0].trim());
        }
        while (size != 5){
            petMin += "0";
            petMax += "9";
            if (size < 4){
                cetiriMin += "0";
                cetiriMax += "9";
            }
            if (size < 3){
                triMin += "0";
                triMax += "9";
            }
            size++;
        }
        cq.where(cb.and(
                        zipovi.<String>get("cities").<String>get("citiesPK").<String>get("city").in(gradovi), 
                        zipovi.<String>get("cities").<String>get("citiesPK").<String>get("state").in(drzave),
                        cb.or(cb.between(zipovi.<Integer>get("zip"), Integer.parseInt(triMin), Integer.parseInt(triMax)),
                        cb.between(zipovi.<Integer>get("zip"), Integer.parseInt(cetiriMin), Integer.parseInt(cetiriMax)),
                        cb.between(zipovi.<Integer>get("zip"), Integer.parseInt(petMin), Integer.parseInt(petMax)))
                       )
                );
        return em.createQuery(cq).getResultList();
    } 

}

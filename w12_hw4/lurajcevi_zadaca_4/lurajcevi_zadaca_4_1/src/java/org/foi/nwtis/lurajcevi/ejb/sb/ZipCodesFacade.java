
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
        /*
        String triMin = filter;
        String cetiriMin = filter;
        String petMin = filter;
        String triMax = filter;
        String cetiriMax = filter;
        String petMax = filter;
        while (filter.length() != 5){
            petMin += "0";
            if (filter.length() < 4)
                cetiriMin += "0";
            if (filter.length() < 3)
                triMin += "0";
        }
        while (filter.length() != 5){
            petMax += "9";
            if (filter.length() < 4)
                cetiriMax += "9";
            if (filter.length() < 3)
                triMax += "9";
        }
        for (String s : data){
            gradovi.add(s.split("-")[2].trim());
            drzave.add(s.split("-")[0].trim());
        }
         System.out.println("TRI MIN " + triMin );
         System.out.println("TRI MAX " + triMax );
         System.out.println("CETIRI MIN " + cetiriMin );
         System.out.println("CETIRI MAX " + cetiriMax );
         System.out.println("PET MIN " + petMin );
         System.out.println("PET MAX " + petMax );
         */ 
        cq.where(cb.and(
                        zipovi.<String>get("cities").<String>get("citiesPK").<String>get("city").in(gradovi), 
                        zipovi.<String>get("cities").<String>get("citiesPK").<String>get("state").in(drzave)
                        /*
                        cb.between(zipovi.<Integer>get("zip"), Integer.parseInt(triMin), Integer.parseInt(triMax)),
                        cb.between(zipovi.<Integer>get("zip"), Integer.parseInt(cetiriMin), Integer.parseInt(cetiriMax)),
                        cb.between(zipovi.<Integer>get("zip"), Integer.parseInt(petMin), Integer.parseInt(petMax))
                        **/
                       )
                );
        return em.createQuery(cq).getResultList();
    } 
    
}

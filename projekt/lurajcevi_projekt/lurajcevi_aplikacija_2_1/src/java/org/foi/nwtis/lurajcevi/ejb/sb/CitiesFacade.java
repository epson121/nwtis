
package org.foi.nwtis.lurajcevi.ejb.sb;

import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.foi.nwtis.lurajcevi.ejb.eb.Cities;

/**
 * @document CitiesFacade
 * @author Luka Rajcevic
 */
@Stateless
public class CitiesFacade extends AbstractFacade<Cities> {
    @PersistenceContext(unitName = "lurajcevi_aplikacija_2_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitiesFacade() {
        super(Cities.class);
    }
    
    /**
     * filtrira gradove prema državama
     * @param drzava popis drzava za filtriranje
     * @return rezultate upita
     */
    public List<Cities> filtrirajGradove(Set<String> drzava){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Cities> gradovi = cq.from(Cities.class);
        cq.select(gradovi);
        cq.where(gradovi.<String>get("citiesPK").<String>get("state").in(drzava));
        return em.createQuery(cq).getResultList();
    }
    
    /**
     * filtrira gradove prema državama koristeći LIKE operator
     * @param drzava - popis drzava za filtriranje
     * @param filterGradova - dodatan filter za LIKE operator
     * @return rezultate upita
     */
    public List<Cities> filtrirajGradove(Set<String> drzava, String filterGradova){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Cities> gradovi = cq.from(Cities.class);
        cq.select(gradovi);
        cq.where(cb.and(gradovi.<String>get("citiesPK").<String>get("state").in(drzava), 
                 cb.like(gradovi.<String>get("name"), filterGradova + "%")));
        return em.createQuery(cq).getResultList();
    }
    
}

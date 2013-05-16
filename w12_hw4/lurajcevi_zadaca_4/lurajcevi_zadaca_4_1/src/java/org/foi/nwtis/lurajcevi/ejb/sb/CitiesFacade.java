/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.foi.nwtis.lurajcevi.ejb.eb.States;

/**
 *
 * @author nwtis_2
 */
@Stateless
public class CitiesFacade extends AbstractFacade<Cities> {
    @PersistenceContext(unitName = "lurajcevi_zadaca_4_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitiesFacade() {
        super(Cities.class);
    }
    
    public List<Cities> filtrirajGradove(Set<String> drzava){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Cities> gradovi = cq.from(Cities.class);
        cq.select(gradovi);
        cq.where(gradovi.<String>get("citiesPK").<String>get("state").in(drzava));
        return em.createQuery(cq).getResultList();
    }
    
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

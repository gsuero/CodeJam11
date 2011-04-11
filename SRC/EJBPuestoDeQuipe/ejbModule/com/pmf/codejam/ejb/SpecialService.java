package com.pmf.codejam.ejb;

import javax.ejb.*;
import javax.persistence.*;

import com.pmf.codejam.entity.Special;
import com.pmf.codejam.exception.*;
import com.pmf.codejam.util.JPAUtil;
import java.util.*;

/**
 * 
 * @author Frederick Clark
 *
 */
@Stateless(name="SpecialService", mappedName="ejb/SpecialServiceJNDI")
public class SpecialService implements SpecialServiceLocal {
	@PersistenceUnit(name="PuestoDeQuipeService")
	public EntityManager em;

    public SpecialService() {
    	em = new JPAUtil().getEMF().createEntityManager();
    }

    public void create(Special special) {
        try {
            em.getTransaction().begin();
            em.persist(special);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    
    public void edit(Special special) throws IllegalOrphanException, SpecialException, Exception {
        try {
            em.getTransaction().begin();
            special = em.merge(special);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = special.getId();
                if (findSpecial(id) == null) {
                    throw new SpecialException("The special with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }    
    
    
    public void destroy(Long id) throws IllegalOrphanException, SpecialException {

           try {
               em.getTransaction().begin();
               Special specials;
               try {
                   specials = em.getReference(Special.class, id);
                   specials.getId();
               } catch (EntityNotFoundException enfe) {
                   throw new SpecialException("The special with id " + id + " no longer exists.", enfe);
               }
               em.remove(specials);
               em.getTransaction().commit();
           } finally {
               if (em != null) {
                   em.close();
               }
           }
    }

    public List<Special> findSpecials() {
        return findSpecials(true, -1, -1);
    }

    public List<Special> findSpecials(int maxResults, int firstResult) {
        return findSpecials(false, maxResults, firstResult);
    }

    @SuppressWarnings("unchecked")
	private List<Special> findSpecials(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createNamedQuery("Special.findAll");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Special findSpecial(Long id) {
        try {
            return em.find(Special.class, id);
        } finally {
            em.close();
        }
    }

    public int getSpecialsCount() {
        try {
            Query q = em.createQuery("Special.count");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
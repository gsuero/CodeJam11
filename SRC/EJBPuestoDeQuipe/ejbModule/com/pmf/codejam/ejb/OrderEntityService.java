package com.pmf.codejam.ejb;

import javax.ejb.*;
import javax.persistence.*;

import com.pmf.codejam.entity.OrderDetail;
import com.pmf.codejam.entity.OrderEntity;
import com.pmf.codejam.exception.*;
import com.pmf.codejam.util.JPAUtil;
import java.util.*;

/**
 * 
 * @author Frederick Clark
 *
 */
@Stateless(name="OrderEntityService", mappedName="ejb/OrderEntityServiceJNDI")
public class OrderEntityService implements OrderEntityServiceLocal {
	@PersistenceUnit(name="PuestoDeQuipeService")
	public EntityManager em;

    public OrderEntityService() {
    	em = new JPAUtil().getEMF().createEntityManager();
    }

    public void create(OrderEntity order) {

        try {
            em.getTransaction().begin();
            Set<OrderDetail> attachedOrderDetailSet = new HashSet<OrderDetail>();
            for (OrderDetail orderDetailSetOrderDetailToAttach : order.getOrderDetails()) {
                orderDetailSetOrderDetailToAttach = em.getReference(orderDetailSetOrderDetailToAttach.getClass(), orderDetailSetOrderDetailToAttach.getOrderDetailPK());
                attachedOrderDetailSet.add(orderDetailSetOrderDetailToAttach);
            }
            order.setOrderDetails(attachedOrderDetailSet);
            em.persist(order);
            for (OrderDetail orderDetailSetOrderDetail : order.getOrderDetails()) {
                OrderEntity oldOrdersOfOrderDetailSetOrderDetail = orderDetailSetOrderDetail.getOrderEntity();
                orderDetailSetOrderDetail.setOrderEntity(order);
                orderDetailSetOrderDetail = em.merge(orderDetailSetOrderDetail);
                if (oldOrdersOfOrderDetailSetOrderDetail != null) {
                    oldOrdersOfOrderDetailSetOrderDetail.getOrderDetails().remove(orderDetailSetOrderDetail);
                    oldOrdersOfOrderDetailSetOrderDetail = em.merge(oldOrdersOfOrderDetailSetOrderDetail);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    
    public void edit(OrderEntity order) throws IllegalOrphanException, OrderEntityException, Exception {
        try {
            em.getTransaction().begin();
            OrderEntity persistentOrders = em.find(OrderEntity.class, order.getOrderNo());
            Set<OrderDetail> orderDetailSetOld = persistentOrders.getOrderDetails();
            Set<OrderDetail> orderDetailSetNew = order.getOrderDetails();
            List<String> illegalOrphanMessages = null;
            for (OrderDetail orderDetailSetOldOrderDetail : orderDetailSetOld) {
                if (!orderDetailSetNew.contains(orderDetailSetOldOrderDetail)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OrderDetail " + orderDetailSetOldOrderDetail + " since its orders field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<OrderDetail> attachedOrderDetailSetNew = new HashSet<OrderDetail>();
            for (OrderDetail orderDetailSetNewOrderDetailToAttach : orderDetailSetNew) {
                orderDetailSetNewOrderDetailToAttach = em.getReference(orderDetailSetNewOrderDetailToAttach.getClass(), orderDetailSetNewOrderDetailToAttach.getOrderDetailPK());
                attachedOrderDetailSetNew.add(orderDetailSetNewOrderDetailToAttach);
            }
            orderDetailSetNew = attachedOrderDetailSetNew;
            order.setOrderDetails(orderDetailSetNew);
            order = em.merge(order);
            for (OrderDetail orderDetailSetNewOrderDetail : orderDetailSetNew) {
                if (!orderDetailSetOld.contains(orderDetailSetNewOrderDetail)) {
                    OrderEntity oldOrdersOfOrderDetailSetNewOrderDetail = orderDetailSetNewOrderDetail.getOrderEntity();
                    orderDetailSetNewOrderDetail.setOrderEntity(order);
                    orderDetailSetNewOrderDetail = em.merge(orderDetailSetNewOrderDetail);
                    if (oldOrdersOfOrderDetailSetNewOrderDetail != null && !oldOrdersOfOrderDetailSetNewOrderDetail.equals(order)) {
                        oldOrdersOfOrderDetailSetNewOrderDetail.getOrderDetails().remove(orderDetailSetNewOrderDetail);
                        oldOrdersOfOrderDetailSetNewOrderDetail = em.merge(oldOrdersOfOrderDetailSetNewOrderDetail);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = order.getOrderNo();
                if (findOrder(id) == null) {
                    throw new OrderEntityException("The orders with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }    
    public void destroy(Integer id) throws IllegalOrphanException, OrderEntityException {
        try {
            em.getTransaction().begin();
            OrderEntity order;
            try {
                order = em.getReference(OrderEntity.class, id);
                order.getOrderNo();
            } catch (EntityNotFoundException enfe) {
                throw new OrderEntityException("The orders with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<OrderDetail> orderDetailSetOrphanCheck = order.getOrderDetails();
            for (OrderDetail orderDetailSetOrphanCheckOrderDetail : orderDetailSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Orders (" + order + ") cannot be destroyed since the OrderDetail " + orderDetailSetOrphanCheckOrderDetail + " in its orderDetailSet field has a non-nullable orders field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(order);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrderEntity> findOrders() {
        return findOrders(true, -1, -1);
    }

    public List<OrderEntity> findOrders(int maxResults, int firstResult) {
        return findOrders(false, maxResults, firstResult);
    }

    @SuppressWarnings("unchecked")
	private List<OrderEntity> findOrders(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createNamedQuery("OrderEntity.findAll");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public OrderEntity findOrder(Integer id) {
        try {
            return em.find(OrderEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdersCount() {
        try {
            Query q = em.createQuery("OrderEntity.count");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
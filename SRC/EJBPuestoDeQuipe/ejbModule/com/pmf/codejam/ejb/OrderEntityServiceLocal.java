package com.pmf.codejam.ejb;
import java.util.List;

import com.pmf.codejam.entity.OrderEntity;
import com.pmf.codejam.exception.*;
/**
 * 
 * @author Frederick
 *
 */
public interface OrderEntityServiceLocal {
    void create(OrderEntity order);
    void edit(OrderEntity order) throws IllegalOrphanException, OrderEntityException, Exception;
    void destroy(Integer id) throws IllegalOrphanException, OrderEntityException;
    List<OrderEntity> findOrders();
    List<OrderEntity> findOrders(int maxResults, int firstResult);
    OrderEntity findOrder(Integer id);
    int getOrdersCount();
}

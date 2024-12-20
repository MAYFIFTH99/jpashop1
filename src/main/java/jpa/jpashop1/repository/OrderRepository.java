package jpa.jpashop1.repository;

import jakarta.persistence.EntityManager;
import jpa.jpashop1.domain.Order;
import jpa.jpashop1.domain.OrderSearch;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {

    private final EntityManager em;

    public OrderRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch cond) {
        return em.createQuery(
                        "select o from Order o join o.member m where o.status = :status and m.name like :name"
                        , Order.class)
                .setParameter("status", cond.getOrderStatus())
                .setParameter("name", cond.getUsername())
                .setMaxResults(1000)
                .getResultList();
    }
}

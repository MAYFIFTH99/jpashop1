package jpa.jpashop1.service;

import jpa.jpashop1.domain.Delivery;
import jpa.jpashop1.domain.Member;
import jpa.jpashop1.domain.Order;
import jpa.jpashop1.domain.OrderItem;
import jpa.jpashop1.domain.item.Item;
import jpa.jpashop1.repository.ItemRepository;
import jpa.jpashop1.repository.MemberRepository;
import jpa.jpashop1.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final DataSourceTransactionManager transactionManager;


    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findById(memberId);
        Item item = itemRepository.findById(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    //검색
//    public List<Order> findOrders(OrderSearchCond cond){}

}

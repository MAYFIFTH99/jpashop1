package jpa.jpashop1.service;

import jakarta.persistence.EntityManager;
import jpa.jpashop1.domain.Address;
import jpa.jpashop1.domain.Member;
import jpa.jpashop1.domain.Order;
import jpa.jpashop1.domain.OrderStatus;
import jpa.jpashop1.domain.item.Book;
import jpa.jpashop1.domain.item.Item;
import jpa.jpashop1.eception.NotEnoughStockException;
import jpa.jpashop1.repository.ItemRepository;
import jpa.jpashop1.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired private EntityManager em;
    @Autowired private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;

    @Test
    void 상품주문() throws Exception {
        //given
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "경기", "123-123"));
        em.persist(member);

        Book book = new Book();
        book.setName("시골 JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), 3);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        //주문 상태
        Assertions.assertEquals(OrderStatus.ORDER, getOrder.getStatus());
        //주문 상품 종류 수
        assertThat(getOrder.getOrderItems().size()).isEqualTo(1);
        //주문 가격
        assertThat(getOrder.getTotalPrice()).isEqualTo(30000);
        //남은 재고
        assertThat(itemRepository.findById(book.getId()).getStockQuantity()).isEqualTo(7);
    }

    @Test
    void 주문취소() throws Exception {
        //given
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "경기", "123-123"));
        em.persist(member);

        Book book = new Book();
        book.setName("시골 JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);

        Long orderId = orderService.order(member.getId(), book.getId(), 3);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
    }

    @Test
    void 상문주문_재고수량초과() throws Exception {
        //given
        Member member = new Member();
        Item book = new Book();
        book.setName("book");
        book.setPrice(10000);
        book.setStockQuantity(10);

        em.persist(book);
        em.persist(member);

        //when
        //then
        assertThatThrownBy(() -> orderService.order(member.getId(), book.getId(), 11))
                .isInstanceOf(NotEnoughStockException.class);

    }


}
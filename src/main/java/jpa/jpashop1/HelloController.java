package jpa.jpashop1;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpa.jpashop1.domain.Address;
import jpa.jpashop1.domain.Member;
import jpa.jpashop1.domain.item.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class HelloController {

    @PersistenceContext
    private EntityManager em;

    @EventListener(value = ApplicationReadyEvent.class)
    @Transactional
    public void init() {
        Member member = new Member();
        member.setName("minseok");
        member.setAddress(new Address("city", "street", "zipcode"));
        em.persist(member);

        Book book = new Book();
        book.setName("book");
        book.setStockQuantity(100);
        book.setIsbn("1234");
        book.setPrice(10000);
        book.setAuthor("author");

        em.persist(book);
    }
}

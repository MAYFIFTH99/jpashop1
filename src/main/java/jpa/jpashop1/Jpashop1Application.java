package jpa.jpashop1;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpa.jpashop1.domain.Address;
import jpa.jpashop1.domain.Member;
import jpa.jpashop1.domain.item.Book;
import jpa.jpashop1.domain.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class Jpashop1Application {

    public static void main(String[] args) {
        SpringApplication.run(Jpashop1Application.class, args);
    }


    @Bean
    Hibernate5JakartaModule hibernate5Module() {
        return new Hibernate5JakartaModule();
    }

}

package jpa.jpashop1.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {


    private String username;
    private OrderStatus orderStatus;
}

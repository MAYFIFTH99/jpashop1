package jpa.jpashop1.repository.order.simplequery;

import jpa.jpashop1.domain.Address;
import jpa.jpashop1.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderSimpleQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime localDateTime;
    private OrderStatus orderStatus;
    private Address address;

    public OrderSimpleQueryDto(Long orderId, String name, LocalDateTime localDateTime, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.localDateTime = localDateTime;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}


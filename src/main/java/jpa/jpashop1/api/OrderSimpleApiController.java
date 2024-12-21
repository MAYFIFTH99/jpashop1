package jpa.jpashop1.api;


import jpa.jpashop1.domain.Address;
import jpa.jpashop1.domain.Order;
import jpa.jpashop1.domain.OrderSearch;
import jpa.jpashop1.domain.OrderStatus;
import jpa.jpashop1.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * XxxToOne 관계에서의 성능 최적화 방법
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> orddersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public Result ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());

        List<SimpleOrderDtoV2> orderDtos = orders.stream().map(m -> new SimpleOrderDtoV2(m.getId(), m.getMember().getName(), m.getOrderDate(), m.getStatus(), m.getDelivery().getAddress())).toList();
        return new Result(orderDtos);
    }

    @GetMapping("/api/v3/simple-orders")
    public Result orderV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDtoV3> ordersDto = orders.stream().map(SimpleOrderDtoV3::new).toList();
        return new Result(ordersDto);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class SimpleOrderDtoV2 {
        private Long orderId;
        private String name;
        private LocalDateTime localDateTime;
        private OrderStatus orderStatus;
        private Address address;
    }

    @Data
    static class SimpleOrderDtoV3 {
        private Long orderId;
        private String name;
        private LocalDateTime localDateTime;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDtoV3(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            localDateTime = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
        }
    }
}

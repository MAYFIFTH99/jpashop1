package jpa.jpashop1.repository.order.query;

import lombok.Data;

@Data
public class OrderItemQueryDto {

    private Long orderId;
    private String itemName;
    private int orderPrice;
    private int count;

    public OrderItemQueryDto(Long itemId, String itemName, int orderPrice, int count) {
        this.orderId = itemId;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}

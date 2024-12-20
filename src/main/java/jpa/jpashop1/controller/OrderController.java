package jpa.jpashop1.controller;

import jpa.jpashop1.domain.Member;
import jpa.jpashop1.domain.item.Item;
import jpa.jpashop1.service.ItemService;
import jpa.jpashop1.service.MemberService;
import jpa.jpashop1.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<Member> members = memberService.getMembers();
        List<Item> items = itemService.findAll();

        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }
    
    @GetMapping("/orders")
    public String list(Model model) {

        return "order/orderList";
    }
}

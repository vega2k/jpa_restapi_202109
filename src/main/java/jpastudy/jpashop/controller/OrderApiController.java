package jpastudy.jpashop.controller;

import jpastudy.jpashop.domain.Order;
import jpastudy.jpashop.domain.OrderItem;
import jpastudy.jpashop.repository.OrderRepository;
import jpastudy.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * xToMany(OneToMany) 관계 최적화
 * Order, Order -> OrderItem -> Item
 */
@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderRepository orderRepository;

    /**
     * V1. 엔티티 직접 노출
     * 엔티티가 변하면 API 스펙이 변한다.
     * Hibernate5Module 모듈 등록, LAZY=null 처리
     * 양방향 관계 문제 발생 -> @JsonIgnore
     */
    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByQuerydsl(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName(); //Lazy 강제 초기화
            order.getDelivery().getAddress(); //Lazy 강제 초기화
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.forEach(o -> o.getItem().getName()); //Lazy 강제초기화
        }
        return all;
    }
}

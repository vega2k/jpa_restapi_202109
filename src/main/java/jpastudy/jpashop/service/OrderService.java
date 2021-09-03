package jpastudy.jpashop.service;

import jpastudy.jpashop.domain.*;
import jpastudy.jpashop.domain.item.Item;
import jpastudy.jpashop.repository.ItemRepository;
import jpastudy.jpashop.repository.MemberRepository;
import jpastudy.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    //주문처리
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        //Member 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        //Item 엔티티 조회
        Item item = itemRepository.findOne(itemId);
        //Delivery 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);
        //OrderItem 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        //Order 생성
        Order order = Order.createOrder(member, delivery, orderItem);
        //Order 저장
        orderRepository.save(order);
        return order.getId();
    }

}

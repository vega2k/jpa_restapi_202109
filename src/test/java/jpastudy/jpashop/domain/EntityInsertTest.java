package jpastudy.jpashop.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class EntityInsertTest {
    @Autowired
    EntityManager em;

    @Test
    @Rollback(value = false)
    public void insert() throws Exception {
        Member member = new Member();
        member.setName("부트");
        Address address = new Address("서울", "11번지", "05401");
        member.setAddress(address);
        em.persist(member);
    }
}

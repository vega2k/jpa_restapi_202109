package jpastudy.jpashop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    public void user() {
        User user = new User();
        user.setName("boot");
        assertEquals(user.getName(),"boot");
    }
}
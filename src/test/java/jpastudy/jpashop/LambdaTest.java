package jpastudy.jpashop;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LambdaTest {
    @Test
    public void iterable() {
        List<User> users = List.of(new User("스프링"), new User("테스트"));
        //Consumer 의 void accept(T t) 메서드 재정의
        users.forEach(user -> System.out.println(user));
    }

    @Test @Disabled
    public void lambda() {
        //Anonymous Inner class
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("익명 내부클래스로 구현");
            }
        });
        t1.start();

        //Lambda Expression
        Thread t2 = new Thread(() -> System.out.println("람다식"));
        t2.start();

    }
}

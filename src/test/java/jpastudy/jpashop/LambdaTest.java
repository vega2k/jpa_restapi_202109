package jpastudy.jpashop;

import org.junit.jupiter.api.Test;

public class LambdaTest {
    @Test
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


    }
}

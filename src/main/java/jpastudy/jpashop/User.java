package jpastudy.jpashop;

import lombok.*;

@Getter @Setter
@Data
@NoArgsConstructor
public class User {
    private String name;
    private int age;

    public User(String name) {
        this.name = name;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

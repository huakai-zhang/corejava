package corejava.chapter11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class User {
    private Integer id;
    private String userName;
    private int age;

    public User(Integer id, String userName, int age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}

/**
 * 请按照给出数据，找出同时满足
 * 偶数 ID且 年龄大于 24且 用户名转为大写 且 用户名字母倒排序
 * 后 只输出一个用户名字
 */
public class StreamDemo {
    public static void main(String[] args) {
        User u1 = new User(11,"a",23);
        User u2 = new User(12,"b",24);
        User u3 = new User(13,"c",22);
        User u4 = new User(14,"d",28);
        User u5 = new User(16,"e",26);

        List<User> userList = Arrays.asList(u1, u2, u3, u4, u5);
        userList.stream().filter(u -> u.getId() % 2 == 0)
                .filter(u -> u.getAge() > 24)
                .map(u -> u.getUserName().toUpperCase())
                .sorted(Comparator.reverseOrder())
                .limit(1)
                .forEach(System.out::println);

        /*Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };
        Function<String, Integer> function1 = s -> s.length();
        System.out.println(function.apply("abc"));
        System.out.println(function1.apply("abcde"));

        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 3;
            }
        };
        Predicate<String> predicate1 = s -> s.length() > 3;
        System.out.println(predicate.test("abc"));
        System.out.println(predicate1.test("abcde"));

        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        Consumer<String> consumer1 = s -> System.out.println(s);
        consumer.accept("abc");
        consumer.accept("abcde");

        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "abc";
            }
        };
        Supplier<String> supplier1 = () -> {return "abcde";};
        System.out.println(supplier.get());
        System.out.println(supplier1.get());*/
    }
}

package com.spring.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootTest
class BCryptTest {

    @Test
    public void test() {
        // 对原始密码加密
        String hashpw = BCrypt.hashpw("123", BCrypt.gensalt());
        System.out.println(hashpw);

        // 校验原始密码和BCrypt密码是否一致
        boolean checkpw = BCrypt.checkpw("123", "$2a$10$c4G.dGQSKvaoFT3ZyuJr2e8KVWsINT4F.2jH7m8YysbeJBxRwS8fy");
        System.out.println(checkpw);
    }

}

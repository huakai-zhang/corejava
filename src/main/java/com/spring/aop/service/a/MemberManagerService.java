package com.spring.aop.service.a;

import com.spring.model.Member;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class MemberManagerService {

    private final static Logger logger = Logger.getLogger(MemberManagerService.class);

    public void add(Member member) {
        logger.info("增加用户");
    }

    public boolean remove(long id) throws Exception {
        logger.info("删除用户");
        throw new Exception("这是自己抛出来的异常");
    }

    public boolean modify(Member member) {
        logger.info("修改用户");
        return true;
    }

    public boolean query(String loginName) {
        logger.info("查询用户");
        return true;
    }
}

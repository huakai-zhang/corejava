package com.spring.account.service;

import com.spring.account.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountOutService {

    @Autowired
    private AccountDao accountDao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transferForOut(final String out, final Double money) throws Exception {
        int outCount = accountDao.updateForOut(out, money);

        if (outCount == 0) {
            throw new Exception("转出失败");
        }
        //throw new Exception("转账超时");
    }
}

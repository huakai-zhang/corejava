package com.spring.account.service;

import com.spring.account.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountInService {

    @Autowired
    private AccountDao accountDao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transferForIn(final String in, final Double money) throws Exception {
        int inCount = accountDao.updateForIn(in, money);

        if (inCount == 0) {
            throw new Exception("转入失败");
        }
    }
}

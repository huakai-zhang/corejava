package com.spring.account.service;

import com.spring.account.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AccountOutService accountOutService;

    @Autowired
    private AccountInService accountInService;

    /**
     * 转账逻辑
     * @param out 由谁转出
     * @param in 转给谁
     * @param money 转多少钱
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transfer(final String out, final String in, final Double money) throws Exception {
        if (out.equals(in)) {
            throw new Exception("不能给自己转账");
        }
        /*// 查询转出账户的余额
        Double account = accountDao.selectAccount(out);
        // 要保证，余额一定大于转出金额
        if (account.compareTo(money) < 0) {
            throw new Exception("余额不足");
        }*/

        accountOutService.transferForOut(out, money);

        accountInService.transferForIn(in, money);
    }

}

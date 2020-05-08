package com.spring.dubbo.order;

import com.spring.dubbo.dao.OrderDao;
import com.spring.dubbo.user.IUserService;
import com.spring.dubbo.user.dto.DebitRequest;
import com.spring.dubbo.user.dto.DebitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/14
 */
@Service(value = "orderService")
public class OrderServiceImpl implements IOrderService {

    @Autowired
    JtaTransactionManager springTransactionManager;

    @Autowired
    OrderDao orderDao;

    @Autowired
    IUserService userService;

    @Override
    public DoOrderResponse doOrder(DoOrderRequest request) {
        System.out.println("曾经来过：" + request);
        DoOrderResponse response = new DoOrderResponse();

        UserTransaction userTransaction = springTransactionManager.getUserTransaction();
        try {
            userTransaction.begin();
            orderDao.insertOrder();
            DebitResponse response1 = userService.debit(new DebitRequest());
            userTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                userTransaction.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        }

        response.setCode("1000");
        response.setMemo("处理成功");
        return response;
    }
}

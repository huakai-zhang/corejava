package com.spring.services.impl;

import com.spring.services.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public void insert() {
        System.out.println("哈哈");
    }
}

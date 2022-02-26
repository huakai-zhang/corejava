package com.spring.service.impl;

import com.spring.service.MemberService;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Lazy
    @Autowired
    private UserService userService;


}

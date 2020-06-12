package com.spring.dubbo.user;

import com.spring.dubbo.dao.UserDao;
import com.spring.dubbo.user.dto.DebitRequest;
import com.spring.dubbo.user.dto.DebitResponse;
import com.spring.dubbo.user.dto.UserRequest;
import com.spring.dubbo.user.dto.UserResponse;
import com.spring.dubbo.utils.JwtInfo;
import org.I0Itec.zkclient.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.xml.bind.ValidationException;
import javax.xml.rpc.ServiceException;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/16
 */
@Service(value = "userService")
public class UserService implements IUserService {

    @Autowired
    UserDao userDao;

    @Autowired
    JwtTokenService jwtTokenService;

    @Override
    public UserResponse login(String username, String password) {
        System.out.println("帐号为：" + username + ",密码为：" + password);
        UserResponse response = new UserResponse();
        response.setCode("11111");
        response.setMsg("登录成功");
        response.setToken(jwtTokenService.genetatorToken(new JwtInfo("1")));
        return response;
    }

    @Override
    public void toLogin(String name) {
        System.out.println("来自：" + name + "，等待登录调用。");
    }

    @Override
    public DebitResponse debit(DebitRequest request) {
        DebitResponse response = new DebitResponse();
        userDao.updateUser();
        response.setCode("000000");
        response.setMemo("成功");
        return response;
    }

    @Override
    public UserResponse checkAuth(UserRequest request) {
        UserResponse response = new UserResponse();
        try {
            beforeCheckAuthValidate(request);
            JwtInfo jwtInfo = jwtTokenService.getInfoFormToken(request.getToken());
            response.setCode("000000");
            response.setMsg("成功");
            response.setUid(jwtInfo.getUid());
        } catch (ValidationException e) {
            response.setCode("-1");
            response.setMsg(e.getMessage());
        }
        return response;
    }

    private void beforeCheckAuthValidate(UserRequest request) throws ValidationException {
        if (null == request) {
            throw new ValidationException("请求对象为空");
        }
        if (StringUtils.isEmpty(request.getToken())) {
            throw new ValidationException("Token信息为空");
        }
    }
}

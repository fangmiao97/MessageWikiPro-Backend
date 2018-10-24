package com.messagewikipro.first.controller;


import com.messagewikipro.first.DAO.RegisterLoginDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * created by Chaoyee
 * register module
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
public class registerController {

    @Autowired
    private RegisterLoginDAO registerLoginDAO;

    /**
     * 插入注册信息
     * @param request
     * @return 是否插入成功
     * @throws UnsupportedEncodingException
     */
    @GetMapping(value = "/register")
    @ResponseBody
    public int register(HttpServletRequest request) throws UnsupportedEncodingException{

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        int i = registerLoginDAO.registerUserInfo(username, password, email);
        return i;//1表示成功，0表示失败
    }

    /**
     * 检查输入的用户名是否有效--2期
     */
    public void nameValidity(){
    }


}

package com.messagewikipro.first.controller;

import com.messagewikipro.first.DAO.RegisterLoginDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * created by Chaoyue
 *  login module
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
public class loginContoller {

    @Autowired
    private RegisterLoginDAO registerLoginDAO;

    @GetMapping(value = "/loginCertificate")
    @ResponseBody
    public int loginCertificate(HttpServletRequest request) throws UnsupportedEncodingException{
        //先看用户存不存在，存在就验证密码。每种情况返回不用的返回数

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (registerLoginDAO.userExist(username)){//如果用户存在的话，则验证密码
            if (password.equals(registerLoginDAO.getUserPassword(username))){
                return 200;//密码正确
            }else return 500;//密码错误
        }else {
            return 404;//用户不存在
        }
    }
}

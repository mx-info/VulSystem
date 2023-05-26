package com.mxnet.controller;

import com.mxnet.mapper.UserMapper;
import com.mxnet.pojo.User;
import com.mxnet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session) {
        User user = userService.getUserByNameAndPwd(username, password);
//        System.out.println(user);
        if (user != null) {
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg", "用户名或密码错误");
            return "index";
        }
    }

    @RequestMapping("/user/register")
    public String register(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        User user = new User();
        user.setUserName(username);
        user.setPwd(password);
        int i = userService.insertUser(user);
        if (i > 0){
            model.addAttribute("msg","注册成功");
        }
        return "index";
    }
}

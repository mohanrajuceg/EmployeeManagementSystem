/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.employee.controller;

import com.neu.employee.dao.AdminDao;
import com.neu.employee.exception.CreateException;
import com.neu.employee.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Sindhu
 */
@Controller
public class UserLoginContoller {
    
    @Autowired
    AdminDao userDao;
    
    @RequestMapping(value="/login.htm",method = RequestMethod.GET)
    protected String doGet(Model model) {
        model.addAttribute("user",new User());
        return "home";
    }
    
    @RequestMapping(value = "/login.htm", method = RequestMethod.POST)
    protected ModelAndView user_login(@ModelAttribute("user")User user, BindingResult result,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
            HttpSession session = (HttpSession) request.getSession();
            User userData = userDao.getUser(user.getEmail(), user.getPassword());
            if (userData == null) {
                model.addAttribute("errorMessage", "Invalid username and password! Please try again!");
                return new ModelAndView("login_error");
            }
            session.setAttribute("user", user);
            String title = userData.getRole().trim();
            return new ModelAndView("admin_page","title",title);
    }
    
}

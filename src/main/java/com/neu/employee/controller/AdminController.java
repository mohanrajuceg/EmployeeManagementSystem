/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.employee.controller;

import com.neu.employee.dao.AdminDao;
import com.neu.employee.exception.CreateException;
import com.neu.employee.model.LeaveInfo;
import com.neu.employee.model.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
public class AdminController {
    
    @Autowired
    AdminDao userDao;
    
    @RequestMapping(value="/list_employee.htm",method = RequestMethod.GET)
    protected ModelAndView doGet() throws CreateException {
        List<User> employeeList = userDao.getAllEmployees();
        if(employeeList.size()>0){
            for(User user: employeeList){
                String manager = userDao.getManager(user.getManager_id());
                System.out.println(manager);
                user.setManager(manager);

            }
        }
        return  new ModelAndView("list_employee","employeeList",employeeList);
    }
    
    @RequestMapping(value="/list_leaves.htm",method = RequestMethod.GET)
    protected ModelAndView viewLeaves() throws CreateException {
       List<LeaveInfo> leavesList = userDao.getLeaveInfo();
        return  new ModelAndView("list_leaves","leaveList",leavesList);
    }
    
    @RequestMapping(value="/add_employee.htm",method = RequestMethod.GET)
    protected ModelAndView addEmployee(Model model) throws CreateException {
        model.addAttribute("user",new User());
        List<User>managerList = userDao.getAllManagers();
        return  new ModelAndView("add_employee","managerList",managerList);
    }
    
    
    @RequestMapping(value="/add_employee.htm",method = RequestMethod.POST)
    public ModelAndView submitUserData(@ModelAttribute("user")User user, BindingResult result,Model model) throws CreateException { 
        userDao.registerNewUser(user);
        List<User> employeeList = userDao.getAllEmployees();
        for(User u: employeeList){
            System.out.println(u.getManager_id());
            String manager = userDao.getManager(u.getManager_id());
            u.setManager(manager);
        }
        model.addAttribute("addedEmployee",true);
        return  new ModelAndView("list_employee","employeeList",employeeList);
    } 
    
    
    @RequestMapping(value="/add_leaves.htm",method = RequestMethod.GET)
    protected ModelAndView addLeave(Model model) throws CreateException {
        model.addAttribute("leaveInfo",new LeaveInfo());
        List<User>employeeList = userDao.getAllEmployees();
        return  new ModelAndView("add_leave","employeeList",employeeList);
    }
    
    @RequestMapping(value="/add_leaves.htm",method = RequestMethod.POST)
    public ModelAndView submitLeaveData(@ModelAttribute("leaveInfo")LeaveInfo leaveInfo, BindingResult result,Model model,HttpServletRequest request) throws CreateException { 
        String user_id = request.getParameter("user_id");
        User user = userDao.getUserById(Integer.parseInt(user_id));
        leaveInfo.setUser(user);
        userDao.addLeaveInfo(leaveInfo);
        List<LeaveInfo> leavesList = userDao.getLeaveInfo();
        model.addAttribute("addedLeave",true);
        return  new ModelAndView("list_leaves","leaveList",leavesList);
    } 
    
    
}

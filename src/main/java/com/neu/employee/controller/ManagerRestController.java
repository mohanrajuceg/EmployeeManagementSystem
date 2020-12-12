package com.neu.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neu.employee.dao.TaskDao;
import com.neu.employee.dao.UserDao;
import com.neu.employee.exception.CreateException;
import com.neu.employee.model.Tasks;
import com.neu.employee.model.User;

@RestController
public class ManagerRestController {
	
	@Autowired
    UserDao userDao;
	
	@Autowired
    TaskDao taskDao;
	
	@RequestMapping(value = "/getAssociates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAssociates(@RequestParam int managerId) {
		return userDao.getUsersByManagerId(managerId);
	}
	
	@RequestMapping(value = "/addTask", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Tasks addTask(@RequestBody Tasks task) throws CreateException {
		return taskDao.addTask(task);
	}
	
	@RequestMapping(value = "/getAllTasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Tasks> getAllTasks() {
		return taskDao.getAllTasks();
	}
	
	@RequestMapping(value = "/getAllActiveTasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Tasks> getAllActiveTasks() {
		return taskDao.getAllActiveTasks();
	}
	
	@RequestMapping(value = "/getTasksByUserId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Tasks> getTasksByUserId(@RequestParam int userId) {
		return taskDao.getTasksByUserId(userId);
	}

}

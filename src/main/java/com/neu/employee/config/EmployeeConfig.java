package com.neu.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.neu.employee.dao.AdminDao;
import com.neu.employee.dao.TaskDao;
import com.neu.employee.dao.UserDao;

@Configuration
public class EmployeeConfig {

	@Bean
	public UserDao userDao() {
		return new UserDao();
	}
	
	@Bean
	public TaskDao taskDao() {
		return new TaskDao();
	}
	
	@Bean
	public AdminDao adminDao() {
		return new AdminDao();
	}
}

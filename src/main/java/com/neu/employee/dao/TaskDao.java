package com.neu.employee.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.neu.employee.exception.CreateException;
import com.neu.employee.model.Tasks;

public class TaskDao extends BaseDao {

	public Tasks addTask(Tasks task) throws CreateException{
		try {
			begin();
			getSession().save(task);
			commit();
			return task;
		} catch(HibernateException e) {
			rollback();
			throw new CreateException("Unable to register the user " + e.getMessage());
		}
	}
	
	public List<Tasks> getAllTasks() throws HibernateException {
		try {
			begin();
			Query query = getSession().createQuery("from Tasks");
			List<Tasks> taskList = query.list();
			commit();
			return taskList;
		} catch (HibernateException e) {
			rollback();
			throw new HibernateException("Unable to fetch all tasks", e);
		}
	}
    
	public List<Tasks> getAllActiveTasks() throws HibernateException {
		try {
			Query query = getSession().createQuery("from Tasks where start_date<=:today and end_date>=today");
			query.setParameter("today", LocalDate.now());
			List<Tasks> taskList = query.list();
			return taskList;
		} catch (HibernateException e) {
			throw new HibernateException("Unable to fetch active tasks", e);
		}
	}
	
	public List<Tasks> getTasksByUserId(int user_id) throws HibernateException {
		try {
			Query query = getSession().createQuery("from Tasks where user.id=: userId and start_date<=:start_date and end_date>=:end_date");
			query.setParameter("userId", user_id);
			query.setParameter("start_date", Date.valueOf(LocalDate.now()));
			query.setParameter("end_date", Date.valueOf(LocalDate.now()));
			List<Tasks> taskList = query.list();
			return taskList;
		} catch (HibernateException e) {
			throw new HibernateException("Unable to fetch active tasks", e);
		}
	}
}

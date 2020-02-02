package com.personalkanbanboard.huaiwe;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
	private final TaskDao taskDao;
	
	@Autowired
	public TaskService(TaskDao taskDao) {
		this.taskDao = taskDao;
	}
	
	public int addTask(Task task) {
		return taskDao.insertTask(task);
	}
	
	public List<Task> getAllTask() {
		return taskDao.selectAllTask();
	}
	
	public Optional<Task> getTaskById(long id) {
		return taskDao.selectTaskById(id);
	}
	
	public int deleteTask(int id) {
		return taskDao.deleteTaskById(id);
	}
	
	public int updateTask(int id, Task task) {
		return taskDao.updateTaskById(id, task);
	}
	

}

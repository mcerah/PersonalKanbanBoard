package com.personalkanbanboard.huaiwe;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/v1/task")
@RestController
public class TaskController {
	private final TaskService taskService;
	
	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@PostMapping
	public Task addTask(@Valid @NotNull @RequestBody Task task) {
		taskService.addTask(task);
		return taskService.getTaskById(task.getId())
				.orElse(null);
	}
	
	@GetMapping
	public List<Task> getAllTask() {
		return taskService.getAllTask();
	}
	
	@GetMapping(path= "{id}")
	public Task getTaskById(@PathVariable("id") int id) {
		return taskService.getTaskById(id)
				.orElse(null);
	}
	
	@DeleteMapping(path="{id}")
	public void deleteTaskById(@PathVariable("id") int id) {
		taskService.deleteTask(id);
	}
	
	@PutMapping(path="{id}")
	public Task updateTask(@PathVariable("id") int id, @Valid @NotNull @RequestBody Task task) {
		if (task.getStatus() == 2) {
			task.setCompletionDate(Date.valueOf(LocalDate.now()));
		} else {
			task.setCompletionDate(null);
		}
		taskService.updateTask(id, task);
		return taskService.getTaskById(task.getId())
				.orElse(null);
	}

}

package com.example.TaskManagement.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TaskManagement.Repo.ITaskRepo;
import com.example.TaskManagement.model.Tasks;

@Service
public class TaskService {

	@Autowired
	ITaskRepo repo;

	public List<Tasks> getAllTasks() {
		ArrayList<Tasks> taskList = new ArrayList<>();
		repo.findAll().forEach(task -> taskList.add(task));

		return taskList;
	}

	public Tasks getTaskById(Long id) {
		return repo.findById(id).get();
	}

	public boolean updateStatus(Long id) {
		Tasks task = getTaskById(id);
		task.setStatus("Completed");

		return saveOrUpdateTask(task);
	}

	public boolean saveOrUpdateTask(Tasks task) {
		Tasks updatedObj = repo.save(task);

		if (getTaskById(updatedObj.getId()) != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteTask(Long id) {
		repo.deleteById(id);

		if (repo.findById(id).isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}

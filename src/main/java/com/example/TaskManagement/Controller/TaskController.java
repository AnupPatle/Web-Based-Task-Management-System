package com.example.TaskManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import com.example.TaskManagement.Service.TaskService;
import com.example.TaskManagement.model.Tasks;

@Controller
public class TaskController {

	@Autowired
	private TaskService service;

	@GetMapping({ "/", "viewTaskList" })
	public String viewAllItems(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("list", service.getAllTasks());
		model.addAttribute("message", message);
		return "ViewTaskList";
	}

	@GetMapping("/updateTaskStatus/{id}")
	public String updateTaskStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if (service.updateStatus(id)) {
			redirectAttributes.addFlashAttribute("message", "Update Success");
			return "redirect:/viewTaskList";
		}
		redirectAttributes.addFlashAttribute("message", "Update Failure");
		return "redirect:/viewTaskList";
	}

	@GetMapping("/addTaskItem")
	public String addTaskItem(Model model) {
		model.addAttribute("task", new Tasks());
		return "AddTaskItem";
	}

	@PostMapping("/saveTaskItem")
	public String saveTaskItem(Tasks task, RedirectAttributes redirectAttributes) {
		if (service.saveOrUpdateTask(task)) {
			redirectAttributes.addFlashAttribute("message", "Save Success");
			return "redirect:/viewTaskList";
		}
		redirectAttributes.addFlashAttribute("message", "Save Failure");
		return "redirect:/addTaskItem";
	}

	@GetMapping("/editTaskItem/{id}")
	public String editTaskItem(@PathVariable Long id, Model model) {
		model.addAttribute("task", service.getTaskById(id));
		return "EditTaskItem";
	}

	@PostMapping("/editSaveTaskItem")
	public String editSaveTaskItem(Tasks task, RedirectAttributes redirectAttributes) {
		if (service.saveOrUpdateTask(task)) {
			redirectAttributes.addFlashAttribute("message", "Edit Success");
			return "redirect:/viewTaskList";
		}
		redirectAttributes.addFlashAttribute("message", "Edit Failure");
		return "redirect:/editTaskItem/" + task.getId();

	}

	@GetMapping("deleteTaskItem/{id}")
	public String deleteTaskItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if (service.deleteTask(id)) {
			redirectAttributes.addFlashAttribute("message", "Delete Success");
			return "redirect:/viewTaskList";
		}
		redirectAttributes.addFlashAttribute("message", "Delete Failure");
		return "redirect:/viewTaskList";
	}

}

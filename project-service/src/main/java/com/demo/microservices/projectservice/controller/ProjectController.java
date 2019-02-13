package com.demo.microservices.projectservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservices.projectservice.model.ProjectInfo;
import com.demo.microservices.projectservice.service.ProjectService;


@RestController
@CrossOrigin
@RequestMapping("/projects")
public class ProjectController {
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	ProjectService projectService;
	
	//Get All projects
	@GetMapping()
//	@PreAuthorize("hasRole('ROLE_GUEST')")
	public List<ProjectInfo> getAllProjects() {
		return projectService.getAllProjects();
	}
	
	//Create a new project
	@PostMapping()
	public ProjectInfo createProject(@Valid @RequestBody ProjectInfo info) {
		return projectService.createProject(info);
	}
	
	//Get a single project
	@GetMapping("/{id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ProjectInfo getProjectById(@PathVariable(value = "id") Integer id) {
		return  projectService.getProjectById(id);
	}
	
	//Update a project
	@PutMapping("/{id}")
	public ProjectInfo updateProject(@PathVariable(value = "id") Integer id, @Valid @RequestBody ProjectInfo info) {
		return projectService.updateProject(info);
	}
	
	//Delete (set isActive to be false and leave in database)
	@DeleteMapping("/{id}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void deleteProject(@PathVariable(value = "id") Integer id) {
		projectService.deleteProject(id);
	}
}

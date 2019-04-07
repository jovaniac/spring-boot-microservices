package com.demo.microservices.projectservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.microservices.projectservice.model.Enums;
import com.demo.microservices.projectservice.model.Enums.PStatus;
import com.demo.microservices.projectservice.model.ProjectInfo;
import com.demo.microservices.projectservice.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository repository;

	String userID = "1";// This should get from JWT token

	@Override
	public List<ProjectInfo> getAllProjects() {
		return repository.findAllByStatusIdNotOrderByDueDateAsc(PStatus.DELETED.getValue());
	}

	@Override
	public ProjectInfo createProject(ProjectInfo info) {
		return repository.save(info);
	}

	@Override
	public ProjectInfo getProjectById(Integer id) {
		return repository.findById(id).get();
	}

	@Override
	public ProjectInfo updateProject(ProjectInfo info) {
		ProjectInfo project = repository.findById(info.getProjectId()).get();
		if (project == null) {
			return null;
		}

		project.setProjectName(info.getProjectName());
		project.setProjectSummary(info.getProjectSummary());
		project.setDueDate(info.getDueDate());
		project.setEstimatedCost(info.getEstimatedCost());
		project.setProjectStatus(info.getProjectStatus());

		return repository.save(project);
	}

	@Override
	public void deleteProject(Integer id) {
		ProjectInfo project = repository.findById(id).get();
		if (project != null) {
			project.setStatusId(Enums.PStatus.DELETED.getValue());
			repository.save(project);
		}
	}

}

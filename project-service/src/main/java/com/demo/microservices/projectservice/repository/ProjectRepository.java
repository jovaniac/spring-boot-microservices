package com.demo.microservices.projectservice.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.demo.microservices.projectservice.model.ProjectInfo;

@Repository
public interface ProjectRepository extends CrudRepository<ProjectInfo, Integer> {

  List<ProjectInfo> findAllByStatusIdNotOrderByCreatedOnDesc(int statusId);

  List<ProjectInfo> findAllByStatusIdNotOrderByDueDateAsc(int statusId);
}

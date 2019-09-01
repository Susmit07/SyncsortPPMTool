package com.syncsort.kanban.ppmtool.repositories;

import com.syncsort.kanban.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {



}

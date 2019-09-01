package com.syncsort.kanban.ppmtool.service;

import com.syncsort.kanban.ppmtool.domain.Project;
import com.syncsort.kanban.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        return  projectRepository.save(project);
    }

}

package com.syncsort.kanban.ppmtool.service;

import com.syncsort.kanban.ppmtool.domain.Project;
import com.syncsort.kanban.ppmtool.exception.ProjectIDException;
import com.syncsort.kanban.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIDException("Project ID " + project.getProjectIdentifier() + " already exists");
        }
    }

    public Project findByProjectID(String projectID) {
        Project project = projectRepository.findByProjectIdentifier(projectID.toUpperCase());
        if (project == null)
            throw new ProjectIDException("Project ID "+projectID.toUpperCase()+ " don't exist in the database");
        return project;
    }

    public List<Project> findAllProjects() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);
        return projects;
    }

    @Transactional
    public void deleteProjectById(String projectIdentifierID) {
        Project project = projectRepository.findByProjectIdentifier(projectIdentifierID.toUpperCase());
        if(project ==  null)
            throw new ProjectIDException("Project ID "+projectIdentifierID.toUpperCase()+ " don't exist to be deleted");
        projectRepository.deleteByProjectIdentifier(projectIdentifierID);
    }
}

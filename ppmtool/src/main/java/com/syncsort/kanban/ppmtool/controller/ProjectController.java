package com.syncsort.kanban.ppmtool.controller;

import com.syncsort.kanban.ppmtool.domain.Project;
import com.syncsort.kanban.ppmtool.service.MapValidationErrorService;
import com.syncsort.kanban.ppmtool.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    MapValidationErrorService mapValidationErrorService;

    // @Valid for better readable exception is the response due to javax validation.
    // Binding result is an interface that invokes the validator on the object and binds the result to it
    // Persist employee.
    @PostMapping("/add")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult) {

        if (mapValidationErrorService.getValidationErrors(bindingResult) != null)
            return mapValidationErrorService.getValidationErrors(bindingResult);
        projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    // Get Employee Details on basis of projectID.
    @GetMapping("/get/{projectID}")
    public ResponseEntity<?> getProjectByID(@PathVariable String projectID) {
        Project projectByID = projectService.findByProjectID(projectID);
        return new ResponseEntity<Project>(projectByID, HttpStatus.OK);
    }
}

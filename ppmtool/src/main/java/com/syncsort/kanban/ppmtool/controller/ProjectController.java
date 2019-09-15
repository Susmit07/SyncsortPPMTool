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
import java.util.List;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    MapValidationErrorService mapValidationErrorService;

    // @Valid for better readable exception is the response due to javax validation.
    // Binding result is an interface that invokes the validator on the object and binds the result to it
    // Persist employee.
    @PostMapping("/addProject")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult) {

        if (mapValidationErrorService.getValidationErrors(bindingResult) != null)
            return mapValidationErrorService.getValidationErrors(bindingResult);
        projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    // Get Employee details on basis of projectID.
    @GetMapping("/getProject/{projectID}")
    public ResponseEntity<?> findProjectByID(@PathVariable String projectID) {
        Project projectByID = projectService.findByProjectID(projectID);
        return new ResponseEntity<>(projectByID, HttpStatus.OK);
    }

    // Get all Employee Details.
    @GetMapping("/getAll")
    public ResponseEntity<?> findAllProjects() {
        List<Project> projectByID = projectService.findAllProjects();
        return new ResponseEntity<>(projectByID, HttpStatus.OK);
    }

    // Delete Employee Details.
    @DeleteMapping("/deleteProject/{projectID}")
    public ResponseEntity<?> deleteProjectByID(@PathVariable String projectID){
        projectService.deleteProjectById(projectID);
        return new ResponseEntity<>("Project with ID: "+projectID+" was deleted successfully.", HttpStatus.OK);
    }
}

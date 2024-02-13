package com.zergatstage.gbsprings05.controller;

import com.zergatstage.gbsprings05.model.Project;
import com.zergatstage.gbsprings05.model.User;
import com.zergatstage.gbsprings05.model.UsersProject;
import com.zergatstage.gbsprings05.repositories.ProjectRepository;
import com.zergatstage.gbsprings05.repositories.UserRepository;
import com.zergatstage.gbsprings05.services.UserProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-project")
public class UserProjectController {
    @Autowired
    private UserProjectService userProjectService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getAllProjects(){
        return new ResponseEntity<>(projectRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/projects")
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        Project savedProject = projectRepository.save(project);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }


    @GetMapping("/users/{projectId}")
    public ResponseEntity<List<User>> getUsersByProjectId(@PathVariable Long projectId) {
        List<User> users = userProjectService.getUsersByProjectId(projectId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/projects/{userId}")
    public ResponseEntity<List<Project>> getProjectsByUserId(@PathVariable Long userId) {
        List<Project> projects = userProjectService.getProjectsByUserId(userId);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @PostMapping("/add-user/{userId}/{projectId}")
    public ResponseEntity<UsersProject> addUserToProject(@PathVariable Long userId, @PathVariable Long projectId) {
        return new ResponseEntity<>(userProjectService.addUserToProject(userId, projectId), HttpStatus.CREATED);
    }

    @PostMapping("/remove-user/{userId}/{projectId}")
    public ResponseEntity<Void> removeUserFromProject(@PathVariable Long userId, @PathVariable Long projectId) {
        userProjectService.removeUserFromProject(userId, projectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


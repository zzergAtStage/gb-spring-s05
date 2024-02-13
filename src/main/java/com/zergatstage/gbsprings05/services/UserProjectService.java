package com.zergatstage.gbsprings05.services;

import com.zergatstage.gbsprings05.model.NoDataFoundException;
import com.zergatstage.gbsprings05.model.Project;
import com.zergatstage.gbsprings05.model.User;
import com.zergatstage.gbsprings05.model.UsersProject;
import com.zergatstage.gbsprings05.repositories.ProjectRepository;
import com.zergatstage.gbsprings05.repositories.UserRepository;
import com.zergatstage.gbsprings05.repositories.UsersProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserProjectService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ProjectRepository projectRepository;
    @Autowired
    private final UsersProjectRepository usersProjectRepository;

    public UserProjectService(UserRepository userRepository, ProjectRepository projectRepository
            , UsersProjectRepository usersProjectRepository){
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.usersProjectRepository = usersProjectRepository;
    }
    public List<User> getUsersByProjectId(Long projectId){
        List<UsersProject> usersProjects = usersProjectRepository.findAllByProjectId(projectId);
        List<User> users = new ArrayList<>();
        for (UsersProject usersProject: usersProjects){
            Optional<User> userOptional = userRepository.findById(usersProject.getUserId());
            userOptional.ifPresent(users::add);
        }
        return users;
    }
    public List<Project> getProjectsByUserId(Long userId) {
        List<UsersProject> usersProjects = usersProjectRepository.findAllByUserId(userId);
        System.out.println("Debug: usersProjects.size=" + usersProjects.size());
        List<Project> projects = new ArrayList<>();
        for (UsersProject usersProject : usersProjects) {
            Optional<Project> projectOptional = projectRepository.findById(usersProject.getProjectId());
            projectOptional.ifPresent(projects::add);
        }
        System.out.println("Debug: projects.size=" + projects.size());
        return projects;
    }
    public UsersProject addUserToProject(Long userId, Long projectId) {
        UsersProject usersProject = new UsersProject();
        usersProject.setUserId(userId);
        usersProject.setProjectId(projectId);
        return usersProjectRepository.save(usersProject);
    }
    public void removeUserFromProject(Long userId, Long projectId) {
        usersProjectRepository.deleteByUserIdAndProjectId(userId, projectId);
    }

}

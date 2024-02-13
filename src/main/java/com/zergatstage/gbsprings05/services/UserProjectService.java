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

/**
 * Service class for managing user-project relationships.
 */
@Service
public class UserProjectService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ProjectRepository projectRepository;
    @Autowired
    private final UsersProjectRepository usersProjectRepository;

    /**
     * Constructor for UserProjectService.
     *
     * @param userRepository         Repository for User entities.
     * @param projectRepository      Repository for Project entities.
     * @param usersProjectRepository Repository for UsersProject entities.
     */
    public UserProjectService(UserRepository userRepository, ProjectRepository projectRepository
            , UsersProjectRepository usersProjectRepository){
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.usersProjectRepository = usersProjectRepository;
    }

    /**
     * Get a list of users associated with a given project.
     *
     * @param projectId The ID of the project.
     * @return A list of users associated with the project.
     */
    public List<User> getUsersByProjectId(Long projectId){
        List<UsersProject> usersProjects = usersProjectRepository.findAllByProjectId(projectId);
        List<User> users = new ArrayList<>();
        for (UsersProject usersProject: usersProjects){
            Optional<User> userOptional = userRepository.findById(usersProject.getUserId());
            userOptional.ifPresent(users::add);
        }
        return users;
    }

    /**
     * Get a list of projects associated with a given user.
     *
     * @param userId The ID of the user.
     * @return A list of projects associated with the user.
     */
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

    /**
     * Add a user to a project.
     *
     * @param userId    The ID of the user.
     * @param projectId The ID of the project.
     * @return The UsersProject entity representing the relationship.
     */
    public UsersProject addUserToProject(Long userId, Long projectId) {
        UsersProject usersProject = new UsersProject();
        usersProject.setUserId(userId);
        usersProject.setProjectId(projectId);
        return usersProjectRepository.save(usersProject);
    }

    /**
     * Remove a user from a project.
     *
     * @param userId    The ID of the user.
     * @param projectId The ID of the project.
     */
    public void removeUserFromProject(Long userId, Long projectId) {
        usersProjectRepository.deleteByUserIdAndProjectId(userId, projectId);
    }
}

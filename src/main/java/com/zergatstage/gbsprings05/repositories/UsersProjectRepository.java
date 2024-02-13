package com.zergatstage.gbsprings05.repositories;

import com.zergatstage.gbsprings05.model.UsersProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersProjectRepository extends JpaRepository<UsersProject, Long> {
    List<UsersProject> findAllByProjectId(Long projectId);

    List<UsersProject> findAllByUserId(Long userId);

    void deleteByUserIdAndProjectId(Long userId, Long projectId);
}

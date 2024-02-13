package com.zergatstage.gbsprings05.repositories;

import com.zergatstage.gbsprings05.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}

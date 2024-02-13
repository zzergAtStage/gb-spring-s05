package com.zergatstage.gbsprings05.repositories;

import com.zergatstage.gbsprings05.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

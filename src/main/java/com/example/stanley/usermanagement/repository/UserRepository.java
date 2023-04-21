package com.example.stanley.usermanagement.repository;

import com.example.stanley.usermanagement.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
  Users findByUsername(String username);
}

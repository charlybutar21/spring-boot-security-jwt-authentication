package com.charly.perpetuostudio.repository;

import com.charly.perpetuostudio.models.Role;
import com.charly.perpetuostudio.models.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}

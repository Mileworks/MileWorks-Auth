package com.mileworks.authsys.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mileworks.authsys.model.Role;
import com.mileworks.authsys.model.RoleName;

/**
 * @author mileworks
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}

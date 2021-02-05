package com.tenpo.app.repository;

import java.util.Optional;

import com.tenpo.app.model.Role;
import com.tenpo.app.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository
				extends JpaRepository<Role, Long> {
	Optional<Role> findByName(UserRole name);

}
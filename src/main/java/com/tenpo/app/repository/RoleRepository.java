package com.tenpo.app.tenpo.repository;

import java.util.Optional;

import com.tenpo.app.tenpo.model.Role;
import com.tenpo.app.tenpo.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository
				extends JpaRepository<Role, Long> {
	Optional<Role> findByName(UserRole name);

}
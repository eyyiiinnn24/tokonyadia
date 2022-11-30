package com.mandiri.tokonyadia.repository;

import com.mandiri.tokonyadia.constant.ERole;
import com.mandiri.tokonyadia.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {


    Optional<Role> findByeRole(ERole role);

}

package com.mandiri.tokonyadia.service.impl;

import com.mandiri.tokonyadia.constant.ERole;
import com.mandiri.tokonyadia.entity.Role;
import com.mandiri.tokonyadia.repository.RoleRepository;
import com.mandiri.tokonyadia.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getOrSave(ERole role) {
        return roleRepository.findByeRole(role)
                .orElseGet(()->roleRepository.save(new Role(null,role)));
    }
}

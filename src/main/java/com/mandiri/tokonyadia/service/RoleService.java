package com.mandiri.tokonyadia.service;

import com.mandiri.tokonyadia.constant.ERole;
import com.mandiri.tokonyadia.entity.Role;

public interface RoleService {

    Role getOrSave (ERole role);
}

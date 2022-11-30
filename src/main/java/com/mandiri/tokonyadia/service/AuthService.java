package com.mandiri.tokonyadia.service;

import com.mandiri.tokonyadia.dto.LoginDTO;
import com.mandiri.tokonyadia.entity.AppUser;

public interface AuthService {
    AppUser register(AppUser appUser);
    LoginDTO login (AppUser appUser);

}

package com.mandiri.tokonyadia.service.impl;

import com.mandiri.tokonyadia.entity.AppUser;
import com.mandiri.tokonyadia.entity.UserDetailImpl;
import com.mandiri.tokonyadia.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserServiceImpl implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public CustomUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser>appUser=appUserRepository.findByEmail(username);
        if(!appUser.isPresent()){
            throw new UsernameNotFoundException("User Not Found");
        }
        return new UserDetailImpl(appUser.get());
    }
}

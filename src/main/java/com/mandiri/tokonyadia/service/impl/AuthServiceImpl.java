package com.mandiri.tokonyadia.service.impl;

import com.mandiri.tokonyadia.constant.ERole;
import com.mandiri.tokonyadia.dto.LoginDTO;
import com.mandiri.tokonyadia.entity.AppUser;
import com.mandiri.tokonyadia.entity.Customer;
import com.mandiri.tokonyadia.entity.Role;
import com.mandiri.tokonyadia.entity.UserDetailImpl;
import com.mandiri.tokonyadia.repository.AppUserRepository;
import com.mandiri.tokonyadia.security.JwtUtils;
import com.mandiri.tokonyadia.service.AuthService;
import com.mandiri.tokonyadia.service.CustomerService;
import com.mandiri.tokonyadia.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class AuthServiceImpl implements AuthService {
    private final AppUserRepository appUserRepository;
    private final AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtUtils;
    private RoleService roleService;
    private final CustomerService customerService;

    @Autowired
    public AuthServiceImpl(AppUserRepository appUserRepository, AuthenticationManager authenticationManager,
                           PasswordEncoder passwordEncoder, JwtUtils jwtUtils, RoleService roleService, CustomerService customerService) {
        this.appUserRepository = appUserRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.roleService = roleService;
        this.customerService = customerService;
    }

    @Override
    @Transactional
    public AppUser register(AppUser appUser) {
        Role role=roleService.getOrSave(ERole.ROLE_CUSTOMER);
        appUser.setRoles(Arrays.asList(role));
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        AppUser appUser1=appUserRepository.save(appUser);


        Customer customer=new Customer();
        customer.setEmail(appUser1.getEmail());
        customer.setFullName("");
        customer.setAppUser(appUser1);
        customerService.saveCustomer(customer);
        return appUser1;
    }

    @Override
    public LoginDTO login(AppUser appUser) {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(appUser.getEmail(), appUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailImpl userDetail=(UserDetailImpl) authentication.getPrincipal();
        String token = jwtUtils.generateTokenFromUsername(userDetail.getUsername());
        return new LoginDTO(userDetail, token);
    }
}

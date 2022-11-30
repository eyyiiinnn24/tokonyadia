package com.mandiri.tokonyadia.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailImpl implements UserDetails {
    private String id;
    private String username;
    private String password;
    public Collection<? extends  GrantedAuthority> authorities;

    public UserDetailImpl(AppUser appUser){
        List<SimpleGrantedAuthority> roles=appUser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getERole().name()))
                .collect(Collectors.toList());
        //pemakaian Collectors.tolist bertujuan untuk didaftarkan di spring security
        // dan dibuat kan list, karena kita bisa set roles banyak tidak hanya satu.
        this.id = appUser.getId();
        this.username = appUser.getEmail();
        this.password = appUser.getPassword();
        this.authorities=roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

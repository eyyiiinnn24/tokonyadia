package com.mandiri.tokonyadia.dto;

import com.mandiri.tokonyadia.entity.UserDetailImpl;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class LoginDTO {

    private String username;
    private List<String>roles;
    private String token;

    public LoginDTO(UserDetailImpl userDetail,String token) {
        this.username =userDetail.getUsername() ;
        this.roles = userDetail.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

package dev.valente.course_platform.config;


import dev.valente.course_platform.devs.Devs;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserAuthenticated implements UserDetails {

    private final Devs dev;

    public UserAuthenticated(Devs dev) {
        this.dev = dev;
    }

    @Override
    public String getUsername() {
        return dev.getUserName();
    }

    @Override
    public String getPassword() {
        return dev.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "read");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
}

package dev.valente.course_platform.devs.service;

import dev.valente.course_platform.config.UserAuthenticated;
import dev.valente.course_platform.devs.exceptions.DevNotFound;
import dev.valente.course_platform.devs.repository.DevsRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final DevsRepository devsRepository;

    public UserDetailsServiceImpl(DevsRepository devsRepository) {
        this.devsRepository = devsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws DevNotFound {

        return devsRepository.findDevsByUserName(username)
                .map(UserAuthenticated::new)
                .orElseThrow(DevNotFound::new);
    }
}

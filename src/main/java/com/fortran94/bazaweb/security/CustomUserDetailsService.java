package com.fortran94.bazaweb.security;

import com.fortran94.bazaweb.model.ParticipantUser;
import com.fortran94.bazaweb.repository.ParticipantUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ParticipantUserRepository repository;

    public CustomUserDetailsService(ParticipantUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ParticipantUser participantUser = repository.findByCallSign(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с позывным " +
                        username + " не найден"));

        return User.builder()
                .username(participantUser.getCallSign())
                .password(participantUser.getPassword())
                .roles(participantUser.getRole().name())
                .build();
    }
}

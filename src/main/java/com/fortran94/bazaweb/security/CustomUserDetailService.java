//package com.fortran94.bazaweb.security;
//
//import com.fortran94.bazaweb.model.ParticipantUser;
//import com.fortran94.bazaweb.repository.ParticipantUserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class CustomUserDetailService implements UserDetailsService {
//
//    private final ParticipantUserRepository repository;
//
//    public CustomUserDetailService(ParticipantUserRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        ParticipantUser user = repository.findByName(username).
//                orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + username));
//        return org.springframework.security.core.userdetails.User
//                .builder()
//                .username(user.getName())
//                .password(user.getPassword())
//                .roles(String.valueOf(user.getRole()))
//                .build();
//    } //TODO username - callsign?
//}
//

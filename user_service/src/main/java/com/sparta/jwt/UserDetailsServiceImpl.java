package com.sparta.jwt;

import com.sparta.entity.User;
import com.sparta.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("이메일을 찾을 수 없습니다"));

        // Return a Spring Security User with roles
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }
}

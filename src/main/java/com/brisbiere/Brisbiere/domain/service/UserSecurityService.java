package com.brisbiere.Brisbiere.domain.service;

import com.brisbiere.Brisbiere.domain.User;
import com.brisbiere.Brisbiere.domain.UserRole;
import com.brisbiere.Brisbiere.persistence.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.usuarioRepository.hasAccount(username).orElseThrow(()->new UsernameNotFoundException("User " + username + " not found."));

        String [] roles = user.getRoles().stream().map(UserRole::getRole).toArray(String[]::new);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(this.grantedAuthorities(roles))
                .build();
    }

    private String [] getAuthorities(String role){
        if("ADMIN".equals(role) || "CUSTOMER".equals(role)){
            return new String[]{"random_order"};
        }

        return new String[] {};
    }

    private List<GrantedAuthority> grantedAuthorities(String [] roles){
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);

        for (String role:roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

            for (String authority:this.getAuthorities(role)) {
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }

        return authorities;
    }
}

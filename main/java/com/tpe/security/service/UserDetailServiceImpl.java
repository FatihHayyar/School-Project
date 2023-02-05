package com.tpe.security.service;

import com.tpe.domain.Role;
import com.tpe.domain.User;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    //bu classta amacim user ve rolleri degistirmek
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUserName(username).orElseThrow(()->new ResourceNotFoundException("User not found"));

        if(user!=null){
            return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),buildGrantedAuthorities(user.getRoles()));
        }else{
            throw new ResourceNotFoundException("User not found");
        }
    }
    private static List<SimpleGrantedAuthority> buildGrantedAuthorities(final Set<Role> roles){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role :roles){
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));

        }
        return  authorities;
    }
}

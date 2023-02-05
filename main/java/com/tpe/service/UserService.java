package com.tpe.service;

import com.tpe.domain.Role;
import com.tpe.domain.User;
import com.tpe.domain.enums.UserRole;
import com.tpe.dto.UserRequest;
import com.tpe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public void saveUser(UserRequest userRequest) {
        User myUser = new User();
        myUser.setFirstName(userRequest.getFirstName());
        myUser.setLastName(userRequest.getLastName());
        myUser.setUserName(userRequest.getUserName());
        // myUser.setPassword(userRequest.getPassword());
        String password = userRequest.getPassword();
        String encodedPassword = passwordEncoder.encode(password);

        myUser.setPassword(encodedPassword);

        // Role setlenmeli
Role role;
        if(userRequest.getFirstName().equals("Gulsah")){
            role = roleService.getRoleByType(UserRole.ROLE_TEACHER);
        }else if(userRequest.getFirstName().equals("Fatih")){
            role = roleService.getRoleByType(UserRole.ROLE_ADMIN);
        }else{
            role = roleService.getRoleByType(UserRole.ROLE_STUDENT);
        }

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        myUser.setRoles(roles);

        userRepository.save(myUser);

    }
}

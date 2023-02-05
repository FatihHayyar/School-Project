package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 25)
    private String firstName;
    @Column(nullable = false,length = 25)
    private String lastName;
    @Column(nullable = false,length = 25,unique = true)
    private String userName;
    @Column(nullable = false,length = 255)
    private String password;

    @JoinTable(name = "t_user_role",joinColumns =@JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<Role>();

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Student student;
}

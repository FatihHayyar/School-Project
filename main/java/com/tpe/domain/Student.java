package com.tpe.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter//bu annotation lari fieldlarin ustune yazipta yapabilirim
@Setter
@Entity
@AllArgsConstructor//tum constructarlari alir bunu istemiyorsak
//@RequiredArgsConstructor //sadece final le yazilan field lari alir
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
@NotNull(message = "first name cannot be null")
@NotBlank(message = "first name cannot be space")
@Size(min = 2, max =25,message = "first name '${validatedValue}' must be between {min} and {max} long")
@Column(nullable = false,length = 25)
    private String name;
    @NotNull(message = "first name cannot be null")
    @NotBlank(message = "first name cannot be space")
    @Size(min = 2, max =25,message = "first name '${validatedValue}' must be between {min} and {max} long")
    @Column(nullable = false,length = 25)
   private String lastName;
    @NotNull
    @NotBlank
    @Column(unique = true,nullable = false)
    private String studentNumber;

    private Integer vizeNot;
    private Integer finalNot;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}

package com.tpe.dto.Student;

import com.tpe.domain.Student;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
@Getter//bu annotation lari fieldlarin ustune yazipta yapabilirim
@Setter

@AllArgsConstructor//tum constructarlari alir bunu istemiyorsak
//@RequiredArgsConstructor //sadece final le yazilan field lari alir
@NoArgsConstructor
public class StudentDTO {


    @NotNull(message = "first name cannot be null")
    @NotBlank(message = "first name cannot be space")
    @Size(min = 2, max = 25, message = "first name '${validatedValue}' must be between {min} and {max} long")
    private String name;
    @NotNull(message = "first name cannot be null")
    @NotBlank(message = "first name cannot be space")
    @Size(min = 2, max = 25, message = "first name '${validatedValue}' must be between {min} and {max} long")
    private String lastName;

    private String studentNumber;

    private Integer vizeNot;
    private Integer finalNot;

    public StudentDTO(Student student){

        this.name= student.getName();
        this.lastName= student.getLastName();
        this.studentNumber= student.getStudentNumber();
        this.vizeNot=student.getVizeNot();
        this.finalNot= student.getFinalNot();
    }

}

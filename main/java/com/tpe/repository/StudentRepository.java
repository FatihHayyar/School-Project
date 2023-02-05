package com.tpe.repository;

import com.tpe.domain.Student;
import com.tpe.dto.Student.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    boolean existsByStudentNumber(String studentNumber);

    Optional<Student> findByStudentNumber(String studentNumber);
    @Query("select s.name from Student s")
    List<String> findAllNames();


//    boolean existsByLastName(String lastName);
//
//    List<Student> findByLastName(String lastName);
//
//    List<Student> findByGrade(Integer grade);
//
//    //JPQL ile yazalim
    @Query("SELECT s FROM Student s WHERE s.finalNot<:pnot")
    List<Student> getAllStudentByFinalNot(@Param("pnot") Integer grade);
//    //Native sql ile
////    @Query(value="SELECT * FROM Student s WHERE s.grade<:pGrade",nativeQuery=true)
////    List<Student> getAllStudentByGrade(@Param("pGrade") Integer grade);
//
//    @Query("select new com.tpe.dto.Student.StudentDTO(s) from Student s where s.id=:id")
//    Optional<StudentDTO> findStudentDTOById(@Param("id") Long id);


}

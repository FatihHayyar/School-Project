package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.dto.Student.StudentDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
//
    public List<Student> getAll() {
      return studentRepository.findAll();

    }
//
    public void createStudent(Student student) {
       if (studentRepository.existsByStudentNumber(student.getStudentNumber())){
           throw new ConflictException("this student nummer already exists");
       }
        studentRepository.save(student);
    }
//
    public Student findStudent(String studentNumber) {
      return studentRepository.findByStudentNumber(studentNumber).orElseThrow(()->new ResourceNotFoundException("student not found: "));
    }
//
    public void deleteStudent(String studentNumber) {
        //ilk yol
//        if(!studentRepository.existsById(id)){
//            throw new ResourceNotFoundException("student not found: "+id);
//        }
//        studentRepository.deleteById(id);
        Student student=findStudent(studentNumber);
        studentRepository.delete(student);
    }
//
//
    public void updateStudent(String studentNumber, StudentDTO studentdto) {
        boolean existNumber=studentRepository.existsByStudentNumber(studentdto.getStudentNumber());
        Student student=findStudent(studentNumber);

        if(existNumber&&!student.getStudentNumber().equals(studentdto.getStudentNumber())){
            throw new ConflictException("Student number already exists");
        }
        student.setName(student.getName());
        student.setLastName(studentdto.getLastName());
        student.setStudentNumber(studentdto.getStudentNumber());
        student.setVizeNot(studentdto.getVizeNot());
        student.setFinalNot(studentdto.getFinalNot());
        studentRepository.save(student);
    }
    public void notUpdateStudent(String studentNumber, StudentDTO studentdto) {
        boolean existNumber=studentRepository.existsByStudentNumber(studentdto.getStudentNumber());
        Student student=findStudent(studentNumber);
        if(!existNumber){
            throw new ResourceNotFoundException("Student not found");
        }

        student.setVizeNot(studentdto.getVizeNot());
        student.setFinalNot(studentdto.getFinalNot());
        studentRepository.save(student);
    }


//
    public Page<Student> getAllWithPage(Pageable pageable) {
      return studentRepository.findAll(pageable);
    }

    public List<String> getAllNames() {
        return studentRepository.findAllNames();
    }
//
//    public List<Student> getAllStudentByLastName(String lastName) {
//        if (!studentRepository.existsByLastName(lastName)){
//            throw new ResourceNotFoundException("Student not found by last name");
//        }
//        return studentRepository.findByLastName(lastName);
//    }
//
    public List<Student> getStudentsByFinalNot(Integer finalNot) {
//        return studentRepository.findByGrade(grade); 1.yol
        return studentRepository.getAllStudentByFinalNot(finalNot);

    }
//
//    public StudentDTO getStudentDto(Long id) {
//        return studentRepository.findStudentDTOById(id).orElseThrow(()->new ResourceNotFoundException("StudentDTO"));
//
//    }
}

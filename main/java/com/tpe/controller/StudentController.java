package com.tpe.controller;



import com.tpe.domain.Student;
import com.tpe.dto.Student.StudentDTO;
import com.tpe.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
//    Logger logger=LoggerFactory.getLogger(StudentController.class);
//
    @Autowired
    private StudentService service;
//
    //get all students
    @GetMapping // http://localhost:8080/students + GET
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Student>> getAll() {
    List<Student> mylist=service.getAll();

    return ResponseEntity.ok(mylist);//200 kodudur
    }
    //create a new student
    @PostMapping// http://localhost:8080/students + Post+Json olara gelir
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createStudent(@Valid @RequestBody Student student){
    service.createStudent(student);
    String map="Creating is successful";
    return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
//    //belli id li ogrenciyi cagiralim
//    @GetMapping("/query")
//    public ResponseEntity<Student> getStudent(@RequestParam("id") Long id ){//eger tek data geleckse ("id") kismina gerek yok
//    Student student=service.findStudent(id);
//    return ResponseEntity.ok(student);
//    }
//
    //belli id li ogrenciyi cagiralim Path variable ile
    @GetMapping("{studentNumber}") //http://localhost:8080/students/1
    public ResponseEntity<Student> getStudentWithPath(@PathVariable("studentNumber") String studentNumber){//1 den fazla data gelecekse request param kullanilir
        Student student=service.findStudent(studentNumber);
        return ResponseEntity.ok(student);
    }
    //belli nolu  ogrenciyi sil

    @DeleteMapping("{studentNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteStudent(@PathVariable("studentNumber") String studentNumber){
        service.deleteStudent(studentNumber);
        String map=
        "Deleting is successful";

        return new ResponseEntity<>(map,HttpStatus.OK);
    }
        //Update
    @PutMapping("{studentNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateStudent(@PathVariable("studentNumber") String studentNumber,@RequestBody StudentDTO studentdto){
        service.updateStudent(studentNumber,studentdto);
        String map=
        "Updating is successful";

        return new ResponseEntity<>(map,HttpStatus.OK);


    }
    @PutMapping("/notUpdate/{studentNumber}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<String> notUpdateStudent(@PathVariable("studentNumber") String studentNumber,@RequestBody StudentDTO studentdto){
        service.notUpdateStudent(studentNumber,studentdto);
        String map=
                "Updating is successful";

        return new ResponseEntity<>(map,HttpStatus.OK);


    }
////    @PreAuthorize("hasRole('ADMIN')")
    //Pageable
    @GetMapping("/page")

    public ResponseEntity<Page<Student>> getAllWithPage(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sort") String prop, @RequestParam("direction")Sort.Direction direction){

        Pageable pageable= PageRequest.of(page,size,Sort.by(direction,prop));
        Page<Student> studentPage=service.getAllWithPage(pageable);
        return ResponseEntity.ok(studentPage);
    }

    @GetMapping("/names") // http://localhost:8080/students + GET
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<List<String>> getAllNames() {
        List<String> mylist=service.getAllNames();

        return ResponseEntity.ok(mylist);//200 kodudur
    }
//
//    //soyadi ile ogrencileri cagir
//    @GetMapping("/queryLastName")
//    public ResponseEntity<List<Student>> getAllStudentByLastName(@RequestParam("lastName") String lastName){
//        List<Student> list=service.getAllStudentByLastName(lastName);
//        return  ResponseEntity.ok(list);
//    }
//
    //get all students by grade JPQL ile
    @GetMapping("/queryNot/{finalNot}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Student>> getStudentsbyNot(@PathVariable("finalNot") Integer finalNot){

        List<Student> list=service.getStudentsByFinalNot(finalNot);
        return ResponseEntity.ok(list);
    }
//
    //DB den direkt DTO olarak veri alalim
//    @GetMapping("/querydto")
//    public ResponseEntity<StudentDTO> getStudentDto(@RequestParam("id") Long id){
//        StudentDTO student=service.getStudentDto(id);
//        return ResponseEntity.ok(student);
//    }
//
//    @GetMapping("/welcome")  // http://localhost:8080/students/welcome + GET
//    public String welcome(HttpServletRequest request){ //  HttpServletRequest ile request e ulaştım
//        logger.warn("-------------------- Welcome {}", request.getServletPath());
//        return "Student Controller a Hoş Geldiniz";
//    }

}

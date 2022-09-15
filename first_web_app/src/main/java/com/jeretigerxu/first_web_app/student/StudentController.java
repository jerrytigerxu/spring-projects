package com.jeretigerxu.first_web_app.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")

public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

   /*
   What the post request actually looks like:
   POST http://localhost:8080/api/v1/student
   Content-Type: application/json
   {
    "name": "Jere",
    "email": "jtxu2008@gmail.com",
    "dob": "1998-04-18"
   }

   Using cURL to make the request directly in the browser URL:
   curl -v -H "Content-Type: application/json" -X POST \
        -d '{"name": "Jere", "email": "jtxu2008@gmail.com", "dob": "1998-04-18"}' \
        http://localhost:8080/api/v1/student


    */



    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @PutMapping(path = "{studentId}")
    //For the updateStudent method, it needs to take 3 parameters: id for identification, name for changing name, and email for changing email
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required=false) String name,
            @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }

    @DeleteMapping(path = "{studentID}")
    public void deleteStudent(@PathVariable("studentID") Long studentId) {
        studentService.deleteStudent(studentId);
    }


}

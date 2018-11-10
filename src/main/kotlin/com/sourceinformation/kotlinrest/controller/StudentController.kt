package com.sourceinformation.kotlinrest.controller

import com.sourceinformation.kotlinrest.model.Student
import com.sourceinformation.kotlinrest.service.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong

@RestController
@RequestMapping(value = "/students")
class StudentController @Autowired constructor(
        private var studentService: StudentService){ //primary constructor

    val counter = AtomicLong()

    //pattern constructor
    /*@Autowired
    constructor(studentRepository: StudentRepository) {
        this.studentRepository = studentRepository
    }*/

    @GetMapping("/student")
    fun stundent(@RequestParam(value = "name", defaultValue="World") name: String) =
            Student(counter.incrementAndGet(), "Hello, $name")

    @PostMapping
    fun saveStudent(@RequestBody student: Student): ResponseEntity<Student> {
        val saveStudent = studentService.save(student)
        return ResponseEntity.ok(saveStudent)
    }

    @PutMapping
    fun updateStudent(@RequestBody student: Student): ResponseEntity<Student> {
        val updateStudent = studentService.save(student)
        return ResponseEntity.ok(updateStudent)
    }

    @DeleteMapping("{id}")
    fun deleteStudent(@PathVariable id: Long): ResponseEntity<Unit> {
        studentService.delete(id)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Student> {
        return ResponseEntity.ok(studentService.findById(id))
    }
}
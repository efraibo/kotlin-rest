package com.sourceinformation.kotlinrest.controller

import com.sourceinformation.kotlinrest.model.Student
import com.sourceinformation.kotlinrest.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong

@RestController(value = "/students")
class StudentController {

    @Autowired
    lateinit var studentRepository: StudentRepository

    val counter = AtomicLong()

    @GetMapping("/student")
    fun stundent(@RequestParam(value = "name", defaultValue="World") name: String) =
            Student(counter.incrementAndGet(), "Hello, $name")

    @PostMapping
    fun saveStudent(@RequestBody student: Student): ResponseEntity<Student> {
        val saveStudent = studentRepository.save(student)
        return ResponseEntity.ok(saveStudent)
    }

    @PutMapping
    fun updateStudent(@RequestBody student: Student): ResponseEntity<Student> {
        verifyIfStudentExist(student.id)
        val updateStudent = studentRepository.save(student)
        return ResponseEntity.ok(updateStudent)
    }

    @DeleteMapping("{id}")
    fun deleteStudent(@PathVariable id: Long) {
        verifyIfStudentExist(id)
        studentRepository.deleteById(id)
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Student> {
        return ResponseEntity.ok(studentRepository.findById(id).orElse(null))
    }

    private fun verifyIfStudentExist(id: Long): Unit {
        if (!studentRepository.findById(id).isPresent) {
            throw Exception("Student not found" + id)
        }
    }
}
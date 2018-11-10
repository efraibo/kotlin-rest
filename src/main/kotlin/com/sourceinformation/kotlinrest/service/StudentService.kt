package com.sourceinformation.kotlinrest.service

import com.sourceinformation.kotlinrest.model.Student
import com.sourceinformation.kotlinrest.repository.StudentRepository
import com.sourceinformation.kotlinrest.service.Exceptions.ObjectNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StudentService @Autowired constructor(private var studentRepository: StudentRepository) {
    fun save(student: Student): Student {
        return studentRepository.save(student)
    }

    fun delete(id: Long): Unit {
        this.verifyIfStudentExist(id)
        studentRepository.deleteById(id)
    }

    fun findById(id: Long): Student {
        this.verifyIfStudentExist(id)
        return studentRepository.findById(id).orElse(null)
    }

    fun update(student: Student): Student {
        this.verifyIfStudentExist(student.id)
        return studentRepository.save(student)
    }

    private fun verifyIfStudentExist(id: Long): Unit {
        if (!studentRepository.findById(id).isPresent) {
            throw ObjectNotFoundException("Student not found! Id: " + id)
        }
    }
}
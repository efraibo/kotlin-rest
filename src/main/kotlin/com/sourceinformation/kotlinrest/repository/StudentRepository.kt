package com.sourceinformation.kotlinrest.repository

import com.sourceinformation.kotlinrest.model.Student
import org.springframework.data.repository.CrudRepository

interface StudentRepository : CrudRepository<Student, Long>
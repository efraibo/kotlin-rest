package com.sourceinformation.kotlinrest.model

import javax.persistence.*

@Entity
data class Student(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @Column(nullable = false)
        val name: String
)
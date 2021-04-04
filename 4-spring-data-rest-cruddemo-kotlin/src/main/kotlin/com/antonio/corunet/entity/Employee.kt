package com.antonio.corunet.entity

import javax.persistence.*

@Entity
class Employee(
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    var id: Int,

    @Column(name="first_name")
    var firstName: String,

    @Column(name="last_name")
    var lastName: String,

    @Column(name="email")
    var email: String
)
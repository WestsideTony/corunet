package com.antonio.corunet.dao

import com.antonio.corunet.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository: JpaRepository<Employee, Int>
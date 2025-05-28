package com.pr6.SpringServer.controller

import com.pr6.SpringServer.model.employee.Employee
import com.pr6.SpringServer.model.employee.EmployeeDao
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employee")
class EmployeeController (private val employeeDao: EmployeeDao) {

    @GetMapping("/get-all")
    fun getAllEmployees(): List<Employee> {
        return employeeDao.getAllEmployees()
    }

    @PostMapping("/save")
    fun saveEmployee(@RequestBody employee: Employee): Employee {
        return employeeDao.save(employee)
    }
}
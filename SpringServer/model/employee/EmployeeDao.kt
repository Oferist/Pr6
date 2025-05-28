package com.pr6.SpringServer.model.employee

import org.springframework.stereotype.Service

@Service
class EmployeeDao (
    private val repository: EmployeeRepository
) {
    fun save(employee: Employee) = repository.save(employee)

    fun getAllEmployees(): List<Employee> =
        repository.findAll().toList()

    fun delete(employeeId: Int) = repository.deleteById(employeeId)
}
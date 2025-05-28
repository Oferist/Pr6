package com.example.springclient.retrofit

import com.example.springclient.model.Employee
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EmployeeApi {
    @GET("/employee/get-all")
    fun getAllEmployees(): Call<List<Employee>>

    @POST("/employee/save")
    fun save(@Body employee: Employee): Call<Employee>
}
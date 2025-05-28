package com.example.springclient

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.springclient.adapter.EmployeeAdapter
import com.example.springclient.model.Employee
import com.example.springclient.retrofit.EmployeeApi
import com.example.springclient.retrofit.RetrofitService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_list)
        initViews()
        setupRecyclerView()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.employeeRecyclerView)
        val fab: FloatingActionButton = findViewById(R.id.employeeList_fab)
        fab.setOnClickListener {
            startActivity(Intent(this, EmployeeForm::class.java))
        }
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = EmployeeAdapter(emptyList())
        recyclerView.adapter = adapter
    }

    private fun loadEmployees() {
        val retrofitService = RetrofitService()
        val employeeApi = retrofitService.getRetrofit().create(EmployeeApi::class.java)
        employeeApi.getAllEmployees().enqueue(object : Callback<List<Employee>> {
            override fun onResponse(call: Call<List<Employee>>, response: Response<List<Employee>>) {
                response.body()?.let { employees ->
                    // Берем только первые 5 элементов
                    val limitedList = if (employees.size > 5) employees.subList(0, 5) else employees
                    adapter.updateList(limitedList)
                }
            }

            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                Toast.makeText(
                    this@EmployeeListActivity,
                    "Failed to load employees: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        loadEmployees()
    }
}
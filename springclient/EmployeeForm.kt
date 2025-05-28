package com.example.springclient

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.springclient.model.Employee
import com.example.springclient.retrofit.EmployeeApi
import com.example.springclient.retrofit.RetrofitService
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Level
import java.util.logging.Logger

class EmployeeForm : AppCompatActivity() {
    private lateinit var inputEditTextName: TextInputEditText
    private lateinit var inputEditBranch: TextInputEditText
    private lateinit var inputEditLocation: TextInputEditText
    private lateinit var buttonSave: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        setupListeners()
    }

    // Инициализация элементов экрана
    private fun initializeViews() {
        inputEditTextName = findViewById(R.id.form_textFieldName)
        inputEditBranch = findViewById(R.id.form_textFieldBranch)
        inputEditLocation = findViewById(R.id.form_textFieldLocation)
        buttonSave = findViewById(R.id.form_buttonSave)
    }

    // Обработка нажатия на кнопку
    private fun setupListeners() {
        val retrofitService = RetrofitService()
        val employeeApi = retrofitService.getRetrofit().create(EmployeeApi::class.java)
        buttonSave.setOnClickListener {
            val name = inputEditTextName.text?.toString() ?: ""
            val branch = inputEditBranch.text?.toString() ?: ""
            val location = inputEditLocation.text?.toString() ?: ""
            // Проверка заполенностей полей
            if (name.isBlank() || branch.isBlank() || location.isBlank()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Создание объекта Employee
            val employee = Employee(name = name, branch = branch, location = location)
            employeeApi.save(employee).enqueue(object : Callback<Employee> {
                override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@EmployeeForm, "Save successful!", Toast.LENGTH_SHORT)
                            .show()
                        clearFields()
                    } else {
                        Toast.makeText(
                            this@EmployeeForm,
                            "Save failed: ${response.code()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Employee>, t: Throwable) {
                    Toast.makeText(
                        this@EmployeeForm,
                        "Save failed: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Logger.getLogger(EmployeeForm::class.java.name)
                        .log(Level.SEVERE, "Error occurred", t)
                }
            })
        }
    }

    // Очистка полей формы
    private fun clearFields() {
        inputEditTextName.text?.clear()
        inputEditBranch.text?.clear()
        inputEditLocation.text?.clear()
    }
}
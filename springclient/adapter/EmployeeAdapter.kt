package com.example.springclient.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.springclient.R
import com.example.springclient.model.Employee

class EmployeeAdapter(
    private var employeeList: List<Employee> = emptyList()
) : RecyclerView.Adapter<EmployeeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeHolder {
        val view = View.inflate(parent.context, R.layout.list_employee_item, null)
        return EmployeeHolder(view).apply {
            itemView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onBindViewHolder(holder: EmployeeHolder, position: Int) {
        val employee = employeeList[position]
        holder.name.text = employee.name
        holder.location.text = employee.location
        holder.branch.text = employee.branch
    }

    override fun getItemCount(): Int = employeeList.size
    fun updateList(newList: List<Employee>) {
        employeeList = newList
        notifyDataSetChanged()
    }
}
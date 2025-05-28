package com.example.springclient.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.springclient.R

class EmployeeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.employeeNameTextView)
    val location: TextView = itemView.findViewById(R.id.locationTextView)
    val branch: TextView = itemView.findViewById(R.id.departmentTextView)
}

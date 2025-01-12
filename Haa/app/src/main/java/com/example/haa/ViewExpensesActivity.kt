package com.example.expensetracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.haa.R

class ViewExpensesActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    private lateinit var adapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_expenses_layout)

        dbHelper = DBHelper(this)
        adapter = ExpenseAdapter { id -> deleteExpense(id) }

        val expenseRecyclerView: RecyclerView = findViewById(R.id.expenseRecyclerView)
        expenseRecyclerView.layoutManager = LinearLayoutManager(this)
        expenseRecyclerView.adapter = adapter

        refreshExpenses()
    }

    private fun refreshExpenses() {
        val expenses = dbHelper.getAllExpenses()
        adapter.submitList(expenses)
    }

    private fun deleteExpense(id: Int) {
        dbHelper.deleteExpense(id)
        refreshExpenses()
    }
}

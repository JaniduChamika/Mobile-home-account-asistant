package com.example.expensetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.haa.R

class HomeActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this)

        findViewById<Button>(R.id.addExpenseButton).setOnClickListener {
            startActivity(Intent(this, AddExpenseActivity::class.java))
        }

        findViewById<Button>(R.id.viewExpensesButton).setOnClickListener {
            startActivity(Intent(this, ViewExpensesActivity::class.java))
        }

        findViewById<Button>(R.id.summaryButton).setOnClickListener {
            startActivity(Intent(this, SummaryActivity::class.java))
        }

        findViewById<Button>(R.id.settingsButton).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        val totalExpense = dbHelper.getTotalExpense()
        findViewById<TextView>(R.id.totalExpenseTextView).text = "Total Expense: $%.2f".format(totalExpense)
    }
}

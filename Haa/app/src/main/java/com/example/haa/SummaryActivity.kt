package com.example.homeexpensetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.expensetracker.HomeActivity
import com.example.haa.R

class SummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.summary_layout) // Ensure your XML file is named `summary_layout.xml`

        // Initializing the views
        val totalExpenseTextView: TextView = findViewById(R.id.totalExpenseTextView)
        val totalIncomeTextView: TextView = findViewById(R.id.TotalIncomeAmount)
        val totalBalanceTextView: TextView = findViewById(R.id.totalexpenceAmount)
        val homeButton: Button = findViewById(R.id.homebutton)

        // Simulated values (replace with real data from your database or logic)
        val totalExpenses = 500.0
        val totalIncome = 1000.0
        val balance = totalIncome - totalExpenses

        // Setting the values to the TextViews
        totalExpenseTextView.text = "$$totalExpenses"
        totalIncomeTextView.text = "$$totalIncome"
        totalBalanceTextView.text = "$$balance"

        // Button click listener to navigate back to the home screen
        homeButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java) // Replace with your actual home activity
            startActivity(intent)
        }
    }
}

package com.example.expensetracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.haa.R

class EditExpenseActivity : AppCompatActivity() {
//    private lateinit var dbHelper: DBHelper
//    private var expenseId: Int = -1
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.edit_expense_layout)
//
//        dbHelper = DBHelper(this)
//
//        expenseId = intent.getIntExtra("expense_id", -1)
//        val expense = dbHelper.getAllExpenses().find { it[DBHelper.COLUMN_ID] == expenseId }
//
//        if (expense != null) {
//            findViewById<EditText>(R.id.expenseNameEditText).setText(expense[DBHelper.COLUMN_NAME] as String)
//            findViewById<EditText>(R.id.expenseCategoryEditText).setText(expense[DBHelper.COLUMN_CATEGORY] as String)
//            findViewById<EditText>(R.id.expenseAmountEditText).setText((expense[DBHelper.COLUMN_AMOUNT] as Double).toString())
//        }
//
//        findViewById<Button>(R.id.updateExpenseButton).setOnClickListener {
//            val name = findViewById<EditText>(R.id.expenseNameEditText).text.toString()
//            val category = findViewById<EditText>(R.id.expenseCategoryEditText).text.toString()
//            val amount = findViewById<EditText>(R.id.expenseAmountEditText).text.toString().toDoubleOrNull()
//
//            if (name.isNotEmpty() && category.isNotEmpty() && amount != null) {
//                dbHelper.updateExpense(expenseId, name, category, amount)
//                Toast.makeText(this, "Expense Updated", Toast.LENGTH_SHORT).show()
//                finish()
//            } else {
//                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
}

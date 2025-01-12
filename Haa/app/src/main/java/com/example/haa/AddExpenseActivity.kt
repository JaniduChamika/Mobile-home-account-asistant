import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.haa.R

class AddExpenseActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_expense_layout)

        dbHelper = DBHelper(this)

        val saveExpenseButton: Button = findViewById(R.id.saveExpenseButton)
        saveExpenseButton.setOnClickListener {
            val name = findViewById<EditText>(R.id.expenseNameEditText).text.toString()
            val category = findViewById<EditText>(R.id.expenseCategoryEditText).text.toString()
            val amount = findViewById<EditText>(R.id.expenseAmountEditText).text.toString().toDoubleOrNull()

            if (name.isNotEmpty() && category.isNotEmpty() && amount != null) {
                dbHelper.insertExpense(name, category, amount)
                finish()
            }
        }
    }
}

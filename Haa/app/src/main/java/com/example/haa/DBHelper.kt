package com.example.haa

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.haa.datamodel.Category
import com.example.haa.datamodel.Expense
import com.example.haa.datamodel.Income
import com.example.haa.datamodel.User
import com.google.android.material.tabs.TabLayout.Tab
import java.util.Date

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    companion object {
        private const val DATABASE_NAME = "Spendly"

        //user table
        const val TABLE_USERS = "User"
        const val COLUMN_USER_ID = "id"
        const val COLUMN_USER_NAME = "name"
        const val COLUMN_USER_EMAIL = "email"
        const val COLUMN_USER_PASSWORD = "password"
        const val COLUMN_USER_STATUS = "status"

        //income table
        const val TABLE_INCOME = "Income"
        const val COLUMN_INCOME_ID = "id"
        const val COLUMN_INCOME_CATEGORY = "category"
        const val COLUMN_INCOME_AMOUNT = "amount"
        const val COLUMN_INCOME_DATE = "date"

        //Expense table
        const val TABLE_EXPENSE = "Expense"
        const val COLUMN_EXPENSE_ID = "id"
        const val COLUMN_EXPENSE_CATEGORY = "category"
        const val COLUMN_EXPENSE_AMOUNT = "amount"
        const val COLUMN_EXPENSE_DATE = "date"
        const val COLUMN_EXPENSE_NOTE = "note"

        //Income category table
        const val TABLE_INCOME_CATEGORY = "Income_category"
        const val COLUMN_INCOME_CATEGORY_ID = "id"
        const val COLUMN_INCOME_CATEGORY_NAME = "name"

        //EXPENSE category table
        const val TABLE_EXPENSE_CATEGORY = "Expense_category"
        const val COLUMN_EXPENSE_CATEGORY_ID = "id"
        const val COLUMN_EXPENSE_CATEGORY_NAME = "name"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Create Users table
        val createUsersTable = ("CREATE TABLE $TABLE_USERS ("
                + "$COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_USER_NAME TEXT, "
                + "$COLUMN_USER_EMAIL TEXT, "
                + "$COLUMN_USER_PASSWORD TEXT,"
                + "$COLUMN_USER_STATUS INTEGER)")

        val createIncomeTable = ("CREATE TABLE $TABLE_INCOME ("
                + "$COLUMN_INCOME_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_INCOME_CATEGORY TEXT, "
                + "$COLUMN_INCOME_AMOUNT REAL , "
                + "$COLUMN_INCOME_DATE TEXT )")
        val createExpenseTable = ("CREATE TABLE $TABLE_EXPENSE ("
                + "$COLUMN_EXPENSE_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_EXPENSE_CATEGORY TEXT, "
                + "$COLUMN_EXPENSE_AMOUNT REAL , "
                + "$COLUMN_EXPENSE_DATE TEXT,"
                + "$COLUMN_EXPENSE_NOTE TEXT )")
        val createExpenseCategoryTable = ("CREATE TABLE $TABLE_EXPENSE_CATEGORY ("
                + "$COLUMN_EXPENSE_CATEGORY_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_EXPENSE_CATEGORY_NAME TEXT)")
        val createIncomeCategoryTable = ("CREATE TABLE $TABLE_INCOME_CATEGORY ("
                + "$COLUMN_INCOME_CATEGORY_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_INCOME_CATEGORY_NAME TEXT)")
        db?.execSQL(createUsersTable)
        db?.execSQL(createIncomeTable)
        db?.execSQL(createExpenseTable)
        db?.execSQL(createExpenseCategoryTable)
        db?.execSQL(createIncomeCategoryTable)
        insertInitalCategory(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_INCOME")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_EXPENSE")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_EXPENSE_CATEGORY")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_INCOME_CATEGORY")

    }

    // Insert User
    fun insertUser(name: String, email: String, password: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_USER_NAME, name)
        contentValues.put(COLUMN_USER_EMAIL, email)
        contentValues.put(COLUMN_USER_PASSWORD, password)
        contentValues.put(COLUMN_USER_STATUS, 0)
        db.insert(TABLE_USERS, null, contentValues)
        db.close()
    }

    fun findUserByEmail(email: String): User? {
        val db = this.readableDatabase
        val record = db.rawQuery("SELECT * FROM $TABLE_USERS WHERE email='$email'", null)
        var user: User? = null
        if (record.moveToNext()) {
            user = User(
                id = record.getInt(record.getColumnIndexOrThrow(COLUMN_USER_ID)),
                name = record.getString(record.getColumnIndexOrThrow(COLUMN_USER_NAME)),
                email = record.getString(record.getColumnIndexOrThrow(COLUMN_USER_EMAIL))

            )
        }
        db.close()
        return user
    }

    fun validateLoginEmailPassword(email: String, password: String): User? {
        val db = this.readableDatabase
        val record = db.rawQuery(
            "SELECT * FROM $TABLE_USERS WHERE email='$email' AND password='$password'",
            null
        )
        var user: User? = null
        if (record.moveToNext()) {
            user = User(
                id = record.getInt(record.getColumnIndexOrThrow(COLUMN_USER_ID)),
                name = record.getString(record.getColumnIndexOrThrow(COLUMN_USER_NAME)),
                email = record.getString(record.getColumnIndexOrThrow(COLUMN_USER_EMAIL))

            )
        }
        db.close()
        return user
    }

    fun insertIncome(category: String, date: String, amount: Double) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_INCOME_CATEGORY, category)
        contentValues.put(COLUMN_INCOME_AMOUNT, amount)
        contentValues.put(COLUMN_INCOME_DATE, date)
        db.insert(TABLE_INCOME, null, contentValues)
        db.close()

    }

    fun insertExpense(category: String, date: String, amount: Double, note: String?) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_EXPENSE_CATEGORY, category)
        contentValues.put(COLUMN_EXPENSE_AMOUNT, amount)
        contentValues.put(COLUMN_EXPENSE_DATE, date)
        contentValues.put(COLUMN_EXPENSE_NOTE, note)

        db.insert(TABLE_EXPENSE, null, contentValues)
        db.close()

    }

    fun insertExCategory(name: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_EXPENSE_CATEGORY_NAME, name)
        db.insert(TABLE_EXPENSE_CATEGORY, null, contentValues)
        db.close()
    }

    fun insertInCategory(name: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_INCOME_CATEGORY_NAME, name)
        db.insert(TABLE_INCOME_CATEGORY, null, contentValues)
        db.close()
    }

    fun insertInitalCategory(db: SQLiteDatabase?) {
        val initialCategories = listOf(
            "Salary",
            "Freelance",
            "Investment"
        )

        for (category in initialCategories) {
            val values = ContentValues().apply {
                put(COLUMN_INCOME_CATEGORY_NAME, category)
            }
            db?.insert(TABLE_INCOME_CATEGORY, null, values)
        }

        val initialExpenseCategories = listOf(
            "Electricity Bill",
            "Water Bill"
        )

        for (category in initialExpenseCategories) {
            val values = ContentValues().apply {
                put(COLUMN_EXPENSE_CATEGORY_NAME, category)
            }
            db?.insert(TABLE_EXPENSE_CATEGORY, null, values)
        }
    }

    fun getAllIncomes(): List<Income>? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_INCOME ", null)
        val incomeList = mutableListOf<Income>()
        var income: Income? = null
        while (cursor.moveToNext()) {
            income = Income(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INCOME_ID)),
                category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INCOME_CATEGORY)),
                date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INCOME_DATE)),
                amount = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_INCOME_AMOUNT))
            )
            incomeList.add(income)
        }
        cursor.close()
        db.close()
        return incomeList
    }

    fun getAllIncomes(from: String?, to: String?): List<Income>? {
        val db = this.readableDatabase
        var query = "SELECT * " +
                " FROM $TABLE_INCOME"
        if (!to.isNullOrEmpty() && !from.isNullOrEmpty()) {
            query += " WHERE DATE($COLUMN_INCOME_DATE) >= DATE('$from') AND DATE($COLUMN_INCOME_DATE) <= DATE('$to')"
        } else if (to.isNullOrEmpty() && !from.isNullOrEmpty()) {
            query += " WHERE DATE($COLUMN_INCOME_DATE) >= DATE('$from') "
        } else if (!to.isNullOrEmpty() && from.isNullOrEmpty()) {
            query += " WHERE DATE($COLUMN_INCOME_DATE) <= DATE('$to')"
        }
        query +=" ORDER BY $COLUMN_INCOME_DATE DESC"
        val cursor = db.rawQuery(query, null)
        val incomeList = mutableListOf<Income>()
        var income: Income? = null
        while (cursor.moveToNext()) {
            income = Income(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INCOME_ID)),
                category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INCOME_CATEGORY)),
                date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INCOME_DATE)),
                amount = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_INCOME_AMOUNT))
            )
            incomeList.add(income)
        }
        cursor.close()
        db.close()
        return incomeList
    }

    fun getAllExpenses(): List<Expense>? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_EXPENSE ", null)
        val expenseList = mutableListOf<Expense>()
        var expense: Expense? = null
        while (cursor.moveToNext()) {
            expense = Expense(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_ID)),
                category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_CATEGORY)),
                date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_DATE)),
                amount = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_AMOUNT)),
                note = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_NOTE))
            )
            expenseList.add(expense)
        }
        cursor.close()
        db.close()
        return expenseList
    }

    fun getAllExpenses(from: String?, to: String?): List<Expense>? {
        val db = this.readableDatabase
        var query = "SELECT * " +
                " FROM $TABLE_EXPENSE"
        if (!to.isNullOrEmpty() && !from.isNullOrEmpty()) {
            query += " WHERE DATE($COLUMN_EXPENSE_DATE) >= DATE('$from') AND DATE($COLUMN_EXPENSE_DATE) <= DATE('$to')"
        } else if (to.isNullOrEmpty() && !from.isNullOrEmpty()) {
            query += " WHERE DATE($COLUMN_EXPENSE_DATE) >= DATE('$from') "
        } else if (!to.isNullOrEmpty() && from.isNullOrEmpty()) {
            query += " WHERE DATE($COLUMN_EXPENSE_DATE) <= DATE('$to')"
        }
        query +=" ORDER BY $COLUMN_EXPENSE_DATE DESC"

        val cursor = db.rawQuery(query, null)
        val expenseList = mutableListOf<Expense>()
        var expense: Expense? = null
        while (cursor.moveToNext()) {
            expense = Expense(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_ID)),
                category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_CATEGORY)),
                date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_DATE)),
                amount = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_AMOUNT)),
                note = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_NOTE))
            )
            expenseList.add(expense)
        }
        cursor.close()
        db.close()
        return expenseList
    }

    fun getTotalExpenses(): Double {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT SUM($COLUMN_EXPENSE_AMOUNT) FROM $TABLE_EXPENSE", null)
        var total = 0.0
        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0)
        }
        cursor.close()
        db.close()
        return total
    }

    fun getTotalIncome(): Double {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT SUM($COLUMN_INCOME_AMOUNT) FROM $TABLE_INCOME", null)
        var total = 0.0
        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0)
        }
        cursor.close()
        db.close()
        return total
    }

    fun getTotalExpensesForCurrentMonth(): Double {
        val db = this.readableDatabase

        val query = "SELECT SUM($COLUMN_EXPENSE_AMOUNT)" +
                " FROM $TABLE_EXPENSE" +
                " WHERE strftime('%Y-%m', $COLUMN_EXPENSE_DATE) = strftime('%Y-%m', 'now')"

        val cursor = db.rawQuery(query, null)
        var totalAmount = 0.0
        if (cursor.moveToFirst()) {
            totalAmount = cursor.getDouble(0)
        }
        cursor.close()
        db.close()
        return totalAmount
    }

    fun getTotalIncomeForCurrentMonth(): Double {
        val db = this.readableDatabase

        val query = "SELECT SUM(${COLUMN_INCOME_AMOUNT})" +
                " FROM $TABLE_INCOME" +
                " WHERE strftime('%Y-%m', $COLUMN_INCOME_DATE) = strftime('%Y-%m', 'now')"

        val cursor = db.rawQuery(query, null)
        var totalAmount = 0.0
        if (cursor.moveToFirst()) {
            totalAmount = cursor.getDouble(0)
        }
        cursor.close()
        db.close()
        return totalAmount
    }

    fun getAllIncomeCategory(): List<Category>? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_INCOME_CATEGORY ", null)
        val categoryList = mutableListOf<Category>()
        var category: Category? = null
        while (cursor.moveToNext()) {
            category = Category(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INCOME_CATEGORY_ID)),
                name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INCOME_CATEGORY_NAME))
            )
            categoryList.add(category)
        }
        cursor.close()
        db.close()
        return categoryList
    }
    fun checkIncomeCategoryExist(name:String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_INCOME_CATEGORY WHERE LOWER($COLUMN_INCOME_CATEGORY_NAME) =LOWER('$name') ", null)
       var isExits=false
        if (cursor.moveToNext()) {
            isExits=true
        }
        cursor.close()
        db.close()
        return isExits
    }
    fun checkExpenseCategoryExist(name:String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_EXPENSE_CATEGORY WHERE LOWER($COLUMN_EXPENSE_CATEGORY_NAME) = LOWER('$name') ", null)
        var isExits=false
        if (cursor.moveToNext()) {
            isExits=true
        }
        cursor.close()
        db.close()
        return isExits
    }
    fun getAllExpenseCategory(): List<Category>? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_EXPENSE_CATEGORY ", null)
        val categoryList = mutableListOf<Category>()
        var category: Category? = null
        while (cursor.moveToNext()) {
            category = Category(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_CATEGORY_ID)),
                name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_CATEGORY_NAME))
            )
            categoryList.add(category)
        }
        cursor.close()
        db.close()
        return categoryList
    }


    fun updateUserStatusByEmail(email: String, newStatus: Int) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USER_STATUS, newStatus)
        db.update(
            TABLE_USERS,                 // The table to update
            values,                       // The new values to update
            "$COLUMN_USER_EMAIL = ?",     // WHERE clause: filter by email
            arrayOf(email)               // The value for the filter (email)
        )
        db.close()
    }

    fun updateProfile(email: String, name: String, password: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USER_NAME, name)
        values.put(COLUMN_USER_PASSWORD, password)

        db.update(
            TABLE_USERS,
            values,
            "$COLUMN_USER_EMAIL = ?",
            arrayOf(email)
        )
        db.close()
    }

    fun findUserByStatus(status: Int): User? {
        val db = readableDatabase
        var user: User? = null

        val sql = "SELECT * FROM $TABLE_USERS WHERE $COLUMN_USER_STATUS = $status"


        val cursor = db.rawQuery(sql, null)

        if (cursor.moveToFirst()) {
            // Retrieve user details from the cursor
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_NAME))
            val email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_EMAIL))
            user = User(id, name, email)

        }
        cursor.close()
        db.close()
        return user
    }

    fun getMonthlyTotalExpense(month: String): Double {
        val db = this.readableDatabase

        val query = "SELECT SUM($COLUMN_EXPENSE_AMOUNT)" +
                " FROM $TABLE_EXPENSE" +
                " WHERE strftime('%Y-%m', $COLUMN_EXPENSE_DATE) = '${convertToYearMonth(month)}'"

        val cursor = db.rawQuery(query, null)
        var totalAmount = 0.0
        if (cursor.moveToFirst()) {
            totalAmount = cursor.getDouble(0)
        }
        cursor.close()
        db.close()
        return totalAmount
    }

    fun getMonthlyTotalIncome(month: String): Double {
        val db = this.readableDatabase

        val query = "SELECT SUM($COLUMN_INCOME_AMOUNT)" +
                " FROM $TABLE_INCOME" +
                " WHERE strftime('%Y-%m', $COLUMN_INCOME_DATE) = '${convertToYearMonth(month)}'"

        val cursor = db.rawQuery(query, null)
        var totalAmount = 0.0
        if (cursor.moveToFirst()) {
            totalAmount = cursor.getDouble(0)
        }
        cursor.close()
        db.close()
//        Log.println(Log.ASSERT, "myapp", "total $query")
        return totalAmount
    }
}

fun convertToYearMonth(input: String): String {
    // Split the input into month and year components
    val parts = input.split("/")
    val month = parts[0]
    val year = "20${parts[1]}" // Assuming all dates are in 21st century

    // Return the formatted string as YYYY-MM
    return "$year-$month"
}
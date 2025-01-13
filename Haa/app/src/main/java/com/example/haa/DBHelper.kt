package com.example.haa

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.haa.datamodel.User
import com.google.android.material.tabs.TabLayout.Tab

class DBHelper(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,1) {
    companion object{
        private const val DATABASE_NAME = "Spendly"
        //user table
        const val TABLE_USERS ="User"
        const val COLUMN_USER_ID = "id"
        const val COLUMN_USER_NAME = "name"
        const val COLUMN_USER_EMAIL = "email"
        const val COLUMN_USER_PASSWORD = "password"
        //income table
        const val TABLE_INCOME ="Income"
        const val COLUMN_INCOME_CATEGORY = "category"
        const val COLUMN_INCOME_AMOUNT = "amount"
        const val COLUMN_INCOME_DATE = "date"
        //Expenses table
        const val TABLE_EXPENSE ="Income"
        const val COLUMN_EXPENSE_CATEGORY = "category"
        const val COLUMN_EXPENSE_AMOUNT = "amount"
        const val COLUMN_EXPENSE_DATE = "date"
        const val COLUMN_EXPENSE_NOTE = "note"

    }
    override fun onCreate(db: SQLiteDatabase?) {
        // Create Users table
        val createUsersTable = ("CREATE TABLE $TABLE_USERS ("
                + "$COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_USER_NAME TEXT, "
                + "$COLUMN_USER_EMAIL TEXT, "
                + "$COLUMN_USER_PASSWORD TEXT)")
        db?.execSQL(createUsersTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
    }
    // Insert User
    fun insertUser(name: String, email: String, password: String){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_USER_NAME, name)
        contentValues.put(COLUMN_USER_EMAIL, email)
        contentValues.put(COLUMN_USER_PASSWORD, password)
        db.insert(TABLE_USERS, null, contentValues)
        db.close()
    }
    fun findUserByEmail(email:String):User?{
        val db=this.readableDatabase
        val record=db.rawQuery("SELECT * FROM $TABLE_USERS WHERE email='$email'",null)
        var user: User? = null
        if (record.moveToNext()){
            user = User(
                id = record.getInt(record.getColumnIndexOrThrow(COLUMN_USER_ID)),
                name = record.getString(record.getColumnIndexOrThrow(COLUMN_USER_NAME)),
                email = record.getString(record.getColumnIndexOrThrow(COLUMN_USER_EMAIL))

            )
        }
        return user
    }
    fun validateLoginEmailPassword(email:String,password:String):User?{
        val db=this.readableDatabase
        val record=db.rawQuery("SELECT * FROM $TABLE_USERS WHERE email='$email' AND password='$password'",null)
        var user: User? = null
        if (record.moveToNext()){
            user = User(
                id = record.getInt(record.getColumnIndexOrThrow(COLUMN_USER_ID)),
                name = record.getString(record.getColumnIndexOrThrow(COLUMN_USER_NAME)),
                email = record.getString(record.getColumnIndexOrThrow(COLUMN_USER_EMAIL))

            )
        }
        return user
    }
}
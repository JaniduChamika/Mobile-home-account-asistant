package com.example.haa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.haa.datamodel.User

class LoginInterface : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_interface)
        val createAccountBtn = findViewById<TextView>(R.id.createAcountText)
        createAccountBtn.setOnClickListener {
            goToRegisterScreen()
        }

        val loginButton = findViewById<TextView>(R.id.loginButton)
        loginButton.setOnClickListener {
            validateLogin()
        }
    }

    private fun goToRegisterScreen() {
        val myintent = Intent(this, Registration::class.java)
        startActivity(myintent)
    }

    private fun goToHomeScreen(email: String, name: String) {
        val myintent = Intent(this, MainActivity::class.java)
        myintent.putExtra("email", email)
        myintent.putExtra("name", name)
        startActivity(myintent)
    }

    private fun validateLogin() {
        val dbHelper = DBHelper(applicationContext)
        val email = findViewById<EditText>(R.id.editEmail).text
        val password = findViewById<EditText>(R.id.editPassword).text
        if (email.isEmpty()) {
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT)
                .show()
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT)
                .show()
        } else if (dbHelper.findUserByEmail(email.toString()) == null) {
            Toast.makeText(this, "Not registered email", Toast.LENGTH_SHORT)
                .show()
        } else {
            val user: User? =
                dbHelper.validateLoginEmailPassword(email.toString(), password.toString())
            if (user == null) {
                Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT)
                    .show()
            } else {
                goToHomeScreen(user.email,user.name)
            }
        }
    }
}
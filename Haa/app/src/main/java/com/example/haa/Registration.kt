package com.example.haa

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)

        val signupButton = findViewById<TextView>(R.id.signupButton)
        signupButton.setOnClickListener {
            saveUserDetails()
        }

        val loginButton = findViewById<TextView>(R.id.loginText)
        loginButton.setOnClickListener {
            goToLoginScreen()
        }
    }

    private fun goToLoginScreen() {
        val myintent = Intent(this, LoginInterface::class.java)
        startActivity(myintent)
    }

    private fun saveUserDetails() {
        val dbHelper = DBHelper(applicationContext)
        val name = findViewById<EditText>(R.id.editName).text
        val email = findViewById<EditText>(R.id.editEmail).text
        val password = findViewById<EditText>(R.id.editPassword).text
        val compassword = findViewById<EditText>(R.id.editComPassword).text
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (name.isEmpty()) {
            Toast.makeText(this, "Please Enter your Name", Toast.LENGTH_SHORT)
                .show()
        } else if (email.isEmpty()) {
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT)
                .show()
        } else if (!email.matches(emailPattern.toRegex())) {
            Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT)
                .show()
        }else if (password.isEmpty()) {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT)
                .show()
        }else if (password.toString().length<4) {
            Toast.makeText(this, "Password must be at least 4 characters", Toast.LENGTH_SHORT)
                .show()
        }  else if (!password.toString().equals(compassword.toString())) {
            Toast.makeText(this, "Password and Confirm password must be same", Toast.LENGTH_SHORT)
                .show()
        } else if (dbHelper.findUserByEmail(email.toString()) != null) {
            Toast.makeText(this, "Already registered email", Toast.LENGTH_SHORT)
                .show()
        } else {
            dbHelper.insertUser(name.toString(),email.toString(),password.toString())
            goToLoginScreen()
        }
    }

}
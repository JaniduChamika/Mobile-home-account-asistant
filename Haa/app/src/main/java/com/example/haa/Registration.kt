package com.example.haa

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)

        val signupButton=findViewById<TextView>(R.id.signupButton)
        signupButton.setOnClickListener {
            goToLoginScreen()
        }

        val loginButton=findViewById<TextView>(R.id.loginText)
        loginButton.setOnClickListener {
            goToLoginScreen()
        }
    }
    private fun goToLoginScreen(){
        val  myintent= Intent(this,LoginInterface::class.java)
        startActivity(myintent)
    }

}
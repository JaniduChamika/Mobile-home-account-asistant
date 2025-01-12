package com.example.haa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class LoginInterface : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_interface)
        val createAccountBtn=findViewById<TextView>(R.id.createAcountText)
        createAccountBtn.setOnClickListener {
            goToRegisterScreen()
        }

        val loginButton=findViewById<TextView>(R.id.loginButton)
        loginButton.setOnClickListener {
            goToHomeScreen()
        }
    }
    private fun goToRegisterScreen(){
       val  myintent=Intent(this,Registration::class.java)
        startActivity(myintent)
    }
    private fun goToHomeScreen(){
        val  myintent=Intent(this,MainActivity::class.java)
        startActivity(myintent)
    }
}
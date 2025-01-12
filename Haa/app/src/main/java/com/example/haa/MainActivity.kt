package com.example.haa


import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment(Home())

        val homeButon = findViewById<ImageButton>(R.id.homeButton)
        homeButon.setOnClickListener {
            openFragment(Home())
        }
        val addNewButton = findViewById<ImageButton>(R.id.addExpensesButton)
        addNewButton.setOnClickListener {
            openFragment(AddNew())
        }

    }

    private fun openFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


}
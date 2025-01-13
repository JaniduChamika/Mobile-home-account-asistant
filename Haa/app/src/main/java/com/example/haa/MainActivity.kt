package com.example.haa


import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val myintent= intent
//        val userName=myintent.getStringExtra("name")
//        val userEmail=myintent.getStringExtra("email")
//        val bundle=Bundle()
//        bundle.putString("name",userName)
//        bundle.putString("email",userEmail)
        val bundle=Bundle()
        bundle.putString("name","Janidu")
        bundle.putString("email","email")
        openHomeFragment(Home(),bundle)

        val homeButon = findViewById<ImageButton>(R.id.homeButton)
        homeButon.setOnClickListener {
            openHomeFragment(Home(),bundle)
        }
        val addNewButton = findViewById<ImageButton>(R.id.addExpensesButton)
        addNewButton.setOnClickListener {
            openFragment(AddNew())
        }
        val historyButton = findViewById<ImageButton>(R.id.historyButton)
        historyButton.setOnClickListener {
            openFragment(ViewHistory())
        }

        val menuButton = findViewById<ImageButton>(R.id.menuButton)
        menuButton.setOnClickListener {
            openFragment(MoreMenu())
        }
    }
    private fun openFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
    private fun openHomeFragment(fragment: Fragment,bundle: Bundle) {

        fragment.arguments=bundle
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}
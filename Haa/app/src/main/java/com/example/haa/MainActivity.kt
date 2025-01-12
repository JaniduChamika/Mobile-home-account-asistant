package com.example.haa

<<<<<<< Updated upstream
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
=======
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openHomeFragment(home())

        val homeButon=findViewById<ImageButton>(R.id.homeButton)
        homeButon.setOnClickListener {
            openHomeFragment(home())
        }
    }
    private fun openHomeFragment(fragment: Fragment){
        val fragmentTransaction:FragmentTransaction=supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container,fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
>>>>>>> Stashed changes
    }
}
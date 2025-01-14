package com.example.haa

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.FragmentTransaction


class MoreMenu : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_more_menu, container, false)
        val categoryButton = view.findViewById<LinearLayout>(R.id.categoryButton)
        categoryButton.setOnClickListener {
            openFragment(CategoryAdd())
        }

        val logoutButton = view.findViewById<LinearLayout>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            showConfirmationDialog(requireContext())
        }
        val contactUsButton = view.findViewById<LinearLayout>(R.id.contactUsButton)
        contactUsButton.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder
                .setMessage("Email: spendly@info.com \nVisit at: www.SpendlyApp.com")
                .setTitle("Contact Us")
                .setNegativeButton("Ok") { dialog, which ->
                    dialog.dismiss()
                }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        val profileButton = view.findViewById<LinearLayout>(R.id.profileButton)
        profileButton.setOnClickListener {
            openFragment(ProfileView())
        }

        return view
    }

    private fun openFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun showConfirmationDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Logout")
        builder.setMessage("Are you sure you want to logout?")

        builder.setPositiveButton("Confirm") { dialog, _ ->
            logout()
            dialog.dismiss() // Close the dialog
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss() // Close the dialog
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun logout() {
        val dbHelper = DBHelper(requireContext().applicationContext)
        val user = dbHelper.findUserByStatus(1)
        if (user != null) {
            dbHelper.updateUserStatusByEmail(user.email, 0)
            val myIntent = Intent(requireContext().applicationContext, LoginInterface::class.java)
            startActivity(myIntent)
        }
    }
}
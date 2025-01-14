package com.example.haa

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction


class ProfileView : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile_view, container, false)
        val nameEdit = view.findViewById<EditText>(R.id.nameEditText)
        val emailEdit = view.findViewById<EditText>(R.id.emailEditText)
        val updateButton = view.findViewById<Button>(R.id.updateButton)

        val dbHelper = DBHelper(requireContext().applicationContext)
        val user = dbHelper.findUserByStatus(1)
        if (user != null) {
            nameEdit.setText(user.name)
            emailEdit.setText(user.email)
        }

        updateButton.setOnClickListener {
            updateProfile(view)
        }
        return view
    }

    private fun updateProfile(view: View) {
        val nameEdit = view.findViewById<EditText>(R.id.nameEditText).text
        val emailEdit = view.findViewById<EditText>(R.id.emailEditText).text
        val passwordEdit = view.findViewById<EditText>(R.id.passwordEditText).text
        val passwordComfirmEdit = view.findViewById<EditText>(R.id.passwordComEditText).text
        if (nameEdit.isEmpty()) {
            Toast.makeText(requireContext(), "Please Enter your Name", Toast.LENGTH_SHORT)
                .show()
        } else if (passwordEdit.isEmpty()) {
            Toast.makeText(requireContext(), "Please Enter Password", Toast.LENGTH_SHORT)
                .show()
        } else if (passwordEdit.toString().length < 4) {
            Toast.makeText(
                requireContext(),
                "Password must be at least 4 characters",
                Toast.LENGTH_SHORT
            )
                .show()
        }  else if (passwordEdit.toString() != passwordComfirmEdit.toString()) {
            Toast.makeText(requireContext(), "Password and Confirm password must be same", Toast.LENGTH_SHORT)
                .show()
        } else {
            val dbHelper = DBHelper(requireContext().applicationContext)
            dbHelper.updateProfile(
                emailEdit.toString(),
                nameEdit.toString(),
                passwordEdit.toString()
            )
            openFragment(MoreMenu())
        }

    }

    private fun openFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
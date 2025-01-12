package com.example.haa

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction


class AddNew : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_new, container, false)
        val incomeButton = view.findViewById<Button>(R.id.incomeButton)
        val expensesButton = view.findViewById<Button>(R.id.expensesButton)
        incomeButton.setOnClickListener {
            expensesButton.setBackgroundResource(R.drawable.default_white_button)
            expensesButton.setTextColor(Color.BLACK)
            incomeButton.setBackgroundResource(R.drawable.default_theam_button)
            incomeButton.setTextColor(Color.WHITE)
            openExpenseTab(AddIncome())
        }
        expensesButton.setOnClickListener {
            incomeButton.setBackgroundResource(R.drawable.default_white_button)
            incomeButton.setTextColor(Color.BLACK)
            expensesButton.setBackgroundResource(R.drawable.default_theam_button)
            expensesButton.setTextColor(Color.WHITE)
            openExpenseTab(AddExpenses())

        }
        openExpenseTab(AddExpenses())
        return view
    }

    private fun openExpenseTab(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.exInFragementContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}
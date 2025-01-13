package com.example.haa

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction


class ViewHistory : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_view_history, container, false)
        val incomeButton = view.findViewById<Button>(R.id.incomeButton)
        val expensesButton = view.findViewById<Button>(R.id.expensesButton)
        incomeButton.setOnClickListener {
            expensesButton.setBackgroundResource(R.drawable.default_white_button)
            expensesButton.setTextColor(Color.BLACK)
            incomeButton.setBackgroundResource(R.drawable.default_theam_button)
            incomeButton.setTextColor(Color.WHITE)
            openFinanceHistoryTab(ViewIncomeHistory())
        }
        expensesButton.setOnClickListener {
            incomeButton.setBackgroundResource(R.drawable.default_white_button)
            incomeButton.setTextColor(Color.BLACK)
            expensesButton.setBackgroundResource(R.drawable.default_theam_button)
            expensesButton.setTextColor(Color.WHITE)
            openFinanceHistoryTab(ViewExpensesHistory())

        }
        openFinanceHistoryTab(ViewExpensesHistory())
        return view
    }
    private fun openFinanceHistoryTab(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.historyFragementContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}
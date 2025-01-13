package com.example.haa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class Home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        loadUserName(view)
        loadSummaryBox(view)
        return view
    }

    fun loadSummaryBox(view: View) {
        val dbhelper = DBHelper(requireContext().applicationContext)
        val totalExpense = dbhelper.getTotalExpenses()
        val expenseText = view.findViewById<TextView>(R.id.textTotalExpense)
        expenseText.text = "Rs. " + String.format("%,.2f", totalExpense)

        val totalIncome = dbhelper.getTotalIncome()
        val incomeText = view.findViewById<TextView>(R.id.textTotalIncome)
        incomeText.text = "Rs. " + String.format("%,.2f", totalIncome)

        val balaceText = view.findViewById<TextView>(R.id.textBalance)
        balaceText.text = "Rs. " + String.format("%,.2f", (totalIncome - totalExpense))

        val monthlyIncomeText=view.findViewById<TextView>(R.id.textMonthlyIncome)
        val monthlyExpense=dbhelper.getTotalExpensesForCurrentMonth()
        val monthlyExpenseText=view.findViewById<TextView>(R.id.textMonthlyExpense)
        monthlyExpenseText.text="Rs. "+ String.format("%,.2f", monthlyExpense)
        val monthlyIncome=dbhelper.getTotalIncomeForCurrentMonth()
        monthlyIncomeText.text="Rs. "+ String.format("%,.2f", monthlyIncome)

    }

    fun loadUserName(view: View) {
        val name = arguments?.getString("name")
        view.findViewById<TextView>(R.id.textUsername).text = "Hi! $name"

    }

}
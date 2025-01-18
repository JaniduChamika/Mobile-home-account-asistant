package com.example.haa

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import com.example.haa.datamodel.Expense
import com.example.haa.datamodel.Income
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class ViewHistory : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_history, container, false)
        val incomeButton = view.findViewById<Button>(R.id.incomeButton)
        val expensesButton = view.findViewById<Button>(R.id.expensesButton)
        val fromDate = view.findViewById<EditText>(R.id.fromDateEdit)
        val toDate = view.findViewById<EditText>(R.id.toDateEdit)


        var incomeBtnSelected = false
        incomeButton.setOnClickListener {
            expensesButton.setBackgroundResource(R.drawable.default_white_button)
            expensesButton.setTextColor(Color.BLACK)
            incomeButton.setBackgroundResource(R.drawable.default_theam_button)
            incomeButton.setTextColor(Color.WHITE)
            openFinanceHistoryTab(ViewIncomeHistory())
            incomeBtnSelected = true
            toDate.setText("")
            fromDate.setText("")
        }
        expensesButton.setOnClickListener {
            incomeButton.setBackgroundResource(R.drawable.default_white_button)
            incomeButton.setTextColor(Color.BLACK)
            expensesButton.setBackgroundResource(R.drawable.default_theam_button)
            expensesButton.setTextColor(Color.WHITE)
            openFinanceHistoryTab(ViewExpensesHistory())
            incomeBtnSelected = false
            toDate.setText("")
            fromDate.setText("")
        }


        val calendar = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val formattedDate = dateFormat.format(calendar.time)
            fromDate.setText(formattedDate)
            val bundle = Bundle()
            bundle.putString("from", fromDate.text?.toString())
            bundle.putString("to", toDate.text?.toString())
            if (incomeBtnSelected) {
                openFinanceHistoryTab(ViewIncomeHistory(), bundle)
            } else {
                openFinanceHistoryTab(ViewExpensesHistory(), bundle)
            }
        }
        val dateSetListener2 = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val formattedDate = dateFormat.format(calendar.time)
            toDate.setText(formattedDate)
            val bundle = Bundle()
            bundle.putString("from", fromDate.text?.toString())
            bundle.putString("to", toDate.text?.toString())
            if (incomeBtnSelected) {
                openFinanceHistoryTab(ViewIncomeHistory(), bundle)
            } else {
                openFinanceHistoryTab(ViewExpensesHistory(), bundle)
            }
        }
        fromDate.setOnClickListener {
            showDatePicker(calendar, dateSetListener)
        }
        toDate.setOnClickListener {
            showDatePicker(calendar, dateSetListener2)
        }
        openFinanceHistoryTab(ViewExpensesHistory())
        return view
    }

    private fun showDatePicker(
        calendar: Calendar,
        dateSetListener: DatePickerDialog.OnDateSetListener
    ) {
        DatePickerDialog(
            requireContext(),
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun openFinanceHistoryTab(fragment: Fragment, bundle: Bundle) {
        fragment.arguments = bundle
        val fragmentTransaction: FragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.historyFragementContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun openFinanceHistoryTab(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.historyFragementContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
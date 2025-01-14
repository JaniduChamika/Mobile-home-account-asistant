package com.example.haa

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class AddExpenses : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_expenses, container, false)
        //date chooser start
        val datePiker = view.findViewById<EditText>(R.id.editTextDate)
        val calendar = Calendar.getInstance()
        val saveExpenseButton = view.findViewById<Button>(R.id.saveExpenseButton)
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            // Format the selected date
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val formattedDate = dateFormat.format(calendar.time)
            datePiker.setText(formattedDate)
        }
        datePiker.setOnClickListener {
            showDatePicker(calendar, dateSetListener)
        }
        saveExpenseButton.setOnClickListener {
            saveExpense(view)
        }
        //date chooser end
        val spinner: Spinner = view.findViewById(R.id.categorySpinner)
        val dbHelper = DBHelper(requireContext().applicationContext)
        val expnesCategory = dbHelper.getAllExpenseCategory()
        val options = arrayListOf(
            "Select Category"
        )
        if (expnesCategory != null) {
            options.addAll(expnesCategory.map { it.name })
        }
        val adapter =
            ArrayAdapter(requireContext(), R.layout.custom_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.setSelection(0)

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

    private fun saveExpense(view: View) {
        val dbHelper = DBHelper(requireContext().applicationContext)
        val category = view.findViewById<Spinner>(R.id.categorySpinner)
        val inputDate = view.findViewById<EditText>(R.id.editTextDate)
        val amount = view.findViewById<EditText>(R.id.expenseAmountEditText)
        val note = view.findViewById<EditText>(R.id.noteEditText)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val parsedInputDate = dateFormat.parse(inputDate.text.toString())
        val today = Calendar.getInstance().time
        if (category.selectedItemPosition == 0) {
            Toast.makeText(requireContext(), "Please Select Category", Toast.LENGTH_SHORT)
                .show()
        } else if (inputDate.text.isEmpty()) {
            Toast.makeText(requireContext(), "Please Select Date", Toast.LENGTH_SHORT)
                .show()
        } else if (parsedInputDate != null && parsedInputDate > today) {
            Toast.makeText(
                view.context,
                "Date should not greater than today",
                Toast.LENGTH_SHORT
            ).show()
        } else if (amount.text.isEmpty()) {
            Toast.makeText(requireContext(), "Please Enter Amount", Toast.LENGTH_SHORT)
                .show()
        } else if (note.text.isEmpty()) {
            Toast.makeText(
                requireContext(),
                "Please Enter Note about you expense",
                Toast.LENGTH_SHORT
            )
                .show()
        } else {
            dbHelper.insertExpense(
                category.selectedItem.toString(),
                inputDate.text.toString(),
                amount.text.toString().toDouble(),
                note.text.toString()
            )
            category.setSelection(0)
            inputDate.setText("")
            amount.setText("")
            note.setText("")
        }
    }
}
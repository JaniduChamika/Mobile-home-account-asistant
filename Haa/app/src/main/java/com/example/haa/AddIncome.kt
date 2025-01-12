package com.example.haa

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.FragmentManager
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class AddIncome : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_income, container, false)

        //date chooser start
        val datePiker = view.findViewById<EditText>(R.id.editTextDate)
        val calendar = Calendar.getInstance()

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
        //date chooser end

        val spinner: Spinner = view.findViewById(R.id.mySpinner)
        val options = arrayListOf(
            "Select an option",
            "Option 1",
            "Option 2",
            "Option 3",
            "Option 4",
            "Option 5",
            "Option 6"
        )
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, options)
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

}
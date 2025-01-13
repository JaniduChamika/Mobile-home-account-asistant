package com.example.haa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.haa.datamodel.Income

class ViewIncomeHistory : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_income_history, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        //data load start
        val dbHelper = DBHelper(requireContext().applicationContext)
        val historyItemLists: List<Income>? = dbHelper.getAllIncomes()
        // Create adapter and set it to RecyclerView
        val adapter = HistoryItemAdapterIncome(historyItemLists)
        recyclerView.adapter = adapter

        // Set the layout manager
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        //data load start
        return view
    }


}
package com.example.haa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewIncomeHistory : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_view_income_history, container, false)
        val recyclerView: RecyclerView =view.findViewById(R.id.recyclerView)

        // Sample data
        val historyItemLists = listOf(
            HistoryItemIncome("HistoryItem income 1", "Rs 1000","2025-09-02"),
            HistoryItemIncome("HistoryItem income 2", "Rs 2000","2025-09-02"),
            HistoryItemIncome("HistoryItem 3", "Rs 3000","2025-09-02"),
            HistoryItemIncome("HistoryItem 4", "Rs 4000","2025-09-02")
        )

        // Create adapter and set it to RecyclerView
        val adapter = HistoryItemAdapterIncome(historyItemLists)
        recyclerView.adapter = adapter

        // Set the layout manager
        recyclerView.layoutManager = LinearLayoutManager(view.context)
//        history item end
        return view
    }


}
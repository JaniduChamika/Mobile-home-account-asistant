package com.example.haa

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryItemViewHolderIncome(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.findViewById(R.id.Textcategory)
    val date: TextView = view.findViewById(R.id.Textdate)
    val price: TextView = view.findViewById(R.id.amount)
}
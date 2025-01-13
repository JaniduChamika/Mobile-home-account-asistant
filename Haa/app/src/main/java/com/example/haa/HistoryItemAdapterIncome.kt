package com.example.haa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HistoryItemAdapterIncome (private val historyItems: List<HistoryItemIncome>) : RecyclerView.Adapter<HistoryItemViewHolderIncome>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolderIncome {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item_income_layout, parent, false)
        return HistoryItemViewHolderIncome(view)
    }

    override fun onBindViewHolder(holder: HistoryItemViewHolderIncome, position: Int) {
        val item = historyItems[position]
        holder.title.text = item.title
        holder.price.text = item.price
        holder.date.text=item.date

    }

    override fun getItemCount(): Int {
        return historyItems.size
    }



}
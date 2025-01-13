package com.example.haa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.haa.datamodel.Expense

class HistoryItemAdapter(private val historyItems: List<Expense>?) : RecyclerView.Adapter<HistoryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item_layout, parent, false)
        return HistoryItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
        val item = historyItems?.get(position)
        holder.title.text = item?.category
        holder.price.text = String.format("%.2f",item?.amount)
        holder.date.text=item?.date
        holder.note.text=item?.note
        holder.expandHeader.setOnClickListener {
            toggleExpand(holder.expandableContent, holder.expandIcon)
        }
    }

    override fun getItemCount(): Int {
        return historyItems?.size?:0
    }
    private fun toggleExpand(expandableContent: LinearLayout, expandIcon: ImageView) {
        if (expandableContent.visibility == View.VISIBLE) {
            // Collapse the content
            expandableContent.visibility = View.GONE
            expandIcon.setImageResource(android.R.drawable.arrow_down_float)
        } else {
            // Expand the content
            expandableContent.visibility = View.VISIBLE
            expandIcon.setImageResource(android.R.drawable.arrow_up_float)
        }
    }


}
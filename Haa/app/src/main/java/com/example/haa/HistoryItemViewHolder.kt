package com.example.haa

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryItemViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.findViewById(R.id.Textcategory)
    val date: TextView = view.findViewById(R.id.Textdate)
    val price: TextView = view.findViewById(R.id.amount)
    val note: TextView = view.findViewById(R.id.noteContent)
    val expandHeader: LinearLayout = view.findViewById(R.id.expandHeader)
    val expandIcon: ImageView = view.findViewById(R.id.expandIcon)
    val expandableContent: LinearLayout = view.findViewById(R.id.expandableContent)
}
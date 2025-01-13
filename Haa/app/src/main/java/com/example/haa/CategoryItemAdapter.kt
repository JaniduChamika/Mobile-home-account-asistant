package com.example.haa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.haa.datamodel.Category

class CategoryItemAdapter (private val categoryList: List<Category>) : RecyclerView.Adapter<CategoryItemAdapter.CategoryViewHolder>() {

    // ViewHolder class to hold item views
    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.findViewById(R.id.textViewCategoryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_layout, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentCategory = categoryList?.get(position)
        holder.categoryName.text = currentCategory?.name
    }

    override fun getItemCount(): Int {
        return categoryList?.size?:0
    }
}
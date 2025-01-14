package com.example.haa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.haa.datamodel.Category
import com.example.haa.datamodel.Income

class CategoryAdd : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_category_add, container, false)
        loadCategory(view)
        val addCategoryButton = view.findViewById<Button>(R.id.addCategoryButton)
        addCategoryButton.setOnClickListener {
            saveCategory(view)
        }
        return view
    }

    private fun saveCategory(view: View) {
        val dbHelper = DBHelper(requireContext().applicationContext)
        val category = view.findViewById<EditText>(R.id.categoryEditText)
        val radioBtnExpense = view.findViewById<RadioButton>(R.id.radioButtonExpense)

        if (category.text.isEmpty()) {
            Toast.makeText(requireContext(), "Please Enter Category Name", Toast.LENGTH_SHORT)
                .show()
        } else {
            if (radioBtnExpense.isChecked) {
                dbHelper.insertExCategory(category.text.toString())
            } else {
                dbHelper.insertInCategory(category.text.toString())
            }

            openFragment(CategoryAdd())

        }
    }

    private fun loadCategory(view: View) {
        val recyclerExpenseView: RecyclerView = view.findViewById(R.id.categoryExpenseRecyclerView)
        val recyclerIncomeView: RecyclerView = view.findViewById(R.id.categoryIncomeRecyclerView)

        //data load start
        val dbHelper = DBHelper(requireContext().applicationContext)
        val itemListsIncome: List<Category>? = dbHelper.getAllIncomeCategory()
        if (itemListsIncome != null) {
            val adapterIncome = CategoryItemAdapter(itemListsIncome)
            recyclerIncomeView.adapter = adapterIncome
            recyclerIncomeView.layoutManager = LinearLayoutManager(view.context)

        }
        val itemLists: List<Category>? = dbHelper.getAllExpenseCategory()
        if (itemLists != null) {
            val adapterExpense = CategoryItemAdapter(itemLists)
            recyclerExpenseView.adapter = adapterExpense
            recyclerExpenseView.layoutManager = LinearLayoutManager(view.context)
        }

        //data load start
    }

    private fun openFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}
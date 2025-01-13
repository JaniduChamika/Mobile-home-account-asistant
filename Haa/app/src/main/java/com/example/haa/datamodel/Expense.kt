package com.example.haa.datamodel

import java.util.Date

data class Expense (
    val id: Int,
    val category: String,
    val date: String,
    val amount:Double,
    val note:String
)
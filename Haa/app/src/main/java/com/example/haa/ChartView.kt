package com.example.haa

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.util.Calendar


class ChartView : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_chart_view, container, false)

        val lineChart: LineChart = view.findViewById(R.id.lineChart)

        // Sample Data: Monthly Expenses and Income
        val months = mutableListOf<String>()

        val calendar = Calendar.getInstance()
        for (i in 0 until 12) {
            val month = calendar.get(Calendar.MONTH) + 1  // Calendar.MONTH is 0-based
            val year = calendar.get(Calendar.YEAR) % 100  // Get last two digits of the year
            months.add(String.format("%02d/%02d", month, year))

            // Move the calendar back one month
            calendar.add(Calendar.MONTH, -1)


        }
        months.reverse()
        // Expenses data (date, amount)
        val expenseData = mutableListOf<Pair<String, Double>>()
        val dbhelper=DBHelper(requireContext().applicationContext)
        for (month in months) {

            expenseData.add(Pair(month,dbhelper.getMonthlyTotalExpense(month)))
        }

//        expenseData.add(Pair("01/25", 1000.0))
//        expenseData.add(Pair("02/25", 1400.0))
//        expenseData.add(Pair("03/25", 1200.0))

        // Income data (date, amount)
        val incomeData = mutableListOf<Pair<String, Double>>()
        for (month in months) {
            incomeData.add(Pair(month,dbhelper.getMonthlyTotalIncome(month) ))
        }


        // Check if there is enough data (at least 2 months of data)
        if (expenseData.size < 2 && incomeData.size < 2) {
//            Toast.makeText(requireContext(), "At least 2 months of data required", Toast.LENGTH_SHORT).show()
        } else {
            // Create entries for the chart
            val expenseEntries = createEntriesForChart(expenseData, months)
            val incomeEntries = createEntriesForChart(incomeData, months)

            // Create LineDataSets
            val expenseDataSet = LineDataSet(expenseEntries, "Expenses")
            val incomeDataSet = LineDataSet(incomeEntries, "Income")

            // Customize the line chart
            setupLineChart(lineChart, expenseDataSet, incomeDataSet, months)

            // Add data to the chart
            val lineData = LineData(expenseDataSet, incomeDataSet)
            lineChart.data = lineData

            // Refresh chart
            lineChart.invalidate()
        }

        return view
    }

    private fun createEntriesForChart(
        data: List<Pair<String, Double>>,
        months: List<String>
    ): List<Entry> {
        val entries = mutableListOf<Entry>()
        for (i in months.indices) {
            val monthYear = months[i]
            val amount = data.find { it.first == monthYear }?.second ?: 0.0
            entries.add(Entry(i.toFloat(), amount.toFloat()))
        }
        return entries
    }

    // Function to set up the LineChart with customization
    private fun setupLineChart(
        lineChart: LineChart,
        expenseDataSet: LineDataSet,
        incomeDataSet: LineDataSet,
        months: List<String>
    ) {
        expenseDataSet.color = Color.parseColor("#BB0000")
        expenseDataSet.valueTextColor = Color.BLACK
        expenseDataSet.setDrawFilled(true)
        expenseDataSet.fillColor = Color.parseColor("#BB0000")
        expenseDataSet.lineWidth = 2f
        expenseDataSet.setDrawValues(true)

        incomeDataSet.color = Color.parseColor("#00A006")
        incomeDataSet.valueTextColor = Color.BLACK
        incomeDataSet.setDrawFilled(true)
        incomeDataSet.fillColor = Color.parseColor("#00A006")
        incomeDataSet.lineWidth = 2f
        incomeDataSet.setDrawValues(true)

        // Customize X-Axis
        val xAxis: XAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.valueFormatter = XAxisFormatter(months)

        // Customize Y-Axis
        lineChart.axisLeft.setDrawGridLines(true)
        lineChart.axisRight.isEnabled = false

        // Set chart description
        lineChart.description.isEnabled = false

        // Enable touch gestures
        lineChart.setTouchEnabled(true)
        lineChart.isDragEnabled = true
        lineChart.setScaleEnabled(true)

        // Add animation for chart rendering
        lineChart.animateXY(1500, 1500, Easing.EaseInOutQuad)

        // Customize the legend programmatically
        val legend: Legend = lineChart.legend
        legend.isEnabled = true
        legend.form = Legend.LegendForm.LINE
        legend.textColor = Color.BLACK
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
    }

    // Custom formatter to format X-axis labels as "MM-yyyy"
    private class XAxisFormatter(val months: List<String>) :
        com.github.mikephil.charting.formatter.ValueFormatter() {
        override fun getFormattedValue(value: Float): String {
            val index = value.toInt()
            return if (index >= 0 && index < months.size) {
                months[index]
            } else {
                ""
            }
        }
    }
}
package com.example.agecalc
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.btnDatePicker)
        button.setOnClickListener{ clickDatePicker(it) }
    }

    fun clickDatePicker(view : View) {

        val myCalender = Calendar.getInstance()
        var year = myCalender.get(Calendar.YEAR)
        var month = myCalender.get(Calendar.MONTH)
        var dayOfMonth = myCalender.get(Calendar.DAY_OF_MONTH)
        val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)

        var dpd = DatePickerDialog(
            this, DatePickerDialog.OnDateSetListener {view, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                tvSelectedDate.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)
                val selectedDateInYears = selectedYear
                val selectedDateInMonths = theDate!!.time / 2629746000
                val selectedDateInWeeks = theDate.time / 604800000
                val selectedDateInDays = theDate.time / 86400000
                val selectedDateInMinutes = theDate.time / 60000
                val selectedDateInSeconds = theDate.time / 1000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInYears = year
                val currentDateInMonths = currentDate.time / 2629746000
                val currentDateInWeeks = currentDate!!.time / 604800000
                val currentDateInDays = currentDate.time / 86400000
                val currentDateInMinutes = currentDate.time / 60000
                val currentDateInSeconds = currentDate.time / 1000

                val differenceYears = currentDateInYears - selectedDateInYears
                val differenceInMonths = currentDateInMonths - selectedDateInMonths
                val differenceInWeeks = currentDateInWeeks - selectedDateInWeeks
                val differenceInDays: Long = currentDateInDays - selectedDateInDays
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                val differenceInSeconds = currentDateInSeconds - selectedDateInSeconds

                var tvSelectedDateInYears = findViewById<TextView>(R.id.tvSelectedDateInYears)
                tvSelectedDateInYears.setText(differenceYears.toString())

                var tvSelectedDateInMonths = findViewById<TextView>(R.id.tvSelectedDateInMonths)
                tvSelectedDateInMonths.setText(differenceInMonths.toString())

                var tvSelectedDateInDays = findViewById<TextView>(R.id.tvSelectedDateInDays)
                tvSelectedDateInDays.setText(differenceInDays.toString())

                var tvSelectedDateInWeeks = findViewById<TextView>(R.id.tvSelectedDateInWeeks)
                tvSelectedDateInWeeks.setText(differenceInWeeks.toString())

                var tvSelectedDateInMinutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
                tvSelectedDateInMinutes.setText(differenceInMinutes.toString())

                var tvSelectedDateInSeconds = findViewById<TextView>(R.id.tvSelectedDateInSeconds)
                tvSelectedDateInSeconds.setText(differenceInSeconds.toString())


            }
            ,year, month, dayOfMonth)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}

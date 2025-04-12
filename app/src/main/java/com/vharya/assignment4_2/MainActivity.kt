package com.vharya.assignment4_2

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.time.Clock
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private val calendar = Calendar.getInstance()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editDate = findViewById<EditText>(R.id.edit_date)
        val editTime = findViewById<EditText>(R.id.edit_time)
        val clearButton = findViewById<Button>(R.id.clear_button)
        val saveButton = findViewById<Button>(R.id.save_button)

        editDate.setOnClickListener {
            DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)

                val dateFormat = SimpleDateFormat("dd / MM / yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)

                editDate.setText(formattedDate)
            },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
            ).show()
        }

        editTime.setOnClickListener {
            TimePickerDialog(this, { view, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedTime.set(Calendar.MINUTE, minute)

                val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                val formattedTime = timeFormat.format(selectedTime.time)

                editTime.setText(formattedTime)
            },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        }

        clearButton.setOnClickListener {
            editDate.text.clear()
            editTime.text.clear()
        }

        saveButton.setOnClickListener {
            Toast.makeText(this, "Saved Data!", Toast.LENGTH_SHORT).show()
        }
    }
}
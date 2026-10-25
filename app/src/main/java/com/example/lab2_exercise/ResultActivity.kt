package com.example.lab2_exercise

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val textNama = findViewById<TextView>(R.id.textNama)
        val textTarikh = findViewById<TextView>(R.id.textTarikh)

        val nama = intent.getStringExtra("EXTRA_NAMA") ?: ""
        val tarikhStr = intent.getStringExtra("EXTRA_TARIKH") ?: ""


        val totalChars = nama.replace(" ", "").length

        val vowelCount = nama.filter { it.lowercase() in "aeiou" }.count()

        val repeatedChars = nama.replace(" ", "")
            .groupBy { it.lowercase() }
            .filter { it.value.size > 1 }
            .keys
            .joinToString(", ")
            .ifEmpty { "Tiada" }

        textNama.text = "Nama: $nama\n" +
                "Jumlah abjad: $totalChars\n" +
                "Bilangan vokal: $vowelCount\n" +
                "Abjad berulang: $repeatedChars"

        var ageText = "-"
        var dayOfWeekText = "-"

        try {
            if (tarikhStr.isNotEmpty()) {
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val dobDate = sdf.parse(tarikhStr)

                if (dobDate != null) {
                    val dobCal = Calendar.getInstance().apply { time = dobDate }
                    val todayCal = Calendar.getInstance()

                    var age = todayCal.get(Calendar.YEAR) - dobCal.get(Calendar.YEAR)
                    if (todayCal.get(Calendar.DAY_OF_YEAR) < dobCal.get(Calendar.DAY_OF_YEAR)) {
                        age--
                    }
                    ageText = "$age tahun"

                    val dayFormat = SimpleDateFormat("EEEE", Locale("ms", "MY"))
                    dayOfWeekText = dayFormat.format(dobDate)
                }
            }
        } catch (e: Exception) {
        }

        textTarikh.text = "Umur anda: $ageText\nDilahirkan pada: $dayOfWeekText"
    }
}
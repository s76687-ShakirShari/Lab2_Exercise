package com.example.lab2_exercise

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editNama = findViewById<EditText>(R.id.editNama)
        val editTarikh = findViewById<EditText>(R.id.editTarikh)
        val btnAnalisis = findViewById<Button>(R.id.btnAnalisis)

        btnAnalisis.setOnClickListener {
            val nama = editNama.text.toString().trim()
            val tarikh = editTarikh.text.toString().trim()

            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("EXTRA_NAMA", nama)
                putExtra("EXTRA_TARIKH", tarikh)
            }
            startActivity(intent)
        }
    }
}
package com.example.foodgenie

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Activity_order_summary : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order_summary)

        val tvSummary = findViewById<TextView>(R.id.tvSummary)
        val btnProceedPayment = findViewById<Button>(R.id.btnProceedPayment)

        val name = intent.getStringExtra("customerName")
        val address = intent.getStringExtra("customerAddress")
        val contact = intent.getStringExtra("customerContact")

        tvSummary.text = """
            Customer: $name
            Address: $address
            Contact: $contact
            
            Order: 1x Food Item
            Total: R120.00
        """.trimIndent()

        btnProceedPayment.setOnClickListener {
            val intent = Intent(this, Activity_payment::class.java)
            startActivity(intent)
        }
    }
}

package com.example.foodgenie

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Activity_delivery_address : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_delivery_address)

        val etName = findViewById<EditText>(R.id.etName)
        val etAddress = findViewById<EditText>(R.id.etAddress)
        val etCity = findViewById<EditText>(R.id.etCity)
        val etContact = findViewById<EditText>(R.id.etContact)
        val btnProceed = findViewById<Button>(R.id.btnProceedPayment)

        btnProceed.setOnClickListener {
            val name = etName.text.toString().trim()
            val address = etAddress.text.toString().trim()
            val city = etCity.text.toString().trim()
            val contact = etContact.text.toString().trim()

            if (name.isEmpty() || address.isEmpty() || city.isEmpty() || contact.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, Activity_order_summary::class.java)
                intent.putExtra("customerName", name)
                intent.putExtra("customerAddress", "$address, $city")
                intent.putExtra("customerContact", contact)
                startActivity(intent)
            }
        }
    }
}

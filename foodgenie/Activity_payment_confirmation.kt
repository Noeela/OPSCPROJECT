package com.example.foodgenie

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Activity_payment_confirmation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment_confirmation)

        val btnTrackOrder = findViewById<Button>(R.id.btnTrackorder)
        btnTrackOrder.setOnClickListener {
            startActivity(Intent(this, Activity_order_tracking::class.java))
            finish()
        }
    }
}

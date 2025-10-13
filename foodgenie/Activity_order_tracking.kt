package com.example.foodgenie

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import  android.widget.Button
import android.widget.ImageView
import android.content.Intent
import android.net.Uri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity_order_tracking : AppCompatActivity() {
    private lateinit var tvEstimatedTime: TextView
    private lateinit var tvOrderId: TextView
    private lateinit var btnTrackMap: Button
    private lateinit var btnBack: ImageView
    private lateinit var tvRiderName: TextView
    private lateinit var tvRiderPhone: TextView

    // Fake progress simulation
    private var currentStep = 1
    private val steps = listOf("Order Received", "Preparing Food", "Out for Delivery", "Delivered")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order_tracking)

        // Initialize views
        tvOrderId = findViewById(R.id.tvOrderId)
        tvEstimatedTime = findViewById(R.id.tvEstimatedTime)
        btnTrackMap = findViewById(R.id.btnTrackMap)
        btnBack = findViewById(R.id.btnBack)
        tvRiderName = findViewById(R.id.tvRiderName)
        tvRiderPhone = findViewById(R.id.tvRiderPhone)

        // Sample static data (you can replace with real order data later)
        tvOrderId.text = "Order ID: #FG2345"
        tvEstimatedTime.text = "Estimated delivery: 25 mins"
        tvRiderName.text = "Alex - FoodGenie Rider"
        tvRiderPhone.text = "+27 65 789 1234"

        // Back button action
        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Open Google Maps when clicking “Track on Map”
        btnTrackMap.setOnClickListener {
            // Example location (Pretoria)
            val gmmIntentUri = Uri.parse("geo:-25.7479,28.2293?q=Delivery+Location")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        // Optional: simulate progress (auto advance through steps)
        simulateOrderProgress()
    }

    // Simulate live order tracking (auto step updates)
    private fun simulateOrderProgress() {
        val handler = android.os.Handler(mainLooper)
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (currentStep < steps.size) {
                    tvEstimatedTime.text = "Status: ${steps[currentStep]}"
                    currentStep++
                    handler.postDelayed(this, 6000) // update every 6 seconds
                } else {
                    tvEstimatedTime.text = "Status: Delivered ✅"
                }
            }
        }, 4000)
    }
}
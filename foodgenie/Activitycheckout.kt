package com.example.foodgenie

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodgenie.databinding.ActivityCheckoutBinding

class Activity_checkout : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemName = intent.getStringExtra("itemName")
        val itemPrice = intent.getDoubleExtra("itemPrice", 0.0)
        val itemImage = intent.getIntExtra("itemImage", R.drawable.burger_chips)

        binding.tvOrderSummary.text = "Order: $itemName\nTotal: R$itemPrice"
        binding.imgFoodPreview.setImageResource(itemImage)

        binding.btnPayNow.setOnClickListener {
            val address = binding.etAddress.text.toString().trim()

            if (address.isEmpty()) {
                Toast.makeText(this, "Please enter your delivery address", Toast.LENGTH_SHORT).show()
            } else {
                // Go to payment screen
                val intent = Intent(this, Activity_payment::class.java)
                intent.putExtra("itemName", itemName)
                intent.putExtra("itemPrice", itemPrice)
                startActivity(intent)
            }
        }
    }
}

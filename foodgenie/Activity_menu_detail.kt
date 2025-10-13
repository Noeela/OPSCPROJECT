package com.example.foodgenie

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.foodgenie.databinding.ActivityMenuDetailBinding

class Activity_menu_detail : AppCompatActivity() {

    private lateinit var binding: ActivityMenuDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("itemName")
        val price = intent.getDoubleExtra("itemPrice", 0.0)
        val image = intent.getIntExtra("itemImage", R.drawable.burger_chips)

        binding.tvFoodName.text = name
        binding.tvPrice.text = "R$price"
        binding.imgFood.setImageResource(image)

        binding.btnAddToCart.setOnClickListener {
            // Navigate to checkout
            val intent = Intent(this, Activity_checkout::class.java)
            intent.putExtra("itemName", name)
            intent.putExtra("itemPrice", price)
            intent.putExtra("itemImage", image)
            startActivity(intent)
        }
    }
}



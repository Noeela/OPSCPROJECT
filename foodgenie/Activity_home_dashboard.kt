package com.example.foodgenie

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Activity_home_dashboard : AppCompatActivity() {

    private lateinit var btnMeals: Button
    private lateinit var btnSides: Button
    private lateinit var btnDrinks: Button
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_dashboard)

        // Retrieve username from intent
        val userName = intent.getStringExtra("USER_NAME") ?: "User"

        // Greeting
        val tvHello = findViewById<TextView>(R.id.tvHello)
        tvHello.text = "Hello $userName."

        // BottomNavigationView
        bottomNav = findViewById(R.id.bottomNav)

        // Category buttons
        btnMeals = findViewById(R.id.btnMeals)
        btnSides = findViewById(R.id.btnSides)
        btnDrinks = findViewById(R.id.btnDrinks)

        // Load default fragment (Meals)
        selectCategory(btnMeals, MealFragment())

        // Bottom navigation clicks
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_meals -> selectCategory(btnMeals, MealFragment())
                R.id.nav_sides -> selectCategory(btnSides, SideFragment())
                R.id.nav_drinks -> selectCategory(btnDrinks, DrinkFragment())
            }
            true
        }

        // Category button clicks
        btnMeals.setOnClickListener { selectCategory(btnMeals, MealFragment()) }
        btnSides.setOnClickListener { selectCategory(btnSides, SideFragment()) }
        btnDrinks.setOnClickListener { selectCategory(btnDrinks, DrinkFragment()) }
    }


    // Replace fragment and highlight active button
    private fun selectCategory(activeButton: Button, fragment: Fragment) {
        // Load fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

        // Reset all buttons to default
        val buttons = listOf(btnMeals, btnSides, btnDrinks)
        buttons.forEach {
            it.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white))
            it.setTextColor(ContextCompat.getColor(this, android.R.color.black))
        }

        // Highlight active button
        activeButton.setBackgroundColor(ContextCompat.getColor(this, R.color.pink)) // Replace with your theme color
        activeButton.setTextColor(ContextCompat.getColor(this, android.R.color.white))
    }
}

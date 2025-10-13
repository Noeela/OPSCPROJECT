package com.example.foodgenie

import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Activity_home : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        // Load default fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .commit()

        bottomNav.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_menu -> MenuFragment()
                R.id.nav_favorites -> FavouritesFragment()
                R.id.nav_profile -> ProfileFragment()
                else -> null
            }
            bottomNav.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_home -> {
                        // Already on Home — stay here
                        true
                    }

                    R.id.nav_menu -> {

                        startActivity(Intent(this, Menu::class.java))
                        startActivity(Intent(this, Activity_rewards::class.java))
                        startActivity(Intent(this, Activity_menu_detail::class.java))
                        startActivity(Intent(this, Activity_order_summary::class.java))
                        startActivity(Intent(this, Activity_order_tracking::class.java))
                        startActivity(Intent(this, Activity_cart::class.java))
                        startActivity(Intent(this, Activity_checkout::class.java))
                        startActivity(Intent(this, Activity_delivery_address::class.java))
                        true
                    }
                 
                    R.id.nav_favorites -> {
                        startActivity(Intent(this, Activity_rewards::class.java))
                        true
                    }


                    R.id.nav_profile -> {
                        startActivity(Intent(this, Activity_SignUp::class.java))
                        startActivity(Intent(this, Activity_login::class.java))
                        startActivity(Intent(this, Activity_home_dashboard::class.java))
                        true
                    }

                    else -> false
                }
            }
            fragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, it)
                    .commit()
                true
            } ?: false
        }
    }
}




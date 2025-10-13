package com.example.foodgenie

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Activity_login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize UI elements
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        val tvSignUp = findViewById<TextView>(R.id.tvAlreadyHaveAccount)
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)

        // Handle login button click
        btnSignIn.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // TODO: Replace with real authentication (Firebase, Room, etc.)
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()

                // Example: Get username from email or input field
                val userName = if (email.contains("@")) email.substringBefore("@") else "User"

                // Pass username to dashboard
                val intent = Intent(this, Activity_home_dashboard::class.java)
                intent.putExtra("USER_NAME", userName)
                startActivity(intent)
                finish()
            }
        }

        // Handle Sign Up text click
        tvSignUp.setOnClickListener {
            val intent = Intent(this, Activity_SignUp::class.java)
            startActivity(intent)
        }

        // Handle Forgot Password click
        tvForgotPassword.setOnClickListener {
            Toast.makeText(this, "Forgot Password feature coming soon!", Toast.LENGTH_SHORT).show()
        }
    }
}

package com.example.foodgenie

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Activity_SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val fullName = findViewById<EditText>(R.id.etFullName)
        val email = findViewById<EditText>(R.id.etEmail)
        val password = findViewById<EditText>(R.id.etPassword)
        val confirmPassword = findViewById<EditText>(R.id.etConfirmPassword)

        val btnCreateAccount = findViewById<Button>(R.id.btnCreateAccount)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvSignIn = findViewById<TextView>(R.id.tvSignIn)
        val btnGoogle = findViewById<ImageView>(R.id.btnGoogle)
        val btnApple = findViewById<ImageView>(R.id.btnApple)

        btnCreateAccount.setOnClickListener {
            val name = fullName.text.toString().trim()
            val emailText = email.text.toString().trim()
            val pass = password.text.toString().trim()
            val confirmPass = confirmPassword.text.toString().trim()

            when {
                name.isEmpty() || emailText.isEmpty() || pass.isEmpty() || confirmPass.isEmpty() -> {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
                pass != confirmPass -> {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Activity_login::class.java)
                    intent.putExtra("USER_NAME", name)
                    startActivity(intent)
                    finish()
                }
            }
        }

        btnLogin.setOnClickListener {
            startActivity(Intent(this, Activity_login::class.java))
            finish()
        }

        tvSignIn.setOnClickListener {
            startActivity(Intent(this, Activity_login::class.java))
            finish()
        }

        btnGoogle.setOnClickListener {
            Toast.makeText(this, "Google Sign-Up coming soon", Toast.LENGTH_SHORT).show()
        }

        btnApple.setOnClickListener {
            Toast.makeText(this, "Apple Sign-Up coming soon", Toast.LENGTH_SHORT).show()
        }
    }
}

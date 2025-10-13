package com.example.foodgenie

import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.content.Intent
import com.example.foodgenie.R.id.btnContinue

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ApiClient.instance.getFoods().enqueue(object : retrofit2.Callback<List<FoodItem>> {
            override fun onResponse(
                call: retrofit2.Call<List<FoodItem>>,
                response: retrofit2.Response<List<FoodItem>>
            ) {
                if (response.isSuccessful) {
                    val foods = response.body()
                    // TODO: display foods in RecyclerView
                    println("Foods: $foods")
                }
            }

            override fun onFailure(call: retrofit2.Call<List<FoodItem>>, t: Throwable) {
                t.printStackTrace()
            }
        })

        val btn = findViewById<Button>(btnContinue)
        btn.setOnClickListener {
            startActivity(Intent(this, Activity_SignUp::class.java))
            finish()
        }
    }
}
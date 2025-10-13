package com.example.foodgenie

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodgenie.databinding.ActivityMenuBinding
import com.google.android.material.tabs.TabLayout

class Menu : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var menuAdapter: MenuAdapter
    private val mealList = mutableListOf<MenuItem>()
    private val sideList = mutableListOf<MenuItem>()
    private val drinkList = mutableListOf<MenuItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDummyData()

        menuAdapter = MenuAdapter(mealList) { item ->
            val intent = Intent(this, Activity_menu_detail::class.java)
            intent.putExtra("itemName", item.name)
            intent.putExtra("itemPrice", item.price)
            intent.putExtra("itemImage", item.imageRes)
            intent.putExtra("itemDescription", item.description)
            startActivity(intent)
        }

        binding.rvMenuItems.layoutManager = LinearLayoutManager(this)
        binding.rvMenuItems.adapter = menuAdapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> menuAdapter.updateList(mealList)
                    1 -> menuAdapter.updateList(sideList)
                    2 -> menuAdapter.updateList(drinkList)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun setupDummyData() {
        mealList.add(MenuItem("Burger & Chips", 50.0, R.drawable.burger_chips, "A juicy burger served with crispy fries"))
        mealList.add(MenuItem("Pizza", 80.0, R.drawable.pizza, "Cheesy pizza with your favorite toppings"))

        sideList.add(MenuItem("Fries", 40.0, R.drawable.chips, "Golden and crispy French fries"))
        sideList.add(MenuItem("Salad", 30.0, R.drawable.gucamole, "Fresh and healthy mixed salad"))

        drinkList.add(MenuItem("Cola", 15.0, R.drawable.milkshake, "Chilled fizzy cola"))
        drinkList.add(MenuItem("Juice", 25.0, R.drawable.mocktail1_small, "Fresh fruit juice"))
    }
}



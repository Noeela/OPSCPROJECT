package com.example.foodgenie

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import android.view.View
import android.content.Intent

class Activity_payment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)

        val rbCard = findViewById<RadioButton>(R.id.rbCard)
        val rbPalPay = findViewById<RadioButton>(R.id.rbPalPay)
        val rbCash = findViewById<RadioButton>(R.id.rbCash)
        val layoutCard = findViewById<LinearLayout>(R.id.layoutCardDetails)
        val layoutPalPay = findViewById<LinearLayout>(R.id.layoutPalPay)
        val radioGroup = findViewById<RadioGroup>(R.id.paymentOptions)

        val etCardNumber = findViewById<EditText>(R.id.etCardNumber)
        val etCardName = findViewById<EditText>(R.id.etCardName)
        val etExpiry = findViewById<EditText>(R.id.etExpiry)
        val etCVV = findViewById<EditText>(R.id.etCVV)
        val etPalPayEmail = findViewById<EditText>(R.id.etPalPayEmail)
        val chkDeliveryPayment = findViewById<CheckBox>(R.id.chkDeliveryPayment)
        val btnPayNow = findViewById<Button>(R.id.btnPayNow)

        // Toggle visibility based on selected payment option
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbCard -> {
                    layoutCard.visibility = View.VISIBLE
                    layoutPalPay.visibility = View.GONE
                }
                R.id.rbPalPay -> {
                    layoutCard.visibility = View.GONE
                    layoutPalPay.visibility = View.VISIBLE
                }
                R.id.rbCash -> {
                    layoutCard.visibility = View.GONE
                    layoutPalPay.visibility = View.GONE
                }
            }
        }

        btnPayNow.setOnClickListener {
            // Check if a payment method is selected
            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId == -1 && !chkDeliveryPayment.isChecked) {
                Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Card validation
            if (selectedId == R.id.rbCard) {
                if (etCardNumber.text.isEmpty() ||
                    etCardName.text.isEmpty() ||
                    etExpiry.text.isEmpty() ||
                    etCVV.text.isEmpty()
                ) {
                    Toast.makeText(this, "Please fill in all card details", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            // PalPay validation
            if (selectedId == R.id.rbPalPay) {
                if (etPalPayEmail.text.isEmpty()) {
                    Toast.makeText(this, "Please enter your PalPay email", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            // Navigate to Payment Confirmation screen
            // Navigate to payment confirmation, then tracking
            val intent = Intent(this, Activity_payment_confirmation::class.java)
            intent.putExtra("itemName", intent.getStringExtra("itemName"))
            startActivity(intent)
            finish()


        }
    }
}

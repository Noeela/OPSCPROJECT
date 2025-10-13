package com.example.foodgenie

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import kotlin.random.Random

class Activity_rewards : AppCompatActivity() {

    private lateinit var imgWheel: ImageView
    private lateinit var btnSpin: Button
    private lateinit var tvPoints: TextView
    private lateinit var tvLastReward: TextView
    private lateinit var recommendedContainer: LinearLayout
    private var currentPoints = 120
    private var spinning = false

    private val rewards = listOf(
        Reward("10 Points", 10, R.drawable.ic_points10),
        Reward("20 Points", 20, R.drawable.ic_points20),
        Reward("50 Points", 50, R.drawable.ic_points50),
        Reward("Spin again", 0, R.drawable.ic_spinagain),
        Reward("Coffee Voucher", 30, R.drawable.ic_coffee),
        Reward("Discount Coupon", 40, R.drawable.ic_discount),
        Reward("Free Snack", 50, R.drawable.ic_snack)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rewards)

        imgWheel = findViewById(R.id.imgWheel)
        btnSpin = findViewById(R.id.btnSpin)
        tvPoints = findViewById(R.id.tvPoints)
        tvLastReward = findViewById(R.id.tvLastReward)
        recommendedContainer = findViewById(R.id.recommendedContainer)

        tvPoints.text = "Your Points: $currentPoints"

        btnSpin.setOnClickListener {
            if (!spinning) spinWheel()
            else Toast.makeText(this, "Wheel is spinning!", Toast.LENGTH_SHORT).show()
        }

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener { finish() }

        showRecommendedRewards()
    }

    private fun spinWheel() {
        if (spinning) return
        spinning = true
        btnSpin.isEnabled = false

        val rewardIndex = Random.nextInt(rewards.size)
        val segmentAngle = 360f / rewards.size
        val targetAngle = 360f * 5 + rewardIndex * segmentAngle + segmentAngle / 2

        val animator = ObjectAnimator.ofFloat(imgWheel, "rotation", imgWheel.rotation, targetAngle)
        animator.duration = 3000
        animator.interpolator = DecelerateInterpolator()

        animator.addListener(object : android.animation.AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: android.animation.Animator) {
                super.onAnimationEnd(animation)
                spinning = false
                btnSpin.isEnabled = true

                val reward = rewards[rewardIndex]
                currentPoints += reward.points
                tvPoints.text = "Your Points: $currentPoints"
                tvLastReward.text = "You won: ${reward.name}"
                Toast.makeText(this@Activity_rewards, "Congrats! ${reward.name}", Toast.LENGTH_SHORT).show()
            }
        })

        animator.start()
    }


    private fun showRecommendedRewards() {
        rewards.forEach { reward ->
            val card = CardView(this).apply {
                layoutParams = LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                    setMargins(16, 0, 16, 0)
                }
                radius = 16f
                setCardBackgroundColor(android.graphics.Color.WHITE)
                elevation = 8f

                val layout = LinearLayout(this@Activity_rewards).apply {
                    orientation = LinearLayout.VERTICAL
                    gravity = android.view.Gravity.CENTER
                    setPadding(16, 16, 16, 16)
                }

                val img = ImageView(this@Activity_rewards).apply {
                    setImageResource(reward.iconRes)
                    layoutParams = LinearLayout.LayoutParams(100, 100)
                }

                val txt = TextView(this@Activity_rewards).apply {
                    text = "${reward.name} (+${reward.points} pts)"
                    textSize = 14f
                    setTextColor(android.graphics.Color.BLACK)
                    gravity = android.view.Gravity.CENTER
                }

                layout.addView(img)
                layout.addView(txt)
                addView(layout)
            }
            recommendedContainer.addView(card)
        }
    }

    data class Reward(val name: String, val points: Int, val iconRes: Int)
}

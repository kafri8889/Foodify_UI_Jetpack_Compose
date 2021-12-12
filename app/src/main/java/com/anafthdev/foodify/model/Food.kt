package com.anafthdev.foodify.model

import androidx.annotation.DrawableRes
import com.anafthdev.foodify.R
import java.util.concurrent.TimeUnit
import kotlin.random.Random

data class Food(
	val name: String,
	val content: String,
	val contentFull: String,
	val description: String,
	@DrawableRes val icon: Int,
	val rating: Float,  // 0.0..5.0
	val calories: Int,
	val cookTimeFrom: Long,
	val cookTimeUntil: Long,
	var isFavorite: Boolean = false
) {
	companion object {
		
		val sample = Food(
			name = "Big boys Cheese burger",
			content = "Our simple, classic cheeseburger begins with a 100% pure beef burger seasoned with just a pinch of salt and pepper. The McDonald’s Cheeseburger is topped",
			contentFull = "No 10 opp lekki phase 1 bridge in sangotedo estate. Our simple, classic cheeseburger begins with a 100% pure beef burger seasoned with just a pinch of salt and pepper. The McDonald’s Cheeseburger is topped",
			description = "No 10 opp lekki phase 1 bridge in sangotedo estate",
			icon = R.drawable.burger,
			rating = 4.5f,
			calories = 300,
			cookTimeFrom = TimeUnit.MINUTES.toMillis(5),
			cookTimeUntil = TimeUnit.MINUTES.toMillis(10),
		)
	}
}

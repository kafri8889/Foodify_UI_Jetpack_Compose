package com.anafthdev.foodify.model

import androidx.annotation.DrawableRes
import com.anafthdev.foodify.R
import com.anafthdev.foodify.data.FoodType
import java.util.concurrent.TimeUnit

data class Food(
	val name: String,
	val content: String,
	val contentFull: String,
	val description: String,
	@DrawableRes val image: Int,
	var price: Double,
	val type: String,
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
			image = R.drawable.burger,
			price = 23.99,
			type = FoodType.Burger.Classic_Cheeseburger,
			rating = 4.5f,
			calories = 300,
			cookTimeFrom = TimeUnit.MINUTES.toMillis(5),
			cookTimeUntil = TimeUnit.MINUTES.toMillis(10),
		)
	}
}

package com.anafthdev.foodify.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.anafthdev.foodify.R
import kotlin.random.Random

data class FoodCategory(
	val name: String,
	@DrawableRes val icon: Int,
	val color: Color,
	val id: Int = Random.nextInt()
) {
	companion object {
		
		val sample_1 = FoodCategory(
			name = "Pizza",
			icon = R.drawable.pizza_icon,
			color = Color.Magenta
		)
		
		val sample_2 = FoodCategory(
			name = "Burger",
			icon = R.drawable.burger_icon,
			color = Color.Green
		)
		
		val sample_3 = FoodCategory(
			name = "Sausage",
			icon = R.drawable.sausage_icon,
			color = Color.Blue
		)
		
		val sample_4 = FoodCategory(
			name = "Samosa",
			icon = R.drawable.samosa_icon,
			color = Color.Red
		)
		
	}
}

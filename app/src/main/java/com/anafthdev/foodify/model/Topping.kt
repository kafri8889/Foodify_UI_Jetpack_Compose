package com.anafthdev.foodify.model

import androidx.annotation.DrawableRes
import com.anafthdev.foodify.R

data class Topping(
	val name: String,
	@DrawableRes val image: Int
) {
	companion object {
		val values = listOf(
			Topping(
				name = "Regular Bun",
				image = R.drawable.regular_bun
			),
			Topping(
				name = "Cheese",
				image = R.drawable.cheese
			),Topping(
				name = "Meat Sau",
				image = R.drawable.meat_sau
			),
		)
	}
}

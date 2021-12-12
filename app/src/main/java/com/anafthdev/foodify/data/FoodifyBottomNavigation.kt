package com.anafthdev.foodify.data

import androidx.annotation.DrawableRes
import com.anafthdev.foodify.R

sealed class FoodifyBottomNavigation(
	val name: String,
	@DrawableRes val icon: Int,
	@DrawableRes val iconSelected: Int,
) {
	companion object {
		
		object Home: FoodifyBottomNavigation("Home", R.drawable.ic_home, R.drawable.ic_home_selected)
		
		object Favorite: FoodifyBottomNavigation("Favorite", R.drawable.ic_favorite, R.drawable.ic_favorite_selected)
		
		object Notification: FoodifyBottomNavigation("Notification", R.drawable.ic_notification, R.drawable.ic_notification_selected)
		
		object ShoppingCart: FoodifyBottomNavigation("Shopping Cart", R.drawable.ic_shopping_cart, R.drawable.ic_shopping_cart_selected)
		
		val values = listOf(
			Home,
			Favorite,
			Notification,
			ShoppingCart
		)
	}
}

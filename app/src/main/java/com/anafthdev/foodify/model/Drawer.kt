package com.anafthdev.foodify.model

import androidx.annotation.DrawableRes
import com.anafthdev.foodify.R

data class Drawer(
	val name: String,
	@DrawableRes val resId: Int
) {
	companion object {
		val values = listOf(
			Drawer(
				name = "My profile",
				resId = R.drawable.ic_profile
			),
			Drawer(
				name = "Payment method",
				resId = R.drawable.ic_work
			),
			Drawer(
				name = "Settings",
				resId = R.drawable.ic_settings
			),
			Drawer(
				name = "Help",
				resId = R.drawable.ic_chat
			),
			Drawer(
				name = "Privacy policy",
				resId = R.drawable.ic_paper
			),
		)
	}
}

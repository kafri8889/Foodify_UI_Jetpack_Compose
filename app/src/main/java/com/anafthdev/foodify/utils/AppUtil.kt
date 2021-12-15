package com.anafthdev.foodify.utils

import android.content.Context
import android.widget.Toast

object AppUtil {
	
	fun Any.toast(context: Context, length: Int = Toast.LENGTH_SHORT) =
		Toast.makeText(context, this.toString(), Toast.LENGTH_SHORT).show()
}
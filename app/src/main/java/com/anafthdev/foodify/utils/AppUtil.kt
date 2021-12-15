package com.anafthdev.foodify.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

object AppUtil {
	
	fun Any.toast(context: Context, length: Int = Toast.LENGTH_SHORT) =
		Toast.makeText(context, this.toString(), Toast.LENGTH_SHORT).show()
}
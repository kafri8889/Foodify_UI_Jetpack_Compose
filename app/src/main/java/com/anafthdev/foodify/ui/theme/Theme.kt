package com.anafthdev.foodify.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
	primary = Purple500,
	primaryVariant = Purple700,
	secondary = Teal200,
	
	background = background,
	/* Other default colors to override
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun FoodifyTheme(content: @Composable() () -> Unit) {
	val colors = LightColorPalette
	
	MaterialTheme(
		colors = colors,
		typography = Typography_DM_Sans,
		shapes = Shapes,
		content = content
	)
}
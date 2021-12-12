package com.anafthdev.foodify.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.anafthdev.foodify.R

@OptIn(ExperimentalTextApi::class)
@Composable
fun dMSansFontFamily() = FontFamily(
	Font(LocalContext.current.assets, "dm_sans_regular.ttf")
)

@OptIn(ExperimentalTextApi::class)
@Composable
fun sKModernistFontFamily() = FontFamily(
	Font(LocalContext.current.assets, "sk_modernist_regular.otf")
)

@Composable
fun typographyDmSans() = Typography(
	body1 = TextStyle(
		fontFamily = dMSansFontFamily(),
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp
	)
)

@Composable
fun typographySkModernist() = Typography(
	body1 = TextStyle(
		fontFamily = sKModernistFontFamily(),
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp
	)
)

val Typography_DM_Sans = Typography(
	defaultFontFamily = FontFamily(Font(R.font.dm_sans_regular)),
	body1 = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp
	)
)

val Typography_Sk_Modernist = Typography(
	defaultFontFamily = FontFamily(Font(R.font.sk_modernist_regular)),
	body1 = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp
	)
)
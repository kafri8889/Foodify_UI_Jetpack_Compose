package com.anafthdev.foodify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anafthdev.foodify.data.FoodifyDestination
import com.anafthdev.foodify.ui.HomeScreen
import com.anafthdev.foodify.ui.PaymentScreen
import com.anafthdev.foodify.ui.SplashScreen
import com.anafthdev.foodify.ui.WelcomeScreen
import com.anafthdev.foodify.ui.theme.FoodifyTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			FoodifyTheme {
				Surface(color = MaterialTheme.colors.background) {
					Screen()
				}
			}
		}
	}
	
	@Composable
	fun Screen() {
		
		val navigationController = rememberNavController()
		
		NavHost(
			navController = navigationController,
			startDestination = FoodifyDestination.SPLASH_SCREEN
		) {
			composable(FoodifyDestination.SPLASH_SCREEN) {
				SplashScreen(
					navController = navigationController
				)
			}
			
			composable(FoodifyDestination.WELCOME_SCREEN) {
				WelcomeScreen(
					navController = navigationController
				)
			}
			
			composable(FoodifyDestination.PAYMENT_SCREEN) {
				PaymentScreen(
					navController = navigationController
				)
			}
			
			composable(FoodifyDestination.HOME_SCREEN) {
				HomeScreen(
					navController = navigationController
				)
			}
		}
	}
}
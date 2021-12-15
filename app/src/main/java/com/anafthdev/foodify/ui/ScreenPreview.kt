package com.anafthdev.foodify.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.anafthdev.foodify.R
import com.anafthdev.foodify.data.FoodifyBottomNavigation
import com.anafthdev.foodify.model.Drawer
import com.anafthdev.foodify.model.Food
import com.anafthdev.foodify.model.FoodCategory
import com.anafthdev.foodify.model.Topping
import com.anafthdev.foodify.ui.components.*
import com.anafthdev.foodify.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalUnitApi::class)
@Preview(showSystemUi = true)
@Composable
fun HomeDrawerPreview() {
	
	Box(
		modifier = Modifier
			.fillMaxSize()
	) {
		Column {
			
			Image(
				painter = painterResource(id = R.drawable.image_profile),
				contentDescription = null,
				modifier = Modifier
					.size(142.dp)
					.padding(top = 32.dp, start = 32.dp, end = 32.dp, bottom = 24.dp)
			)
			
			Text(
				text = "Marvis Ighedosa",
				style = typographyDmSans().body1.copy(
					color = black.copy(alpha = 0.8f),
					fontWeight = FontWeight.Bold,
					fontSize = TextUnit(16f, TextUnitType.Sp)
				),
				modifier = Modifier
					.padding(start = 24.dp)
			)
			
			Text(
				text = "dosamarvis@gmail.com",
				style = typographySkModernist().body1.copy(
					color = black.copy(alpha = 0.8f),
					fontWeight = FontWeight.Normal,
					fontSize = TextUnit(14f, TextUnitType.Sp)
				),
				modifier = Modifier
					.padding(start = 24.dp, top = 8.dp)
			)
			
			Column(
				modifier = Modifier
					.padding(start = 24.dp, top = 32.dp)
			) {
				Drawer.values.forEach { drawer ->
					DrawerItem(
						drawer = drawer,
						onClick = {}
					)
				}
			}
		}
		
		GradientButton(
			shape = RoundedCornerShape(18.dp),
			contentPadding = PaddingValues(),
			brush = Brush.horizontalGradient(
				colors = listOf(
					Color(0xFFF9881F),
					Color(0xFFFF774C),
				)
			),
			onClick = {},
			modifier = Modifier
				.width(128.dp)
				.padding(bottom = 32.dp, start = 24.dp)
				.height(48.dp)
				.align(Alignment.BottomStart)
		) {
			Text(
				text = "Log out",
				style = typographySkModernist().body1.copy(
					color = white,
					fontSize = TextUnit(14f, TextUnitType.Sp),
					fontWeight = FontWeight.Bold
				)
			)
		}
	}
}

@OptIn(
	ExperimentalUnitApi::class,
	ExperimentalPagerApi::class
)
//@Preview(showSystemUi = true)
@Composable
fun MainWelcomeScreenPreview() {
	
	val pagerState = rememberPagerState()
	
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		
		Text(
			text = "Choose from a wide range of  delicious meals",
			fontSize = TextUnit(24f, TextUnitType.Sp),
			fontWeight = FontWeight.Bold,
			textAlign = TextAlign.Center,
			modifier = Modifier
				.padding(top = 16.dp)
		)
		
		HorizontalPager(
			state = pagerState,
			count = 3,
			modifier = Modifier
				.fillMaxWidth()
				.height(380.dp)
		) { page ->
			Image(
				painter = when (page) {
					0 -> painterResource(id = R.drawable.welcome_slider_1)				  
					1 -> painterResource(id = R.drawable.welcome_slider_2)				  
					2 -> painterResource(id = R.drawable.welcome_slider_3)
					else -> painterResource(id = R.drawable.welcome_slider_1)
				},
				contentScale = ContentScale.FillWidth,
				contentDescription = null,
				modifier = Modifier
					.fillMaxSize()
			)
		}
		
		HorizontalPagerIndicator(
			pagerState = pagerState,
			modifier = Modifier
				.padding(top = 16.dp)
		)
	}
}

@OptIn(ExperimentalUnitApi::class)
//@Preview(showSystemUi = true)
@Composable
fun SignUpScreenPreview() {
	val passwordVisibility = false
	
	Column(
		modifier = Modifier
			.padding(16.dp)
	) {
		Text(
			text = "Create an account",
			style = typographyDmSans().body1.copy(
				fontWeight = FontWeight.Bold,
				fontSize = TextUnit(24f, TextUnitType.Sp)
			),
			modifier = Modifier
				.padding(start = 8.dp, end = 8.dp)
		)
		
		Text(
			text = "Welcome friend, enter your details so lets get started in ordering food.",
			style = typographySkModernist().body1.copy(
				fontSize = TextUnit(14f, TextUnitType.Sp)
			),
			modifier = Modifier
				.padding(top = 16.dp, start = 8.dp)
		)
		
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			
			// Email text field
			OutlinedTextField(
				value = "",
				shape = RoundedCornerShape(16.dp),
				onValueChange = {},
				label = {
					Text("Email")
				},
				placeholder = {
					Text("example@foodify.com")
				},
				leadingIcon = {
					Icon(
						imageVector = Icons.Default.Email,
						contentDescription = null
					)
				},
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 48.dp)
			)
			
			
			
			// Password text field
			OutlinedTextField(
				value = "",
				shape = RoundedCornerShape(16.dp),
				visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
				onValueChange = {},
				label = {
					Text("Password")
				},
				leadingIcon = {
					Icon(
						imageVector = Icons.Default.Lock,
						contentDescription = null
					)
				},
				trailingIcon = {
					Icon(
						painter = run {
							if (passwordVisibility) painterResource(id = R.drawable.ic_visibility_off)
							else painterResource(id = R.drawable.ic_visibility)
						},
						contentDescription = null
					)
				},
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 16.dp)
			)
			
			
			
			// Confirm password text field
			OutlinedTextField(
				value = "",
				shape = RoundedCornerShape(16.dp),
				visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
				onValueChange = {},
				label = {
					Text("Confirm Password")
				},
				leadingIcon = {
					Icon(
						imageVector = Icons.Default.Lock,
						contentDescription = null
					)
				},
				trailingIcon = {
					Icon(
						painter = run {
							if (passwordVisibility) painterResource(id = R.drawable.ic_visibility_off)
							else painterResource(id = R.drawable.ic_visibility)
						},
						contentDescription = null
					)
				},
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 16.dp)
			)
			
			
			
			Spacer(modifier = Modifier.weight(1f))
			
			
			
			Button(
				shape = RoundedCornerShape(24.dp),
				elevation = ButtonDefaults.elevation(
					defaultElevation = 2.dp
				),
				colors = ButtonDefaults.buttonColors(
					backgroundColor = white
				),
				onClick = {
				
				},
				modifier = Modifier
					.padding(bottom = 16.dp)
			) {
				Row(
					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.Center,
					modifier = Modifier
						.padding(6.dp),
				) {
					Image(
						painter = painterResource(id = R.drawable.ic_google),
						contentDescription = null,
						modifier = Modifier
							.padding(end = 8.dp),
					)
					
					Text("Sign-in with Google")
				}
			}
			
			
			
			// Create an account button
			GradientButton(
				shape = RoundedCornerShape(18.dp),
				contentPadding = PaddingValues(),
				brush = Brush.horizontalGradient(
					colors = listOf(
						Color(0xFFF9881F),
						Color(0xFFFF774C),
					)
				),
				onClick = {
				
				},
				modifier = Modifier
					.fillMaxWidth()
					.height(56.dp)
					.padding(start = 24.dp, end = 24.dp, top = 8.dp)
			) {
				Text(
					text = "Create an account",
					fontSize = TextUnit(14f, TextUnitType.Sp),
					color = white,
					style = typographyDmSans().body1.copy(
						fontWeight = FontWeight.Bold
					)
				)
			}
			
			
			
			// Login to my account button
			TransparentButton(
				onClick = {
				
				},
				modifier = Modifier
					.padding(bottom = 16.dp)
			) {
				Text(
					text = "Login to my account",
					fontSize = TextUnit(14f, TextUnitType.Sp),
					color = orange,
					style = typographyDmSans().body1.copy(
						fontWeight = FontWeight.Bold
					)
				)
			}
		}
		
	}
}





@OptIn(ExperimentalUnitApi::class)
//@Preview(showSystemUi = true)
@Composable
fun SignInScreenPreview() {
	val passwordVisibility = false
	
	Column(
		modifier = Modifier
			.padding(16.dp)
	) {
		Text(
			text = "Login to your account",
			style = typographyDmSans().body1.copy(
				fontWeight = FontWeight.Bold,
				fontSize = TextUnit(24f, TextUnitType.Sp)
			),
			modifier = Modifier
				.padding(start = 8.dp, end = 8.dp)
		)
		
		Text(
			text = "Good to see you again, enter your details below to continue ordering.",
			style = typographySkModernist().body1.copy(
				fontSize = TextUnit(14f, TextUnitType.Sp)
			),
			modifier = Modifier
				.padding(top = 16.dp, start = 8.dp)
		)
		
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			
			// Email text field
			OutlinedTextField(
				value = "",
				shape = RoundedCornerShape(16.dp),
				onValueChange = {},
				label = {
					Text("Email")
				},
				placeholder = {
					Text("example@foodify.com")
				},
				leadingIcon = {
					Icon(
						imageVector = Icons.Default.Email,
						contentDescription = null
					)
				},
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 48.dp)
			)
			
			
			
			// Password text field
			OutlinedTextField(
				value = "",
				shape = RoundedCornerShape(16.dp),
				visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
				onValueChange = {},
				label = {
					Text("Password")
				},
				leadingIcon = {
					Icon(
						imageVector = Icons.Default.Lock,
						contentDescription = null
					)
				},
				trailingIcon = {
					Icon(
						painter = run {
							if (passwordVisibility) painterResource(id = R.drawable.ic_visibility_off)
							else painterResource(id = R.drawable.ic_visibility)
						},
						contentDescription = null
					)
				},
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 16.dp)
			)
			
			
			
			TransparentButton(
				onClick = {},
				modifier = Modifier
					.align(Alignment.Start)
					.padding(start = 8.dp, top = 8.dp)
			) {
				Text(
					text = "Forgot password",
					fontSize = TextUnit(14f, TextUnitType.Sp),
					color = orange,
					style = typographyDmSans().body1.copy(
						fontWeight = FontWeight.Bold
					)
				)
			}
			
			
			
			Spacer(modifier = Modifier.weight(1f))
			
			
			
			Button(
				shape = RoundedCornerShape(24.dp),
				elevation = ButtonDefaults.elevation(
					defaultElevation = 2.dp
				),
				colors = ButtonDefaults.buttonColors(
					backgroundColor = white
				),
				onClick = {
				
				},
				modifier = Modifier
					.padding(bottom = 16.dp)
			) {
				Row(
					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.Center,
					modifier = Modifier
						.padding(6.dp),
				) {
					Image(
						painter = painterResource(id = R.drawable.ic_google),
						contentDescription = null,
						modifier = Modifier
							.padding(end = 8.dp),
					)
					
					Text("Sign-in with Google")
				}
			}
			
			
			
			// Create an account button
			GradientButton(
				shape = RoundedCornerShape(18.dp),
				contentPadding = PaddingValues(),
				brush = Brush.horizontalGradient(
					colors = listOf(
						Color(0xFFF9881F),
						Color(0xFFFF774C),
					)
				),
				onClick = {
				
				},
				modifier = Modifier
					.fillMaxWidth()
					.height(56.dp)
					.padding(start = 24.dp, end = 24.dp, top = 8.dp)
			) {
				Text(
					text = "Create an account",
					fontSize = TextUnit(14f, TextUnitType.Sp),
					color = white,
					style = typographyDmSans().body1.copy(
						fontWeight = FontWeight.Bold
					)
				)
			}
			
			
			
			// Login to my account button
			TransparentButton(
				onClick = {
				
				},
				modifier = Modifier
					.padding(bottom = 16.dp)
			) {
				Text(
					text = "Login to my account",
					fontSize = TextUnit(14f, TextUnitType.Sp),
					color = orange,
					style = typographyDmSans().body1.copy(
						fontWeight = FontWeight.Bold
					)
				)
			}
		}
		
	}
}

@OptIn(ExperimentalUnitApi::class)
//@Preview(showSystemUi = true)
@Composable
fun ForgotPasswordScreenPreview() {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
		modifier = Modifier
			.fillMaxSize()
	) {
		
		Text(
			text = "Forgot password",
			color = black,
			style = typographyDmSans().body1.copy(
				fontSize = TextUnit(24f, TextUnitType.Sp),
				fontWeight = FontWeight.Bold
			)
		)
		
		
		
		Text(
			text = "Enter your email address to request a password reset.",
			color = black,
			style = typographySkModernist().body1.copy(
				fontSize = TextUnit(14f, TextUnitType.Sp),
				fontWeight = FontWeight.Normal,
				textAlign = TextAlign.Center
			),
			modifier = Modifier
				.padding(top = 16.dp, start = 16.dp, end = 16.dp)
		)
		
		
		
		OutlinedTextField(
			value = "",
			shape = RoundedCornerShape(16.dp),
			onValueChange = {},
			label = {
				Text("Email")
			},
			placeholder = {
				Text("example@foodify.com")
			},
			leadingIcon = {
				Icon(
					imageVector = Icons.Default.Email,
					contentDescription = null
				)
			},
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 48.dp, start = 16.dp, end = 16.dp)
		)
		
		
		
		GradientButton(
			shape = RoundedCornerShape(18.dp),
			contentPadding = PaddingValues(),
			brush = Brush.horizontalGradient(
				colors = listOf(
					Color(0xFFF9881F),
					Color(0xFFFF774C),
				)
			),
			onClick = {
			
			},
			modifier = Modifier
				.fillMaxWidth()
				.padding(start = 24.dp, end = 24.dp, top = 64.dp)
				.height(48.dp)
		) {
			Text(
				text = "Send",
				fontSize = TextUnit(16f, TextUnitType.Sp),
				color = white,
				style = typographyDmSans().body1.copy(
					fontWeight = FontWeight.Bold
				)
			)
		}
	}
}

@OptIn(ExperimentalUnitApi::class)
//@Preview(showSystemUi = true)
@Composable
fun ResetPasswordScreenPreview() {
	
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
		modifier = Modifier
			.fillMaxSize()
	) {
		
		Text(
			text = "Forgot password",
			color = black,
			style = typographyDmSans().body1.copy(
				fontSize = TextUnit(24f, TextUnitType.Sp),
				fontWeight = FontWeight.Bold
			)
		)
		
		
		
		Text(
			text = "A reset code has been sent to your email",
			color = black,
			style = typographySkModernist().body1.copy(
				fontSize = TextUnit(14f, TextUnitType.Sp),
				fontWeight = FontWeight.Bold,
				textAlign = TextAlign.Center
			),
			modifier = Modifier
				.padding(top = 16.dp, start = 16.dp, end = 16.dp)
		)
		
		
		
		OTPField(
			onValueChange = { index, s ->
			
			},
			modifier = Modifier
				.padding(top = 48.dp)
		)
		
		
		
		GradientButton(
			shape = RoundedCornerShape(18.dp),
			contentPadding = PaddingValues(),
			brush = Brush.horizontalGradient(
				colors = listOf(
					Color(0xFFF9881F),
					Color(0xFFFF774C),
				)
			),
			onClick = {
			
			},
			modifier = Modifier
				.fillMaxWidth()
				.padding(start = 24.dp, end = 24.dp, top = 64.dp)
				.height(48.dp)
		) {
			Text(
				text = "Reset",
				fontSize = TextUnit(16f, TextUnitType.Sp),
				color = white,
				style = typographyDmSans().body1.copy(
					fontWeight = FontWeight.Bold
				)
			)
		}
	}
}





@OptIn(ExperimentalUnitApi::class)
//@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
	
	val foodCategories = listOf(
		FoodCategory.sample_2,
		FoodCategory.sample_2,
		FoodCategory.sample_2,
		FoodCategory.sample_2,
		FoodCategory.sample_2,
	)
	
	val foods = listOf(
		Food.sample,
		Food.sample,
		Food.sample,
		Food.sample,
		Food.sample,
	)
	
	val scaffoldState = rememberScaffoldState()
	
	Scaffold(
		scaffoldState = scaffoldState,
		backgroundColor = background,
		topBar = {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.height(56.dp)
					.background(background)
			) {
				
				IconButton(
					onClick = {},
					modifier = Modifier
						.size(48.dp)
						.padding(start = 16.dp)
						.background(
							color = white,
							shape = RoundedCornerShape(16.dp)
						)
						.align(Alignment.CenterStart)
				) {
					Icon(
						imageVector = Icons.Rounded.Menu,
						tint = black,
						contentDescription = null,
						modifier = Modifier
							.size(24.dp)
							.align(Alignment.Center)
					)
				}
				
				Text(
					text = "Home",
					style = typographyDmSans().body1.copy(
						fontSize = TextUnit(24f, TextUnitType.Sp),
						fontWeight = FontWeight.SemiBold,
						textAlign = TextAlign.Center
					),
					modifier = Modifier
						.fillMaxWidth()
						.align(Alignment.Center)
				)
				
				IconButton(
					onClick = {},
					modifier = Modifier
						.size(48.dp)
						.padding(end = 8.dp)
						.background(
							color = white,
							shape = RoundedCornerShape(16.dp)
						)
						.align(Alignment.CenterEnd)
				) {
					Image(
						painter = painterResource(id = R.drawable.ic_profile),
						contentDescription = null,
						modifier = Modifier
							.size(56.dp, 56.dp)
							.align(Alignment.Center)
					)
				}
			}
		},
		bottomBar = {
			BottomAppBar(
				elevation = 8.dp,
				backgroundColor = white,
				cutoutShape = RoundedCornerShape(100),
				modifier = Modifier
					.clip(RoundedCornerShape(32.dp, 32.dp, 0.dp, 0.dp)),
			) {
				BottomNavigation(
					elevation = 0.dp,
					backgroundColor = white,
				) {
					Row {
						FoodifyBottomNavigation.values.forEach { foodifyBottomNavigation ->
							IconButton(
								onClick = {},
								modifier = Modifier
									.weight(
										0.25f
									)
									.padding(
										end = if (foodifyBottomNavigation.name == FoodifyBottomNavigation.Companion.Favorite.name) 32.dp
										else 0.dp,
										
										start = if (foodifyBottomNavigation.name == FoodifyBottomNavigation.Companion.Notification.name) 32.dp
										else 0.dp
									)
							) {
								if (foodifyBottomNavigation.name == FoodifyBottomNavigation.Companion.ShoppingCart.name) {
									BadgedBox(
										badge = {
											Badge(
												backgroundColor = Color(0xFFFE554A),
											) {
												Text(
													text = "2",
													color = white,
													modifier = Modifier
														.background(Color(0xFFFE554A))
														.clip(RoundedCornerShape(100))
												)
											}
										},
									) {
										Image(
											painter = painterResource(
												id = if (FoodifyBottomNavigation.Companion.ShoppingCart.name == foodifyBottomNavigation.name) foodifyBottomNavigation.iconSelected
												else foodifyBottomNavigation.icon
											),
											contentDescription = null
										)
									}
								} else {
									Image(
										painter = painterResource(
											id = foodifyBottomNavigation.icon
										),
										contentDescription = null
									)
								}
							}
						}
					}
				}
			}
		}
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
		) {
			Text(
				text = "Enjoy Delicious food",
				style = typographyDmSans().body1.copy(
					fontWeight = FontWeight.Bold,
					fontSize = TextUnit(24f, TextUnitType.Sp)
				),
				modifier = Modifier
					.padding(top = 32.dp, start = 24.dp)
			)
			
			LazyRow(
				modifier = Modifier
					.padding(top = 24.dp)
			) {
				items(foodCategories) { foodCategory ->
					FoodCategoryMenu(
						foodCategory = foodCategory,
						selected = true,
						onClick = {}
					)
				}
			}
			
			
			
			Column (
				modifier = Modifier
					.padding(top = 48.dp)
			) {
				
				// Popular restaurant
				Box(
					modifier = Modifier.fillMaxWidth()
				) {
					Text(
						text = "Popular restaurant",
						style = typographyDmSans().body1.copy(
							fontSize = TextUnit(16f, TextUnitType.Sp),
							fontWeight = FontWeight.Bold
						),
						modifier = Modifier
							.align(Alignment.CenterStart)
							.padding(start = 24.dp)
					)
					
					TransparentButton(
						indication = rememberRipple(color = white),
						onClick = {},
						modifier = Modifier
							.align(Alignment.CenterEnd)
					) {
						Text(
							text = "View All(29)",
							style = typographySkModernist().body1.copy(
								color = orange,
								fontSize = TextUnit(14f, TextUnitType.Sp),
								fontWeight = FontWeight.Normal,
								textDecoration = TextDecoration.Underline
							),
						)
					}
				}
				
				
				
				LazyRow(
					modifier = Modifier
						.padding(top = 24.dp)
				) {
					itemsIndexed(foods) { i, food ->
						food.isFavorite = (i % 2) == 0
						
						FoodMenu(
							food = food,
							onClick = {},
							onFavoriteClicked = {}
						)
					}
				}
			}
			
			
			
		}
	}
}





@OptIn(ExperimentalUnitApi::class)
//@Preview(showSystemUi = true)
@Composable
fun FoodDetailScreen() {
	
	val food = Food.sample
	
	val selectedTopping by remember { mutableStateOf(Topping.values[0]) }
	
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(background)
	) {
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			modifier = Modifier
				.fillMaxSize()
				.verticalScroll(rememberScrollState())
				.background(background)
		) {
			
			Card(
				elevation = 0.2.dp,
				shape = RoundedCornerShape(24.dp),
				backgroundColor = white,
				modifier = Modifier
					.size(256.dp)
					.padding(top = 48.dp)
			) {
				Box(
					modifier = Modifier.fillMaxSize()
				) {
					Image(
						painter = painterResource(id = food.image),
						contentDescription = null,
						modifier = Modifier
							.size(160.dp)
							.align(Alignment.Center)
					)
				}
			}
			
			
			
			
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.padding(top = 24.dp)
					.clip(RoundedCornerShape(50))
					.background(
						Brush.horizontalGradient(
							colors = listOf(
								Color(0xFFF9881F),
								Color(0xFFFF774C),
							)
						)
					)
			) {
				IconButton(
					onClick = {},
					modifier = Modifier
						.padding(start = 4.dp, end = 4.dp)
				) {
					Icon(
						painter = painterResource(id = R.drawable.ic_horizontal_rule_rounded),
						tint = white,
						contentDescription = null,
						modifier = Modifier
							.size(24.dp)
					)
				}
				
				Text(
					text = "0",
					style = typographyDmSans().body1.copy(
						color = white,
						fontSize = TextUnit(16f, TextUnitType.Sp),
						fontWeight = FontWeight.Bold
					)
				)
				
				IconButton(
					onClick = {},
					modifier = Modifier
						.padding(start = 4.dp, end = 4.dp)
				) {
					Icon(
						imageVector = Icons.Rounded.Add,
						tint = white,
						contentDescription = null,
						modifier = Modifier
							.size(24.dp)
					)
				}
			}
			
			
			
			Text(
				text = food.name,
				style = typographyDmSans().body1.copy(
					fontSize = TextUnit(24f, TextUnitType.Sp),
					fontWeight = FontWeight.Bold
				),
				modifier = Modifier
					.padding(top = 32.dp)
			)
			
			
			
			Row(
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.Center,
				modifier = Modifier
					.fillMaxWidth()
			) {
				
				// Rating
				Row(
					verticalAlignment = Alignment.CenterVertically,
					modifier = Modifier
						.padding(16.dp)
				) {
					Icon(
						painter = painterResource(id = R.drawable.ic_star),
						tint = Color(0xFFF5A62E),
						contentDescription = null,
						modifier = Modifier
							.size(18.dp)
					)
					
					Text(
						text = "${food.rating.toString()[0]}+",
						style = typographySkModernist().body1.copy(
							fontSize = TextUnit(12f, TextUnitType.Sp),
						),
						modifier = Modifier
							.padding(start = 8.dp)
					)
				}
				
				Row(
					verticalAlignment = Alignment.CenterVertically,
					modifier = Modifier
						.padding(16.dp)
				) {
					Image(
						painter = painterResource(id = R.drawable.flame),
						contentDescription = null,
						modifier = Modifier
							.size(18.dp)
					)
					
					Text(
						text = "${food.calories}cal",
						style = typographySkModernist().body1.copy(
							fontSize = TextUnit(12f, TextUnitType.Sp),
						),
						modifier = Modifier
							.padding(start = 8.dp)
					)
				}
				
				Row(
					verticalAlignment = Alignment.CenterVertically,
					modifier = Modifier
						.padding(16.dp)
				) {
					Image(
						painter = painterResource(id = R.drawable.alarm_clock),
						contentDescription = null,
						modifier = Modifier
							.size(18.dp)
					)
					
					Text(
						text = "${TimeUnit.MILLISECONDS.toMinutes(food.cookTimeFrom)}-${TimeUnit.MILLISECONDS.toMinutes(food.cookTimeUntil)}min",
						style = typographySkModernist().body1.copy(
							fontSize = TextUnit(12f, TextUnitType.Sp),
						),
						modifier = Modifier
							.padding(start = 8.dp)
					)
				}
			}
			
			
			
			ExpandedText(
				text = food.content,
				expandedText = food.contentFull,
				expandedTextButton = " more",
				shrinkTextButton = " less",
				textStyle = typographySkModernist().body1.copy(
					color = Color(0xFF3D3D3D)
				),
				expandedTextButtonStyle = typographySkModernist().body1.copy(
					color = orange,
				),
				shrinkTextButtonStyle = typographySkModernist().body1.copy(
					color = orange,
				),
				modifier = Modifier
					.padding(top = 32.dp, start = 24.dp, end = 16.dp)
			)
			
			Text(
				text = "Toppings for you",
				style = typographyDmSans().body1.copy(
					color = Color(0xFF3D3D3D),
					fontSize = TextUnit(16f, TextUnitType.Sp),
					fontWeight = FontWeight.SemiBold
				),
				modifier = Modifier
					.padding(top = 32.dp, start = 24.dp)
					.align(Alignment.Start)
			)
			
			LazyRow(
				modifier = Modifier
					.padding(top = 8.dp, bottom = 72.dp)
			) {
				items(Topping.values) { topping ->
					ToppingMenu(
						topping = topping,
						isSelected = selectedTopping == topping,
						onClick = {}
					)
				}
			}
		}
		
		
		
		GradientButton(
			shape = RoundedCornerShape(18.dp),
			contentPadding = PaddingValues(),
			brush = Brush.horizontalGradient(
				colors = listOf(
					Color(0xFFF9881F),
					Color(0xFFFF774C),
				)
			),
			onClick = {},
			modifier = Modifier
				.fillMaxWidth()
				.height(72.dp)
				.padding(bottom = 24.dp, start = 16.dp, end = 16.dp)
				.align(Alignment.BottomCenter)
		) {
			Text(
				text = "Add to cart",
				style = typographySkModernist().body1.copy(
					color = white,
					fontSize = TextUnit(14f, TextUnitType.Sp),
					fontWeight = FontWeight.Bold
				)
			)
		}
	}
}





@OptIn(ExperimentalUnitApi::class)
//@Preview(showSystemUi = true)
@Composable
fun FoodCartScreenPreview() {
	
	val foods = listOf(
		Food.sample.copy(),
		Food.sample.copy(),
		Food.sample.copy(),
		Food.sample.copy(),
	)
	
	Column(
		modifier = Modifier
			.fillMaxSize()
	) {
	
		Text(
			text = "Your cart",
			style = typographyDmSans().body1.copy(
				color = black.copy(alpha = 0.8f),
				fontWeight = FontWeight.Bold,
				fontSize = TextUnit(24f, TextUnitType.Sp)
			),
			modifier = Modifier
				.padding(top = 32.dp, start = 16.dp)
		)
		
		LazyColumn(
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 24.dp)
		) {
			items(foods) { food ->
				FoodCartItem(
					food = food,
					actionRowContent = {},
					onPriceChange = {}
				)
			}
		}
		
		Box(
			modifier = Modifier
				.fillMaxSize()
				.padding(top = 32.dp, start = 32.dp, end = 32.dp)
		) {
			Text(
				text = "Total",
				style = typographySkModernist().body1.copy(
					color = black.copy(0.8f),
					fontSize = TextUnit(16f, TextUnitType.Sp)
				),
				modifier = Modifier
					.align(Alignment.CenterStart)
			)
			
			Text(
				text = "$345",
				style = typographyDmSans().body1.copy(
					color = black.copy(0.8f),
					fontWeight = FontWeight.Bold,
					fontSize = TextUnit(24f, TextUnitType.Sp)
				),
				modifier = Modifier
					.align(Alignment.CenterEnd)
			)
		}
	}
}





@OptIn(ExperimentalUnitApi::class)
//@Preview(showSystemUi = true)
@Composable
fun MainPaymentScreenPreview() {
	
	val paymentMethod = listOf(
		R.drawable.mastercard,
		R.drawable.paypal,
		R.drawable.stripe
	)
	
	var selectedPayment by remember { mutableStateOf(1) }
	
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(background)
	) {
		
		Text(
			text = "Delivery method",
			style = typographyDmSans().body1.copy(
				color = black.copy(alpha = 0.8f),
				fontWeight = FontWeight.Bold,
				fontSize = TextUnit(24f, TextUnitType.Sp)
			),
			modifier = Modifier
				.padding(top = 32.dp, start = 24.dp)
		)
		
		
		
		// Address
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 32.dp, start = 24.dp, end = 24.dp)
		) {
			Text(
				text = "137 Teaticket Hwy, East Falmouth MA 2536",
				maxLines = 2,
				overflow = TextOverflow.Ellipsis,
				style = typographyDmSans().body1.copy(
					color = black.copy(alpha = 0.8f),
					fontSize = TextUnit(14f, TextUnitType.Sp)
				),
				modifier = Modifier
					.weight(1f)
			)
			
			TransparentButton(
				onClick = {}
			) {
				Text(
					text = "Change",
					style = typographySkModernist().body1.copy(
						color = orange,
						fontWeight = FontWeight.Bold,
						fontSize = TextUnit(14f, TextUnitType.Sp)
					)
				)
			}
		}
		
		
		
		// Phone number
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 24.dp, start = 24.dp, end = 24.dp)
		) {
			Text(
				text = "+234 9011039271",
				maxLines = 1,
				overflow = TextOverflow.Ellipsis,
				style = typographyDmSans().body1.copy(
					color = black.copy(alpha = 0.8f),
					fontSize = TextUnit(14f, TextUnitType.Sp)
				),
				modifier = Modifier
					.weight(1f)
			)
			
			TransparentButton(
				onClick = {}
			) {
				Text(
					text = "Change",
					style = typographySkModernist().body1.copy(
						color = orange,
						fontWeight = FontWeight.Bold,
						fontSize = TextUnit(14f, TextUnitType.Sp)
					)
				)
			}
		}
		
		
		
		Text(
			text = "Payment",
			style = typographyDmSans().body1.copy(
				color = black.copy(alpha = 0.8f),
				fontWeight = FontWeight.Bold,
				fontSize = TextUnit(24f, TextUnitType.Sp)
			),
			modifier = Modifier
				.padding(top = 32.dp, start = 24.dp, end = 24.dp)
		)
		
		
		
		LazyRow(
			modifier = Modifier
				.padding(top = 24.dp, start = 24.dp, end = 24.dp)
		) {
			
			item {
				IconButton(
					onClick = {},
					modifier = Modifier
						.size(72.dp)
						.padding(8.dp)
						.drawBehind {
							drawRoundRect(
								color = Color.Gray,
								cornerRadius = CornerRadius(100f, 100f),
								style = Stroke(
									width = 2f,
									pathEffect = PathEffect.dashPathEffect(
										floatArrayOf(10f, 10f),
										0f
									)
								)
							)
						}
				) {
					Icon(
						imageVector = Icons.Rounded.Add,
						contentDescription = null
					)
				}
			}
			
			itemsIndexed(paymentMethod) { i, item ->
				IconButton(
					onClick = {},
					modifier = Modifier
						.size(72.dp)
						.padding(8.dp)
						.border(
							if (selectedPayment != -1) {
								if (selectedPayment == i) BorderStroke(2.dp, orange)
								else BorderStroke(1.dp, Color.Transparent)
							} else BorderStroke(1.dp, Color.Transparent),
							shape = RoundedCornerShape(16.dp)
						)
						.clip(RoundedCornerShape(16.dp))
						.background(white)
				) {
					Image(
						painter = painterResource(id = item),
						contentDescription = null,
						modifier = Modifier
							.size(32.dp)
					)
				}
			}
		}
		
		
		
		// Pay on arrival
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 24.dp, start = 24.dp, end = 24.dp)
		) {
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.fillMaxWidth()
					.clip(RoundedCornerShape(16.dp))
					.background(white)
			) {
				CircularCheckbox(
					checked = true,
					onCheckedChange = {},
					modifier = Modifier
						.padding(start = 14.dp, top = 8.dp, bottom = 8.dp)
						.clip(RoundedCornerShape(100))
				)
				
				Text(
					text = "Pay on arrival",
					style = typographyDmSans().body1.copy(
						color = black.copy(alpha = 0.8f),
						fontWeight = FontWeight.Normal,
						fontSize = TextUnit(14f, TextUnitType.Sp)
					)
				)
			}
			
			Text(
				text = "Pay with cash/POS upon arrival ",
				style = typographyDmSans().body1.copy(
					color = orange,
					fontWeight = FontWeight.Normal,
					fontSize = TextUnit(12f, TextUnitType.Sp)
				),
				modifier = Modifier
					.padding(top = 16.dp)
			)
		}
		
		
		
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 32.dp)
		) {
			
			// Delivery fee
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.padding(start = 24.dp, end = 24.dp)
			) {
				Text(
					text = "Delivery Fee",
					style = typographyDmSans().body1.copy(
						color = black,
						fontWeight = FontWeight.Normal,
						fontSize = TextUnit(15f, TextUnitType.Sp)
					),
					modifier = Modifier
						.align(Alignment.CenterStart)
				)
				
				Text(
					text = "$20",
					style = typographyDmSans().body1.copy(
						color = black.copy(alpha = 0.8f),
						fontWeight = FontWeight.Normal,
						fontSize = TextUnit(14f, TextUnitType.Sp)
					),
					modifier = Modifier
						.align(Alignment.CenterEnd)
				)
			}
			
			
			
			// Subtotal
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 8.dp, bottom = 8.dp, start = 24.dp, end = 24.dp)
			) {
				Text(
					text = "Subtotal",
					style = typographyDmSans().body1.copy(
						color = black,
						fontWeight = FontWeight.Normal,
						fontSize = TextUnit(15f, TextUnitType.Sp)
					),
					modifier = Modifier
						.align(Alignment.CenterStart)
				)
				
				Text(
					text = "$520",
					style = typographyDmSans().body1.copy(
						color = black.copy(alpha = 0.8f),
						fontWeight = FontWeight.Normal,
						fontSize = TextUnit(14f, TextUnitType.Sp)
					),
					modifier = Modifier
						.align(Alignment.CenterEnd)
				)
			}
			
			
			
			Divider(
				color = black.copy(alpha = 0.6f),
				thickness = 1.dp,
				modifier = Modifier
					.fillMaxWidth()
			)
			
			
			
			// Total
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 8.dp, start = 24.dp, end = 24.dp, bottom = 32.dp)
			) {
				Text(
					text = "Total",
					style = typographyDmSans().body1.copy(
						color = black,
						fontWeight = FontWeight.Normal,
						fontSize = TextUnit(15f, TextUnitType.Sp)
					),
					modifier = Modifier
						.align(Alignment.CenterStart)
				)
				
				Text(
					text = "$540",
					style = typographyDmSans().body1.copy(
						color = black.copy(alpha = 0.8f),
						fontWeight = FontWeight.Bold,
						fontSize = TextUnit(24f, TextUnitType.Sp)
					),
					modifier = Modifier
						.align(Alignment.CenterEnd)
				)
			}
		}
		
		
		
		GradientButton(
			shape = RoundedCornerShape(18.dp),
			contentPadding = PaddingValues(),
			brush = Brush.horizontalGradient(
				colors = listOf(
					Color(0xFFF9881F),
					Color(0xFFFF774C),
				)
			),
			onClick = {},
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 32.dp, start = 24.dp, end = 24.dp)
				.height(48.dp)
		) {
			Text(
				text = "Proceed to payment",
				style = typographySkModernist().body1.copy(
					color = white,
					fontSize = TextUnit(14f, TextUnitType.Sp),
					fontWeight = FontWeight.Bold
				)
			)
		}
	}
}





@OptIn(ExperimentalUnitApi::class)
//@Preview(showSystemUi = true)
@Composable
fun CreditCardPaymentScreenPreview() {
	
	var cardDetails by remember { mutableStateOf("") }
	
	var expDate by remember { mutableStateOf("") }
	
	var cvv by remember { mutableStateOf("") }
	
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(start = 24.dp, end = 24.dp)
	) {
		
		Text(
			text = "Payment",
			style = typographyDmSans().body1.copy(
				color = black.copy(alpha = 0.8f),
				fontWeight = FontWeight.Bold,
				fontSize = TextUnit(24f, TextUnitType.Sp)
			),
			modifier = Modifier
				.padding(top = 32.dp)
		)
		
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 32.dp)
		) {
			Text(
				text = "Card details",
				style = typographySkModernist().body1.copy(
					color = black.copy(alpha = 0.8f),
					fontWeight = FontWeight.Normal,
					fontSize = TextUnit(12f, TextUnitType.Sp)
				),
				modifier = Modifier
					.padding(start = 16.dp)
			)
			
			OutlinedTextField(
				value = cardDetails,
				shape = RoundedCornerShape(16.dp),
				singleLine = true,
				colors = TextFieldDefaults.outlinedTextFieldColors(
					backgroundColor = white,
					unfocusedBorderColor = Color.Gray.copy(alpha = 0.8f),
					focusedBorderColor = Color.Gray.copy(alpha = 0.8f)
				),
				onValueChange = { s ->
					cardDetails = s
				},
				placeholder = {
					Text(
						text = "Enter card details",
						style = typographySkModernist().body1.copy(
							color = Color.Gray.copy(alpha = 0.8f),
							fontSize = TextUnit(14f, TextUnitType.Sp)
						)
					)
				},
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 8.dp)
					.border(
						width = 1.dp,
						color = Color.Gray,
						shape = RoundedCornerShape(16.dp)
					)
			)
		}
		
		
		
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 24.dp)
		) {
			
			Column(
				modifier = Modifier
					.weight(1f)
					.padding(end = 8.dp)
			) {
				Text(
					text = "Exp date",
					style = typographySkModernist().body1.copy(
						color = black.copy(alpha = 0.8f),
						fontSize = TextUnit(12f, TextUnitType.Sp)
					),
					modifier = Modifier
						.padding(start = 16.dp, bottom = 8.dp)
				)
				
				OutlinedTextField(
					value = expDate,
					shape = RoundedCornerShape(16.dp),
					keyboardOptions = KeyboardOptions(
						keyboardType = KeyboardType.Number,
						imeAction = ImeAction.Next
					),
					colors = TextFieldDefaults.outlinedTextFieldColors(
						backgroundColor = white,
						unfocusedBorderColor = Color.Gray.copy(alpha = 0.8f),
						focusedBorderColor = Color.Gray.copy(alpha = 0.8f)
					),
					placeholder = {
						Text(
							text = "DD/MM",
							style = typographySkModernist().body1.copy(
								color = Color.Gray.copy(alpha = 0.8f),
								fontSize = TextUnit(14f, TextUnitType.Sp)
							)
						)
					},
					onValueChange = { s ->
						expDate = if (s[s.length - 1].isDigit()) {
							if (s.length == 2) "$s/" else s
						} else s.substring(0, s.length - 2)
					}
				)
			}
			
			Column(
				modifier = Modifier
					.weight(1f)
					.padding(start = 8.dp)
			) {
				Text(
					text = "CVV",
					style = typographySkModernist().body1.copy(
						color = black.copy(alpha = 0.8f),
						fontSize = TextUnit(12f, TextUnitType.Sp)
					),
					modifier = Modifier
						.padding(start = 16.dp, bottom = 8.dp)
				)
				
				OutlinedTextField(
					value = cvv,
					shape = RoundedCornerShape(16.dp),
					keyboardOptions = KeyboardOptions(
						keyboardType = KeyboardType.Number,
						imeAction = ImeAction.Next
					),
					colors = TextFieldDefaults.outlinedTextFieldColors(
						backgroundColor = white,
						unfocusedBorderColor = Color.Gray.copy(alpha = 0.8f),
						focusedBorderColor = Color.Gray.copy(alpha = 0.8f)
					),
					placeholder = {
						Text(
							text = "CVV",
							style = typographySkModernist().body1.copy(
								color = Color.Gray.copy(alpha = 0.8f),
								fontSize = TextUnit(14f, TextUnitType.Sp)
							)
						)
					},
					onValueChange = { s ->
						if (s.length < 3) {
							cvv = if (s[s.length - 1].isDigit()) {
								if (s.length == 2) "$s/" else s
							} else s.substring(0, s.length - 2)
						}
					}
				)
			}
		}
		
		
		
		Spacer(
			modifier = Modifier
				.weight(1f)
		)
		
		
		
		GradientButton(
			shape = RoundedCornerShape(18.dp),
			contentPadding = PaddingValues(),
			brush = Brush.horizontalGradient(
				colors = listOf(
					Color(0xFFF9881F),
					Color(0xFFFF774C),
				)
			),
			onClick = {},
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 32.dp)
				.height(48.dp)
		) {
			Text(
				text = "Pay now",
				style = typographySkModernist().body1.copy(
					color = white,
					fontSize = TextUnit(14f, TextUnitType.Sp),
					fontWeight = FontWeight.Bold
				)
			)
		}
	}
}





@OptIn(ExperimentalUnitApi::class)
//@Preview(showSystemUi = true)
@Composable
fun CompletePaymentScreenPreview() {
	
	val localConfig = LocalConfiguration.current
	
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(background)
	) {
		
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center,
			modifier = Modifier
				.fillMaxWidth()
				.align(Alignment.Center)
				.padding(bottom = 64.dp)
		) {
			Image(
				painter = painterResource(id = R.drawable.ic_payment_success),
				contentDescription = null,
				modifier = Modifier
					.size(
						width = (localConfig.screenWidthDp / 2).dp,
						height = (localConfig.screenWidthDp / 2).dp
					)
			)
			
			Text(
				text = "Your order has been successfully placed",
				style = typographyDmSans().body1.copy(
					color = black.copy(alpha = 0.8f),
					fontWeight = FontWeight.Bold,
					fontSize = TextUnit(24f, TextUnitType.Sp),
					textAlign = TextAlign.Center
				),
				modifier = Modifier
					.padding(start = 32.dp, end = 32.dp, top = 16.dp)
			)
			
			Text(
				text = "Sit and relax while your orders is being worked on . It’ll take 5min before you get it",
				style = typographyDmSans().body1.copy(
					color = black.copy(alpha = 0.8f),
					fontWeight = FontWeight.Normal,
					fontSize = TextUnit(15f, TextUnitType.Sp),
					textAlign = TextAlign.Center
				),
				modifier = Modifier
					.padding(start = 24.dp, end = 24.dp, top = 16.dp)
			)
		}
		
		GradientButton(
			shape = RoundedCornerShape(18.dp),
			contentPadding = PaddingValues(),
			brush = Brush.horizontalGradient(
				colors = listOf(
					Color(0xFFF9881F),
					Color(0xFFFF774C),
				)
			),
			onClick = {},
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 32.dp, start = 24.dp, end = 24.dp)
				.height(48.dp)
				.align(Alignment.BottomCenter)
		) {
			Text(
				text = "Go back to home",
				style = typographySkModernist().body1.copy(
					color = white,
					fontSize = TextUnit(14f, TextUnitType.Sp),
					fontWeight = FontWeight.Bold
				)
			)
		}
	}
}

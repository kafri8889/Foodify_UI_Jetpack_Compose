package com.anafthdev.foodify.ui

import android.os.Handler
import android.os.Looper
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
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
import androidx.compose.ui.platform.LocalContext
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.anafthdev.foodify.R
import com.anafthdev.foodify.data.FoodifyBottomNavigation
import com.anafthdev.foodify.data.FoodifyDestination
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
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@Composable
fun SplashScreen(
	navController: NavHostController
) {
	var hasNavigate by remember { mutableStateOf(false) }
	
	if (!hasNavigate) {
		Handler(Looper.getMainLooper()).postDelayed({
			navController.navigate(FoodifyDestination.WELCOME_SCREEN) {
				popUpTo(FoodifyDestination.SPLASH_SCREEN) {
					inclusive = true
				}
			}
		}, 2300)
		
		true.also { hasNavigate = it }
	}
	
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
		modifier = Modifier
			.fillMaxSize()
	) {
		Image(
			painter = painterResource(id = R.drawable.ic_foodify_logo),
			contentDescription = null,
			modifier = Modifier
				.size(100.dp, 67.dp)
		)
	}
}





@OptIn(ExperimentalUnitApi::class)
@Composable
fun HomeScreen(
	navController: NavHostController
) {
	
	val scope = rememberCoroutineScope()
	val scaffoldState = rememberScaffoldState()
	val homeNavController = rememberNavController()
	
	val navigationBackStackEntry by homeNavController.currentBackStackEntryAsState()
	val currentRoute = navigationBackStackEntry?.destination?.route ?: FoodifyDestination.Home.HOME_SCREEN
	
	var showHomeBottomBar by remember { mutableStateOf(false) }
	var selectedBottomNavigationItem by remember { mutableStateOf(FoodifyBottomNavigation.values[0].name) }
	
	selectedBottomNavigationItem = when(currentRoute) {
		FoodifyDestination.Home.HOME_SCREEN -> FoodifyBottomNavigation.Companion.Home.name
		FoodifyDestination.Home.FOOD_CART_SCREEN -> FoodifyBottomNavigation.Companion.ShoppingCart.name
		else -> selectedBottomNavigationItem
	}
	
	showHomeBottomBar = when (currentRoute) {
		FoodifyDestination.Home.HOME_SCREEN -> true
		FoodifyDestination.Home.FOOD_DETAIL_SCREEN -> false
		FoodifyDestination.Home.FOOD_CART_SCREEN -> true
		else -> true
	}
	
	Scaffold(
		scaffoldState = scaffoldState,
		backgroundColor = background,
		drawerBackgroundColor = background,
		isFloatingActionButtonDocked = true,
		floatingActionButtonPosition = FabPosition.Center,
		floatingActionButton = {
			if (showHomeBottomBar) {
				FloatingActionButton(
					backgroundColor = Color(0xFFF9881F),
					onClick = {},
				) {
					Icon(
						painter = painterResource(id = R.drawable.ic_search),
						tint = white,
						contentDescription = null
					)
				}
			}
		},
		topBar = {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.height(56.dp)
					.background(background)
			) {
				
				IconButton(
					onClick = {
						scope.launch {
							scaffoldState.drawerState.open()
						}
					},
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
							.size(24.dp, 24.dp)
							.align(Alignment.Center)
					)
				}
			}
		},
		bottomBar = {
			if (showHomeBottomBar) {
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
									onClick = {
										selectedBottomNavigationItem = foodifyBottomNavigation.name
										homeNavController.navigate(
											when (selectedBottomNavigationItem) {
												FoodifyBottomNavigation.Companion.Home.name -> FoodifyDestination.Home.HOME_SCREEN
												FoodifyBottomNavigation.Companion.Favorite.name -> FoodifyDestination.Home.HOME_SCREEN
												FoodifyBottomNavigation.Companion.Notification.name -> FoodifyDestination.Home.HOME_SCREEN
												FoodifyBottomNavigation.Companion.ShoppingCart.name -> FoodifyDestination.Home.FOOD_CART_SCREEN
												else -> FoodifyDestination.Home.HOME_SCREEN
											}
										)
									},
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
													id = if (selectedBottomNavigationItem == foodifyBottomNavigation.name) foodifyBottomNavigation.iconSelected
													else foodifyBottomNavigation.icon
												),
												contentDescription = null
											)
										}
									} else {
										Image(
											painter = painterResource(
												id = if (selectedBottomNavigationItem == foodifyBottomNavigation.name) foodifyBottomNavigation.iconSelected
												else foodifyBottomNavigation.icon
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
		},
		drawerContent = {
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
							.padding(top = 32.dp)
					) {
						Drawer.values.forEach { drawer ->
							DrawerItem(
								drawer = drawer,
								paddingValues = PaddingValues(
									start = 24.dp,
									top = 12.dp,
									bottom = 12.dp,
									end = 24.dp
								),
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
					onClick = {
						navController.navigate(FoodifyDestination.WELCOME_SCREEN) {
							popUpTo(0)
						}
					},
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
	) {
		NavHost(
			navController = homeNavController,
			startDestination = FoodifyDestination.Home.HOME_SCREEN
		) {
			composable(
				route = FoodifyDestination.Home.HOME_SCREEN
			) {
				MainHomeScreen(
					homeNavController = homeNavController
				)
			}
			
			composable(
				route = FoodifyDestination.Home.FOOD_DETAIL_SCREEN
			) {
				FoodDetailScreen(
					food = Food.sample,
					homeNavController = homeNavController
				)
			}
			
			composable(
				route = FoodifyDestination.Home.FOOD_CART_SCREEN
			) {
				FoodCartScreen(
					navController = navController,
				)
			}
		}
	}
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun MainHomeScreen(
	homeNavController: NavHostController
) {
	
	val foodCategories = listOf(
		FoodCategory.sample_1,
		FoodCategory.sample_2,
		FoodCategory.sample_3,
		FoodCategory.sample_4,
	)
	
	val foods = listOf(
		Food.sample.copy(),
		Food.sample.copy(),
		Food.sample.copy(),
		Food.sample.copy(),
		Food.sample.copy(),
	)
	
	var selectedFoodCategory by remember { mutableStateOf(foodCategories[0].id) }
	
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
		
		
		
		// Food Category menu
		LazyRow(
			modifier = Modifier
				.padding(top = 32.dp)
		) {
			items(foodCategories) { foodCategory ->
				FoodCategoryMenu(
					foodCategory = foodCategory,
					selected = selectedFoodCategory == foodCategory.id,
					onClick = {
						selectedFoodCategory = foodCategory.id
					}
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
					var isFavorite by remember { mutableStateOf(food.isFavorite) }
					food.isFavorite = isFavorite
					
					FoodMenu(
						food = food,
						onClick = {
							homeNavController.navigate(FoodifyDestination.Home.FOOD_DETAIL_SCREEN) {
								popUpTo(FoodifyDestination.Home.HOME_SCREEN) {
									saveState = false
								}
								
								restoreState = false
								launchSingleTop = true
							}
						},
						onFavoriteClicked = { mIsFavorite ->
							isFavorite = mIsFavorite
						}
					)
				}
			}
		}
		
		
		
	}
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun FoodDetailScreen(
	food: Food,
	homeNavController: NavHostController
) {
	
	var orderCount by remember { mutableStateOf(0) }
	var selectedTopping by remember { mutableStateOf(listOf<Topping>()) }
	
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
					onClick = {
						if (orderCount > 0) orderCount -= 1
					},
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
					text = orderCount.toString(),
					style = typographyDmSans().body1.copy(
						color = white,
						fontSize = TextUnit(16f, TextUnitType.Sp),
						fontWeight = FontWeight.Bold
					)
				)
				
				IconButton(
					onClick = {
						orderCount += 1
					},
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
					color = black.copy(alpha = 0.8f)
				),
				expandedTextStyle = typographySkModernist().body1.copy(
					color = black.copy(alpha = 0.8f)
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
					.padding(top = 8.dp, bottom = 88.dp)
			) {
				items(Topping.values) { topping ->
					ToppingMenu(
						topping = topping,
						isSelected = selectedTopping.contains(topping),
						onClick = {
							selectedTopping = if (selectedTopping.contains(topping)) ArrayList(selectedTopping).apply { remove(topping) }
							else ArrayList(selectedTopping).apply { add(topping) }
						}
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
			onClick = {
				homeNavController.navigate(FoodifyDestination.Home.FOOD_CART_SCREEN) {
					popUpTo(FoodifyDestination.Home.FOOD_DETAIL_SCREEN) {
						saveState = false
					}
					
					restoreState = false
					launchSingleTop = true
				}
			},
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
@Composable
fun FoodCartScreen(
	navController: NavHostController
) {
	
	val foods = listOf(
		Food.sample.copy(),
		Food.sample.copy(),
		Food.sample.copy(),
		Food.sample.copy(),
	)
	
	val totalPrice = remember {
		mutableStateListOf<Double>().apply {
			foods.forEach {
				add(it.price)
			}
		}
	}
	
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
			itemsIndexed(foods) { i, food ->
				FoodCartItem(
					food = food,
					actionRowContent = {
						IconButton(
							onClick = {},
							modifier = Modifier
								.align(Alignment.CenterEnd)
						) {
							Image(
								painter = painterResource(id = R.drawable.ic_edit_icon),
								contentDescription = null,
							)
						}
						
						IconButton(
							onClick = {},
							modifier = Modifier
								.align(Alignment.CenterStart)
						) {
							Image(
								painter = painterResource(id = R.drawable.ic_delete_icon),
								contentDescription = null,
							)
						}
					},
					onPriceChange = { price ->
						totalPrice[i] = price
					}
				)
			}
			
			item {
				Column(
					modifier = Modifier
						.fillMaxWidth()
						.padding(top = 32.dp, start = 32.dp, end = 32.dp, bottom = 96.dp)
				) {
					Box(
						modifier = Modifier
							.fillMaxWidth()
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
							text = run {
								if (totalPrice.sum().toString().length > 6) {
									"$${totalPrice.sum().toString().substring(0, 6)}"
								} else "$${totalPrice.sum()}"
							},
							style = typographyDmSans().body1.copy(
								color = black.copy(0.8f),
								fontWeight = FontWeight.Bold,
								fontSize = TextUnit(24f, TextUnitType.Sp)
							),
							modifier = Modifier
								.align(Alignment.CenterEnd)
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
						onClick = {
							navController.navigate(FoodifyDestination.PAYMENT_SCREEN) {
								popUpTo(0)
							}
						},
						modifier = Modifier
							.fillMaxWidth()
							.height(72.dp)
							.padding(top = 24.dp)
					) {
						Text(
							text = "Process to payment",
							style = typographySkModernist().body1.copy(
								color = white,
								fontSize = TextUnit(14f, TextUnitType.Sp),
								fontWeight = FontWeight.Bold
							)
						)
					}  // Process to payment button
				}
			}
		}  // LazyColumn
		
		
		
	}
}





@Composable
fun PaymentScreen(
	navController: NavHostController
) {
	
	val paymentNavController = rememberNavController()
	
	BackHandler {
		navController.navigate(FoodifyDestination.HOME_SCREEN) {
			popUpTo(0)
		}
	}
	
	Scaffold(
		topBar = {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.height(56.dp)
					.background(background)
			) {
				IconButton(
					onClick = {
						navController.navigate(FoodifyDestination.HOME_SCREEN) {
							popUpTo(0)
						}
					},
					modifier = Modifier
						.padding(12.dp)
				) {
					Icon(
						painter = painterResource(id = R.drawable.ic_left_arrow),
						contentDescription = null
					)
				}
			}
		}
	) {
		NavHost(
			navController = paymentNavController,
			startDestination = FoodifyDestination.Payment.PAYMENT_SCREEN
		) {
			composable(FoodifyDestination.Payment.PAYMENT_SCREEN) {
				MainPaymentScreen(
					paymentNavController = paymentNavController
				)
			}
			
			composable(FoodifyDestination.Payment.CREDIT_CARD_PAYMENT_SCREEN) {
				CreditCardPaymentScreen(
					paymentNavController = paymentNavController
				)
			}
			
			composable(FoodifyDestination.Payment.COMPLETE_PAYMENT_SCREEN) {
				CompletePaymentScreen(
					navController = navController
				)
			}
		}
	}
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun MainPaymentScreen(
	paymentNavController: NavHostController
) {
	val paymentMethod = listOf(
		R.drawable.mastercard,
		R.drawable.paypal,
		R.drawable.stripe
	)
	
	var selectedPayment by remember { mutableStateOf(-1) }
	var isPayOnArrival by remember { mutableStateOf(false) }
	
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(background)
			.verticalScroll(rememberScrollState())
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
					onClick = {
						selectedPayment = i
					},
					modifier = Modifier
						.size(72.dp)
						.padding(8.dp)
						.border(
							if (selectedPayment != -1) {
								if (selectedPayment == i) BorderStroke(1.dp, orange)
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
					checked = isPayOnArrival,
					onCheckedChange = { mIsPayOnArrival ->
						isPayOnArrival = mIsPayOnArrival
					},
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
			onClick = {
				paymentNavController.navigate(FoodifyDestination.Payment.CREDIT_CARD_PAYMENT_SCREEN) {
					popUpTo(FoodifyDestination.Payment.PAYMENT_SCREEN) {
						saveState = false
					}
					
					restoreState = false
					launchSingleTop = true
				}
			},
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
@Composable
fun CreditCardPaymentScreen(
	paymentNavController: NavHostController
) {
	
	var cardDetails by remember { mutableStateOf("") }
	
	var expDate by remember { mutableStateOf("") }
	
	var cvv by remember { mutableStateOf("") }
	
	BackHandler {
		paymentNavController.navigate(FoodifyDestination.Payment.PAYMENT_SCREEN) {
			popUpTo(0)
		}
	}
	
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
				keyboardOptions = KeyboardOptions(
					keyboardType = KeyboardType.Text
				),
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
						keyboardType = KeyboardType.Text,
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
						if (s.length < 6) {
							expDate = s
						}
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
			onClick = {
				paymentNavController.navigate(FoodifyDestination.Payment.COMPLETE_PAYMENT_SCREEN) {
					popUpTo(0)
				}
			},
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
@Composable
fun CompletePaymentScreen(
	navController: NavHostController
) {
	
	val localConfig = LocalConfiguration.current
	
	BackHandler {
		navController.navigate(FoodifyDestination.HOME_SCREEN) {
			popUpTo(0)
		}
	}
	
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
			onClick = {
				navController.navigate(FoodifyDestination.HOME_SCREEN) {
					popUpTo(0)
				}
			},
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





@OptIn(ExperimentalUnitApi::class)
@Composable
fun WelcomeScreen(
	navController: NavHostController
) {
	
	val welcomeScreenNavController = rememberNavController()
	
	val navigationBackStackEntry by welcomeScreenNavController.currentBackStackEntryAsState()
	val currentRoute = navigationBackStackEntry?.destination?.route ?: FoodifyDestination.Welcome.WELCOME_SCREEN
	
	Scaffold(
		topBar = {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.height(56.dp)
			) {
				Row(
					modifier = Modifier.align(Alignment.Center)
				) {
					Image(
						painter = painterResource(id = R.drawable.foodify_logo),
						contentDescription = null,
						modifier = Modifier
							.size(32.dp, 22.dp)
					)
				}
				
				TransparentButton(
					indication = rememberRipple(color = background),
					onClick = {
						if (
							(currentRoute != FoodifyDestination.Welcome.FORGOT_PASSWORD_SCREEN) and
							(currentRoute != FoodifyDestination.Welcome.RESET_PASSWORD_SCREEN)
						) {
							navController.navigate(FoodifyDestination.HOME_SCREEN) {
								popUpTo(0)
							}
						} else {
							welcomeScreenNavController.navigate(FoodifyDestination.Welcome.SIGN_IN_SCREEN) {
								popUpTo(FoodifyDestination.Welcome.WELCOME_SCREEN)
							}
						}
					},
					modifier = Modifier
						.align(Alignment.CenterEnd)
				) {
					Text(
						text = when (currentRoute) {
							FoodifyDestination.Welcome.WELCOME_SCREEN -> "Skip"
							FoodifyDestination.Welcome.SIGN_UP_SCREEN -> "Skip"
							FoodifyDestination.Welcome.SIGN_IN_SCREEN -> "Skip"
							FoodifyDestination.Welcome.FORGOT_PASSWORD_SCREEN -> "Cancel"
							FoodifyDestination.Welcome.RESET_PASSWORD_SCREEN -> "Cancel"
							else -> "Skip"
						},
						style = typographySkModernist().body1.copy(
							fontSize = TextUnit(16f, TextUnitType.Sp)
						),
						color = orange,
					)
				}
			}
		}  // TopBar
	) {
		NavHost(
			navController = welcomeScreenNavController,
			startDestination = FoodifyDestination.Welcome.WELCOME_SCREEN
		) {
			composable(FoodifyDestination.Welcome.WELCOME_SCREEN) {
				MainWelcomeScreen(
					welcomeNavController = welcomeScreenNavController
				)
			}
			
			composable(FoodifyDestination.Welcome.SIGN_UP_SCREEN) {
				SignUpScreen(
					welcomeNavController = welcomeScreenNavController
				)
			}
			
			composable(FoodifyDestination.Welcome.SIGN_IN_SCREEN) {
				SignInScreen(
					navController = navController,
					welcomeNavController = welcomeScreenNavController
				)
			}
			
			composable(FoodifyDestination.Welcome.FORGOT_PASSWORD_SCREEN) {
				ForgotPasswordScreen(
					welcomeNavController = welcomeScreenNavController,
				)
			}
			
			composable(FoodifyDestination.Welcome.RESET_PASSWORD_SCREEN) {
				ResetPasswordScreen(
					welcomeNavController = welcomeScreenNavController
				)
			}
		}
	}
}

@OptIn(
	ExperimentalUnitApi::class,
	ExperimentalPagerApi::class,
	ExperimentalFoundationApi::class
)
@Composable
fun MainWelcomeScreen(
	welcomeNavController: NavHostController
) {
	
	val pagerState = rememberPagerState()
	
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
	) {
		
		Text(
			text = when (pagerState.currentPage) {
				0 -> "Order from your favourite stores or vendors"
				1 -> "Choose from a wide range of  delicious meals"
				2 -> "Enjoy instant delivery and payment"
				else -> "Choose from a wide range of  delicious meals"
			},
			style = typographyDmSans().body1.copy(
				fontSize = TextUnit(24f, TextUnitType.Sp),
				fontWeight = FontWeight.Bold,
				textAlign = TextAlign.Center,
			),
			modifier = Modifier
				.padding(top = 16.dp, start = 8.dp, end = 8.dp)
		)
		
		CompositionLocalProvider(
			LocalOverScrollConfiguration provides null  // Remove over scroll effect
		) {
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
						.padding(top = 16.dp, end = 16.dp, start = 16.dp)
				)
			}
		}
		
		HorizontalPagerIndicator(
			pagerState = pagerState,
			modifier = Modifier.padding(bottom = 32.dp)
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
				welcomeNavController.navigate(FoodifyDestination.Welcome.SIGN_UP_SCREEN) {
					popUpTo(FoodifyDestination.Welcome.WELCOME_SCREEN) {
						saveState = false
					}
					
					restoreState = false
					launchSingleTop = true
				}
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
		
		TransparentButton(
			indication = rememberRipple(color = background),
			onClick = {
				welcomeNavController.navigate(FoodifyDestination.Welcome.SIGN_IN_SCREEN) {
					popUpTo(FoodifyDestination.Welcome.WELCOME_SCREEN) {
						saveState = false
					}
					
					restoreState = false
					launchSingleTop = true
				}
			},
			modifier = Modifier
				.fillMaxWidth()
				.height(56.dp)
				.padding(start = 24.dp, end = 24.dp, top = 8.dp)
		) {
			Text(
				text = "Login",
				fontSize = TextUnit(14f, TextUnitType.Sp),
				color = orange,
				style = typographyDmSans().body1.copy(
					fontWeight = FontWeight.Bold
				)
			)
		}
	}
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun SignUpScreen(
	welcomeNavController: NavHostController
) {
	var passwordVisibility by remember { mutableStateOf(false) }
	var confirmPasswordVisibility by remember { mutableStateOf(false) }
	var email by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }
	var confirmPassword by remember { mutableStateOf("") }
	
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
				value = email,
				shape = RoundedCornerShape(16.dp),
				onValueChange = { s ->
					email = s			
				},
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
				value = password,
				shape = RoundedCornerShape(16.dp),
				visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
				onValueChange = { s -> 
					password = s
				},
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
					IconButton(
						onClick = {
							passwordVisibility = !passwordVisibility
						}
					) {
						Icon(
							painter = run {
								if (passwordVisibility) painterResource(id = R.drawable.ic_visibility_off)
								else painterResource(id = R.drawable.ic_visibility)
							},
							contentDescription = null
						)
					}
				},
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 16.dp)
			)
			
			
			
			// Confirm password text field
			OutlinedTextField(
				value = confirmPassword,
				shape = RoundedCornerShape(16.dp),
				visualTransformation = if (confirmPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
				onValueChange = { s ->
					confirmPassword = s
				},
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
					IconButton(
						onClick = {
							confirmPasswordVisibility = !confirmPasswordVisibility
						}
					) {
						Icon(
							painter = run {
								if (confirmPasswordVisibility) painterResource(id = R.drawable.ic_visibility_off)
								else painterResource(id = R.drawable.ic_visibility)
							},
							contentDescription = null
						)
					}
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
					
					Text("Sign-up with Google")
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
					welcomeNavController.navigate(FoodifyDestination.Welcome.WELCOME_SCREEN) {
						popUpTo(0)
					}
				},
				modifier = Modifier
					.fillMaxWidth()
					.height(56.dp)
					.padding(start = 24.dp, end = 24.dp, bottom = 8.dp)
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
				indication = rememberRipple(color = background),
				onClick = {
					welcomeNavController.navigate(FoodifyDestination.Welcome.SIGN_IN_SCREEN) {
						popUpTo(FoodifyDestination.Welcome.WELCOME_SCREEN) {
							saveState = false
						}
						
						restoreState = false
						launchSingleTop = true
					}
				},
				modifier = Modifier
					.fillMaxWidth()
					.height(56.dp)
					.padding(start = 24.dp, end = 24.dp)
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
@Composable
fun SignInScreen(
	navController: NavHostController,
	welcomeNavController: NavHostController,
) {
	
	var email by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }
	var passwordVisibility by remember { mutableStateOf(false) }
	
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
				value = email,
				shape = RoundedCornerShape(16.dp),
				onValueChange = { s ->
					email = s
				},
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
				value = password,
				shape = RoundedCornerShape(16.dp),
				visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
				onValueChange = { s ->
					password = s
				},
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
					IconButton(
						onClick = {
							passwordVisibility = !passwordVisibility
						}
					) {
						Icon(
							painter = run {
								if (passwordVisibility) painterResource(id = R.drawable.ic_visibility_off)
								else painterResource(id = R.drawable.ic_visibility)
							},
							contentDescription = null
						)
					}
				},
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 16.dp)
			)
			
			
			
			TransparentButton(
				indication = rememberRipple(color = background),
				onClick = {
					welcomeNavController.navigate(FoodifyDestination.Welcome.FORGOT_PASSWORD_SCREEN) {
						popUpTo(FoodifyDestination.Welcome.SIGN_IN_SCREEN) {
							saveState = false
						}
						
						restoreState = false
						launchSingleTop = true
					}
				},
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
					welcomeNavController.navigate(FoodifyDestination.Welcome.SIGN_UP_SCREEN) {
						popUpTo(FoodifyDestination.Welcome.WELCOME_SCREEN) {
							saveState = false
						}
						
						restoreState = false
						launchSingleTop = true
					}
				},
				modifier = Modifier
					.fillMaxWidth()
					.height(56.dp)
					.padding(start = 24.dp, end = 24.dp, bottom = 8.dp)
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
				indication = rememberRipple(color = background),
				onClick = {
					navController.navigate(FoodifyDestination.HOME_SCREEN) {
						popUpTo(0)
					}
				},
				modifier = Modifier
					.fillMaxWidth()
					.height(56.dp)
					.padding(start = 24.dp, end = 24.dp)
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
@Composable
fun ForgotPasswordScreen(
	welcomeNavController: NavHostController
) {
	var email by remember { mutableStateOf("") }
	
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
			value = email,
			shape = RoundedCornerShape(16.dp),
			onValueChange = { s ->
				email = s
			},
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
				welcomeNavController.navigate(FoodifyDestination.Welcome.RESET_PASSWORD_SCREEN) {
					popUpTo(FoodifyDestination.Welcome.SIGN_IN_SCREEN) {
						saveState = false
					}
					
					restoreState = false
					launchSingleTop = true
				}
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
@Composable
fun ResetPasswordScreen(
	welcomeNavController: NavHostController
) {
	
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
			length = OTPField.OTPLength_4,
			onValueChange = { index, s ->
			
			},
			textStyle = typographySkModernist().body1.copy(
				fontWeight = FontWeight.Bold,
				fontSize = TextUnit(14f, TextUnitType.Sp)
			),
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
				welcomeNavController.navigate(FoodifyDestination.Welcome.SIGN_IN_SCREEN) {
					popUpTo(FoodifyDestination.Welcome.WELCOME_SCREEN) {
						saveState = false
					}
					
					restoreState = false
					launchSingleTop = true
				}
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

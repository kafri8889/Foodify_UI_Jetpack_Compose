package com.anafthdev.foodify.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.anafthdev.foodify.R
import com.anafthdev.foodify.model.Food
import com.anafthdev.foodify.model.FoodCategory
import com.anafthdev.foodify.model.Topping
import com.anafthdev.foodify.ui.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TransparentButton(
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
	indication: Indication = rememberRipple(),
	interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
	shape: Shape = MaterialTheme.shapes.small,
	contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
	content: @Composable RowScope.() -> Unit
) {
	Surface(
		elevation = 0.dp,
		shape = shape,
		color = Color.Transparent,
		contentColor = Color.Transparent,
		border = null,
		modifier = modifier
			.then(
				Modifier
					.clip(shape)
					.clickable(
						interactionSource = interactionSource,
						indication = indication,
						onClick = onClick
					)
			),
	) {
		CompositionLocalProvider(LocalContentAlpha provides 1f) {
			ProvideTextStyle(
				value = MaterialTheme.typography.button
			) {
				Row(
					Modifier
						.defaultMinSize(
							minWidth = ButtonDefaults.MinWidth,
							minHeight = ButtonDefaults.MinHeight
						)
						.padding(contentPadding),
					horizontalArrangement = Arrangement.Center,
					verticalAlignment = Alignment.CenterVertically,
					content = content
				)
			}
		}
	}
}





@Composable
fun GradientButton(
	brush: Brush,
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
	shape: Shape = RoundedCornerShape(4.dp),
	contentPadding: PaddingValues? = null,
	content: @Composable () -> Unit
) {
	Button(
		shape = shape,
		contentPadding = contentPadding ?: ButtonDefaults.ContentPadding,
		colors = ButtonDefaults.buttonColors(
			backgroundColor = Color.Transparent,
			contentColor = Color.Transparent
		),
		onClick = onClick,
		modifier = modifier
	) {
		Box(
			contentAlignment = Alignment.Center,
			modifier = Modifier
				.fillMaxSize()
				.background(brush)
		) {
			content()
		}
	}
}





@Composable
fun ExpandedText(
	text: String,
	expandedText: String,
	expandedTextButton: String,
	shrinkTextButton: String,
	modifier: Modifier = Modifier,
	softWrap: Boolean = true,
	textStyle: TextStyle = LocalTextStyle.current,
	expandedTextStyle: TextStyle = LocalTextStyle.current,
	expandedTextButtonStyle: TextStyle = LocalTextStyle.current,
	shrinkTextButtonStyle: TextStyle = LocalTextStyle.current,
) {
	
	var isExpanded by remember { mutableStateOf(false) }
	
	val textHandler = "${if (isExpanded) expandedText else text} ${if (isExpanded) shrinkTextButton else expandedTextButton}"
	
	val annotatedString = buildAnnotatedString {
		withStyle(
			if (isExpanded) expandedTextStyle.toSpanStyle() else textStyle.toSpanStyle()
		) {
			append(if (isExpanded) expandedText else text)
		}
		
		append("  ")
		
		withStyle(
			if (isExpanded) shrinkTextButtonStyle.toSpanStyle() else expandedTextButtonStyle.toSpanStyle()
		) {
			append(if (isExpanded) shrinkTextButton else expandedTextButton)
		}
		
		addStringAnnotation(
			tag = "expand_shrink_text_button",
			annotation = if (isExpanded) shrinkTextButton else expandedTextButton,
			start = textHandler.indexOf(if (isExpanded) shrinkTextButton else expandedTextButton),
			end = textHandler.indexOf(if (isExpanded) shrinkTextButton else expandedTextButton) + if (isExpanded) expandedTextButton.length else shrinkTextButton.length
		)
	}
	
	ClickableText(
		text = annotatedString,
		softWrap = softWrap,
		modifier = modifier,
		onClick = {
			annotatedString
				.getStringAnnotations(
					"expand_shrink_text_button",
					it,
					it
				)
				.firstOrNull()?.let { stringAnnotation ->
					isExpanded = stringAnnotation.item == expandedTextButton
				}
		}
	)
}

@OptIn(ExperimentalUnitApi::class)
//@Preview(showBackground = true)
@Composable
fun ExpandedTextPreview() {
	ExpandedText(
		text = "abc",
		expandedText = "abcdef",
		expandedTextButton = "more",
		shrinkTextButton = "less",
		expandedTextButtonStyle = typographySkModernist().body1.copy(
			color = orange,
			fontSize = TextUnit(14f, TextUnitType.Sp)
		),
		shrinkTextButtonStyle = typographySkModernist().body1.copy(
			color = orange,
			fontSize = TextUnit(14f, TextUnitType.Sp)
		)
	)
}





@OptIn(ExperimentalUnitApi::class)
@Composable
fun FoodCategoryMenu(
	foodCategory: FoodCategory,
	selected: Boolean = false,
	onClick: () -> Unit
) {
	Box(
		modifier = Modifier
			.padding(start = 8.dp, end = 8.dp)
			.size(100.dp, 140.dp)
			.drawBehind {
				drawRoundRect(
					color = foodCategory.color,
					alpha = if (selected) 1f else 0.2f,
					size = Size(100.dp.toPx(), 140.dp.toPx()),
					cornerRadius = CornerRadius(256f, 256f),
					style = Stroke(4f)
				)
			}
			.clip(RoundedCornerShape(50))
			.clickable { onClick() }
	) {
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center,
			modifier = Modifier
				.fillMaxSize()
				.padding(12.dp)
				.clip(RoundedCornerShape(50))
				.background(foodCategory.color.copy(alpha = if (selected) 0.2f else 0.015f))
		) {
			Image(
				painter = painterResource(id = foodCategory.icon),
				contentDescription = null,
				alpha = if (selected) 1f else 0.4f,
				modifier = Modifier
					.size(26.dp)
			)
			
			Text(
				text = foodCategory.name,
				color = black.copy(
					alpha = if (selected) 1f else 0.4f,
				),
				style = typographyDmSans().body1.copy(
					fontSize = TextUnit(14f, TextUnitType.Sp),
				),
				modifier = Modifier
					.padding(top = 18.dp, bottom = 16.dp)
			)
		}
	}
}

//@Preview(showBackground = true)
@Composable
fun FoodCategoryMenuPreview() {
	Row {
		FoodCategoryMenu(
			foodCategory = FoodCategory.sample_2,
			selected = true
		) {}
		
		FoodCategoryMenu(
			foodCategory = FoodCategory.sample_2,
			selected = false
		) {}
	}
}





@OptIn(
	ExperimentalMaterialApi::class,
	ExperimentalUnitApi::class,
)
@Composable
fun FoodMenu(
	food: Food,
	onClick: () -> Unit,
	onFavoriteClicked: (Boolean) -> Unit
) {
	Card(
		elevation = 0.5.dp,
		shape = RoundedCornerShape(18.dp),
		backgroundColor = white,
		onClick = onClick,
		modifier = Modifier
			.padding(start = 12.dp, end = 12.dp)
	) {
		Column(
			modifier = Modifier
				.width(224.dp)
				.padding(16.dp)
		) {
			Image(
				painter = painterResource(id = food.image),
				contentDescription = null,
				modifier = Modifier
					.fillMaxWidth()
					.height(112.dp)
			)
			
			Text(
				text = food.name,
				style = typographyDmSans().body1.copy(
					fontSize = TextUnit(16f, TextUnitType.Sp),
					fontWeight = FontWeight.SemiBold
				),
				modifier = Modifier
					.padding(top = 8.dp)
			)
			
			Text(
				text = food.description,
				maxLines = 2,
				overflow = TextOverflow.Ellipsis,
				style = typographyDmSans().body1.copy(
					fontSize = TextUnit(12f, TextUnitType.Sp),
					fontWeight = FontWeight.Normal
				),
				modifier = Modifier
					.padding(top = 8.dp)
			)
			
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 12.dp)
			) {
				Row(
					verticalAlignment = Alignment.CenterVertically,
					modifier = Modifier
						.align(Alignment.CenterStart)
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
						style = typographyDmSans().body1.copy(
							fontSize = TextUnit(12f, TextUnitType.Sp),
						),
						modifier = Modifier
							.padding(start = 8.dp)
					)
				}
				
				IconButton(
					onClick = {
						onFavoriteClicked(!food.isFavorite)
					},
					modifier = Modifier
						.align(Alignment.CenterEnd)
				) {
					Image(
						painter = painterResource(
							id = if (food.isFavorite) R.drawable.ic_favorite_selected
							else R.drawable.ic_favorite
						),
						contentDescription = null,
						modifier = Modifier
							.size(18.dp)
					)
				}
			}
		}
	}
}

//@Preview
@Composable
fun FoodMenuPreview() {
	FoodMenu(
		food = Food.sample,
		onClick = {},
		onFavoriteClicked = {},
	)
}





@OptIn(
	ExperimentalMaterialApi::class,
	ExperimentalUnitApi::class
)
@Composable
fun ToppingMenu(
	topping: Topping,
	isSelected: Boolean,
	onClick: () -> Unit
) {
	Card(
		elevation = 1.dp,
		shape = RoundedCornerShape(40),
		backgroundColor = white,
		onClick = onClick,
		modifier = Modifier
			.padding(12.dp)
			.border(
				width = 1.dp,
				shape = RoundedCornerShape(40),
				color = if (isSelected) orange else Color.Transparent
			)
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.Center,
			modifier = Modifier
				.padding(12.dp)
		) {
			
			Box(
				modifier = Modifier
					.size(32.dp)
					.clip(RoundedCornerShape(100))
					.background(background)
			) {
				Image(
					painter = painterResource(id = topping.image),
					contentDescription = null,
					modifier = Modifier
						.size(24.dp)
						.align(Alignment.Center)
				)
			}
			
			Text(
				text = topping.name,
				style = typographyDmSans().body1.copy(
					color = black,
					fontSize = TextUnit(12f, TextUnitType.Sp)
				),
				modifier = Modifier
					.padding(start = 8.dp)
			)
			
		}
	}
}

//@Preview
@Composable
fun ToppingMenuPreview() {
	Column {
		ToppingMenu(
			topping = Topping.values[0],
			isSelected = true,
			onClick = {}
		)
		
		ToppingMenu(
			topping = Topping.values[0],
			isSelected = false,
			onClick = {}
		)
	}
}





@OptIn(
	ExperimentalMaterialApi::class,
	ExperimentalUnitApi::class
)
@Composable
fun FoodCartItem(
	food: Food,
	onPriceChange: (Double) -> Unit
) {
	
	var count by remember { mutableStateOf(1) }
	
	var currentPrice by remember { mutableStateOf(food.price) }
	
	Card(
		elevation = 0.4f.dp,
		shape = RoundedCornerShape(30),
		backgroundColor = white,
		onClick = {},
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp)
	) {
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 16.dp)
		) {
			
			Card(
				elevation = 0.2f.dp,
				shape = RoundedCornerShape(30),
				modifier = Modifier
					.size(64.dp)
					.padding(8.dp)
					.align(Alignment.CenterStart)
			) {
				Box(
					contentAlignment = Alignment.Center,
					modifier = Modifier
						.fillMaxSize()
				) {
					Image(
						painter = painterResource(id = food.image),
						contentDescription = null,
						modifier = Modifier
							.size(32.dp)
					)
				}
			}
			
			
			
			Column(
				modifier = Modifier
					.align(Alignment.Center)
			) {
				Text(
					text = food.name,
					maxLines = 1,
					overflow = TextOverflow.Ellipsis,
					style = typographyDmSans().body1.copy(
						color = black.copy(alpha = 0.8f),
						fontWeight = FontWeight.SemiBold,
						fontSize = TextUnit(16f, TextUnitType.Sp)
					)
				)
				
				Text(
					text = food.type,
					overflow = TextOverflow.Ellipsis,
					style = typographyDmSans().body1.copy(
						color = black.copy(alpha = 0.8f),
						fontWeight = FontWeight.Normal,
						fontSize = TextUnit(12f, TextUnitType.Sp)
					),
					modifier = Modifier
						.padding(top = 2.dp, bottom = 2.dp)
				)
				
				Text(
					text = run {
						if (currentPrice.toString().length > 6) {
							"$${currentPrice.toString().substring(0, 6)}"
						} else "$$currentPrice"
					},
					overflow = TextOverflow.Ellipsis,
					style = typographyDmSans().body1.copy(
						color = orange.copy(alpha = 0.8f),
						fontWeight = FontWeight.SemiBold,
						fontSize = TextUnit(16f, TextUnitType.Sp)
					)
				)
			}
			
			
			
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.align(Alignment.CenterEnd)
			) {
				
				FloatingActionButton(
					shape = RoundedCornerShape(100),
					backgroundColor = orange.copy(alpha = 0.8f),
					elevation = FloatingActionButtonDefaults.elevation(
						defaultElevation = 0.dp
					),
					onClick = {
						if (count > 1) {
							count -= 1
							currentPrice = food.price * count
							onPriceChange(currentPrice)
						}
					},
					modifier = Modifier
						.size(24.dp)
				) {
					Icon(
						painter = painterResource(id = R.drawable.ic_horizontal_rule_rounded),
						contentDescription = null,
						tint = white,
						modifier = Modifier
							.size(16.dp)
					)
				}
				
				Text(
					text = count.toString(),
					style = typographyDmSans().body1.copy(
						color = black.copy(alpha = 0.8f),
						fontWeight = FontWeight.SemiBold,
						fontSize = TextUnit(16f, TextUnitType.Sp)
					),
					modifier = Modifier
						.padding(start = 8.dp, end = 8.dp)
				)
				
				FloatingActionButton(
					shape = RoundedCornerShape(100),
					backgroundColor = orange.copy(alpha = 0.8f),
					elevation = FloatingActionButtonDefaults.elevation(
						defaultElevation = 0.dp
					),
					onClick = {
						count += 1
						currentPrice = food.price * count
						onPriceChange(currentPrice)
					},
					modifier = Modifier
						.size(24.dp)
				) {
					Icon(
						imageVector = Icons.Rounded.Add,
						contentDescription = null,
						tint = white,
						modifier = Modifier
							.size(16.dp)
					)
				}
			}
			
			
			
		}
	}
}

@Preview
@Composable
fun FoodCartItemPreview() {
	FoodCartItem(
		food = Food.sample,
		onPriceChange = {}
	)
}

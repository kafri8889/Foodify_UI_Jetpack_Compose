package com.anafthdev.foodify.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

object OTPField {
	
	sealed class OTPLength(val length: Int)
	
	object OTPLength_4: OTPLength(4)
	object OTPLength_5: OTPLength(5)
	object OTPLength_6: OTPLength(6)
	
}

@OptIn(ExperimentalComposeUiApi::class)
/**
 * @param onValueChange return (int, string) -> int = index, string = value
 *
 * @author Kafri8889
 */
@Composable
fun OTPField(
	modifier: Modifier = Modifier,
	textStyle: TextStyle = MaterialTheme.typography.body1,
	length: OTPField.OTPLength = OTPField.OTPLength_4,
	onValueChange: (Int, String) -> Unit
) {
	
	val focusManager = LocalFocusManager.current
	val keyboardController = LocalSoftwareKeyboardController.current
	
	val focusRequesters = remember { mutableStateListOf<FocusRequester>().apply {
		for (i in 0 until length.length) { add(FocusRequester()) }
	} }
	
	val values = remember { mutableStateListOf<String>().apply {
		for (i in 0 until length.length) { add("") }
	} }
	
	Row(
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Center,
		modifier = Modifier
			.fillMaxWidth()
			.then(modifier)
	) {
		for (i in 0 until length.length) {
			Card(
				elevation = 2.dp,
				shape = MaterialTheme.shapes.small,
				modifier = Modifier
					.size(
						when (length) {
							OTPField.OTPLength_4 -> 64.dp
							OTPField.OTPLength_5 -> 60.dp
							OTPField.OTPLength_6 -> 56.dp
						}
					)
					.padding(8.dp)
			) {
				Row(
					horizontalArrangement = Arrangement.Center,
					verticalAlignment = Alignment.CenterVertically,
					modifier = Modifier
						.fillMaxSize()
				) {
					BasicTextField(
						value = values[i],
						keyboardOptions = KeyboardOptions(
							keyboardType = KeyboardType.Number,
							imeAction = ImeAction.Next
						),
						keyboardActions = KeyboardActions(
							onNext = {
								focusManager.moveFocus(FocusDirection.Down)
								if (i == (length.length - 1)) {
									focusManager.clearFocus(force = true)
									keyboardController?.hide()
								}
							},
						),
						onValueChange = { s ->
							values[i] = if (s.length > 1) values[i] else s
							onValueChange(i, values[i])
							if (values[i].isBlank()) focusManager.moveFocus(FocusDirection.Up)
							else focusManager.moveFocus(FocusDirection.Down)
						},
						textStyle = textStyle.copy(
							textAlign = TextAlign.Center
						),
						modifier = Modifier
							.fillMaxWidth()
							.align(Alignment.CenterVertically)
							.focusOrder(focusRequesters[i]) {
								down = if (i == (length.length - 1)) focusRequesters[i] else focusRequesters[i + 1]
								up = if (i == 0) focusRequesters[i] else focusRequesters[i - 1]
							}
					)
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun OTPFieldPreview() {
	OTPField(
		length = OTPField.OTPLength_4,
		onValueChange = { i, s -> }
	)
}

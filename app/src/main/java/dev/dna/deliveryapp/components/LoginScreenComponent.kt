package dev.dna.deliveryapp.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.dna.deliveryapp.R
import dev.dna.deliveryapp.navigation.Screens
import dev.dna.deliveryapp.utils.AppColors
import dev.dna.deliveryapp.utils.AppUtils

private const val TAG = "LoginScreenComponents"

@Composable
fun LoginOrSignUpWithPhoneNumber(
    navController: NavController,
    spacerHeight: Dp = 5.dp,
    registerViewModel: RegisterAndLoginViewModel = viewModel(),
    onLogin: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val (_, getDisplayHeight, _) = AppUtils.screenHeightAndWidth(context)
    val phoneNumber = remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    // taking phone number
    InputPhoneNumber(
        phoneNumber = phoneNumber,
        height = getDisplayHeight.dp.times(0.085f),
        onFocusChanged = {
            if (phoneNumber.value.trim().isNotEmpty()) {
                registerViewModel.isPhoneNumberValid(phoneNumber.value.trim(), context)
            }
        },
        onAction = KeyboardActions {
            if (registerViewModel.isPhoneNumberValid(phoneNumber.value, context)) {
                onLogin(phoneNumber.value.trim())
                keyboardController?.hide()
            }
        })

    Spacer(modifier = Modifier.height(spacerHeight))

    SignInAndSignOptions(
        icon = null,
        title = stringResource(id = R.string.login_lbl),
        bgColor = AppColors.green,
        navController = navController,
        onClicked = {
            if (registerViewModel.isPhoneNumberValid(phoneNumber.value, context)) {
                navController.navigate(Screens.HomeScreen.route)
            }
        }
    )

    AlreadyUserOrNewUser(navController, isNewUser = true)
}

@Composable
fun AlreadyUserOrNewUser(navController: NavController, isNewUser: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = if (isNewUser) stringResource(id = R.string.not_a_member_lbl) else stringResource(
                id = R.string.already_have_an_account
            ),
            fontFamily = FontFamily(Font(R.font.poppins_regular))
        )

        TextButton(onClick = {
            Log.d(TAG, "AlreadyUserOrNewUser: called to navigate to either login or register")
            if (isNewUser) navController.navigate(Screens.RegisterScreen.route) else navController.navigate(
                Screens.LoginScreen.route
            )
        }) {
            Text(
                text = if (isNewUser) stringResource(id = R.string.register_now_lbl) else stringResource(
                    id = R.string.login_lbl
                ),
                color = AppColors.green,
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
        }
    }
}

@Composable
fun AllLoginOptions(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // login with google
        SignInAndSignOptions(
            icon = painterResource(id = R.drawable.google_login), title = stringResource(
                id = R.string.google_login
            ), bgColor = AppColors.google, navController = navController,
            onClicked = {
                //TODO More details later
                navController.navigate(Screens.OTPCodeScreen.route)
            }
        )

        // login with google
        SignInAndSignOptions(
            icon = painterResource(id = R.drawable.facebook_login), title = stringResource(
                id = R.string.facebook_login
            ), bgColor = AppColors.facebook, navController = navController,
            onClicked = {
                //TODO More details later
                navController.navigate(Screens.OTPCodeScreen.route)
            }
        )

        // login with google
        SignInAndSignOptions(
            icon = painterResource(id = R.drawable.apple_login), title = stringResource(
                id = R.string.apple_login
            ), bgColor = AppColors.apple, navController = navController,
            onClicked = {
                //TODO More details later
                navController.navigate(Screens.OTPCodeScreen.route)
            }
        )
    }
}


@Composable
fun SignInAndSignOptions(
    icon: Painter?,
    title: String,
    bgColor: Color,
    navController: NavController,
    maxWidthSize: Float = 1f,
    height: Dp = 56.dp,
    onClicked: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(maxWidthSize)
            .height(height)
            .padding(bottom = 8.dp)
            .background(color = bgColor, shape = CircleShape)
            .clickable { onClicked.invoke() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (icon != null) Arrangement.Start else Arrangement.Center
    ) {
        if (icon != null) {
            Spacer(modifier = Modifier.width(62.dp))
            Button(
                onClick = { onClicked.invoke() },
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    disabledElevation = 0.dp,
                    focusedElevation = 0.dp
                ),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = bgColor,
                    disabledContainerColor = Color.White
                ),
                contentPadding = PaddingValues(4.dp)
            ) {
                Image(painter = icon, contentDescription = title)
            }
            Spacer(modifier = Modifier.width(4.dp))
        }

        Text(
            text = title,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            color = AppColors.grayish
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputPhoneNumber(
    phoneNumber: MutableState<String>,
    height: Dp = 52.dp,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Done,
    onFocusChanged: () -> Unit = {},
    onAction: KeyboardActions = KeyboardActions.Default
) {
    var animatedVisibility by remember { mutableStateOf(false) }
    val (_, _, screenArea) = AppUtils.screenHeightAndWidth(LocalContext.current)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        shape = CircleShape,
        border = BorderStroke(2.dp, color = AppColors.green),

        ) {
        TextField(
            value = phoneNumber.value,
            onValueChange = { phoneNumber.value = it; animatedVisibility = it.trim().isNotEmpty() },
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 8.dp)
                .onFocusChanged {
                    if (!it.isFocused) {
                        onFocusChanged()
                    }
                },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.enter_phone_number),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = AppColors.green,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                )
            },
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = AppColors.green,
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            ),
            trailingIcon = {
                AnimatedVisibility(animatedVisibility, enter = fadeIn(), exit = fadeOut()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "Clear text",
                        modifier = Modifier
                            .size(screenArea.dp.times(0.00008f))
                            .background(color = AppColors.lightLightGreen, shape = CircleShape)
                            .clickable {
                                phoneNumber.value = ""
                                animatedVisibility = false
                            },
                        tint = Color.White
                    )
                }
            },
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction,
            ),
            keyboardActions = onAction,
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedContainerColor = Color.White,
                focusedTextColor = AppColors.green,
            )
        )
    }
}
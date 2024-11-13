package dev.dna.deliveryapp.screens.phone_number

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.dna.deliveryapp.R
import dev.dna.deliveryapp.components.SignInAndSignOptions
import dev.dna.deliveryapp.navigation.Screens
import dev.dna.deliveryapp.screens.food.OgaTopAppBar
import dev.dna.deliveryapp.utils.AppColors
import dev.dna.deliveryapp.utils.AppUtils

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PhoneNumberScreen(navController: NavController) {
    Scaffold(
        topBar = {
            OgaTopAppBar(
                title = stringResource(id = R.string.phone_number),
                bgColor = Color.White,
                iconBgColor = AppColors.green,
                isCart = null,
                icon = painterResource(id = R.drawable.ic_close),
                navController = navController,
                iconClicked = { navController.popBackStack() },
                onCartOrHelpIcon = {}
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), color = Color.White
        ) {
            val phoneNumber = remember { mutableStateOf("") }
            val valid = phoneNumber.value.trim().isNotEmpty()
            var animatedVisibility by remember { mutableStateOf(false) }
            val (_, getDisplayHeight, screenArea) = AppUtils.screenHeightAndWidth(LocalContext.current)
            val keyboardController = LocalSoftwareKeyboardController.current

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(id = R.string.phone_number), style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Left,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color.Black.copy(0.87f)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = phoneNumber.value,
                    onValueChange = {
                        phoneNumber.value = it
                        animatedVisibility = it.trim().isNotEmpty()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color.Black.copy(0.67f)
                    ),
                    placeholder = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.example_number_lbl),
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    color = Color.Black.copy(0.67f)
                                )
                            )
                        }
                    },
                    trailingIcon = {
                        AnimatedVisibility(animatedVisibility, enter = fadeIn(), exit = fadeOut()) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_close),
                                contentDescription = "Clear text",
                                modifier = Modifier
                                    .size(screenArea.dp.times(0.00008f))
                                    .background(
                                        color = AppColors.lightLightGreen,
                                        shape = CircleShape
                                    )
                                    .clickable {
                                        phoneNumber.value = ""
                                        animatedVisibility = false
                                    },
                                tint = Color.White
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions {
                        if (!valid) return@KeyboardActions

                        keyboardController?.hide()
                    },
                    singleLine = true,
                    maxLines = 1,
                    colors = textFieldColors(
                        focusedTextColor = Color.Black.copy(0.67f),
                        disabledIndicatorColor = AppColors.green.copy(0.89f),
                        focusedContainerColor = Color.White,
                        cursorColor = Color.Black.copy(0.57f),
                        focusedIndicatorColor = AppColors.green.copy(0.89f),
                        unfocusedIndicatorColor = AppColors.green.copy(0.89f),
                        focusedPlaceholderColor = Color.Black.copy(0.67f)
                    )
                )


                Text(
                    text = stringResource(id = R.string.adding_phone_number_message),
                    style = TextStyle(
                        fontSize = 11.sp,
//                    fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    )
                )


                Spacer(modifier = Modifier.height(20.dp))
                // confirmation button
                SignInAndSignOptions(
                    icon = null,
                    title = stringResource(id = R.string.confirm_delivery_lbl),
                    bgColor = AppColors.green,
                    maxWidthSize = 0.96f,
                    height = getDisplayHeight.dp.times(0.065f),
                    navController = navController,
                    onClicked = { navController.navigate(Screens.OTPCodeScreen.route) }
                )
            }
        }
    }
}
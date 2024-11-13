package dev.dna.deliveryapp.screens.anything

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.dna.deliveryapp.R
import dev.dna.deliveryapp.screens.food.OgaTopAppBar
import dev.dna.deliveryapp.utils.AppColors
import dev.dna.deliveryapp.utils.AppUtils

private const val TAG = "AnythingScreen"

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AnythingScreen(navController: NavController) {
    val (_, getDisplayHeight, screenArea) = AppUtils.screenHeightAndWidth(LocalContext.current)

    Scaffold(
        containerColor = AppColors.green,
        topBar = {
            OgaTopAppBar(
                title = null,
                bgColor = AppColors.green,
                iconBgColor = AppColors.lightLightGreen,
                icon = painterResource(id = R.drawable.ic_close),
                isCart = false,
                navController = navController,
                iconClicked = { navController.popBackStack() },
                onCartOrHelpIcon = {/*TODO*/ }
            )
        }
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current

        Surface(modifier = Modifier.fillMaxSize(), color = AppColors.green) {
            val messageEntered = remember { mutableStateOf("") }
            val valid = messageEntered.value.trim().isNotEmpty()



            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.anything_lbl), style = TextStyle(
                            color = Color.White,
                            fontSize = 40.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                            textAlign = TextAlign.Center,
                            shadow = Shadow(
                                color = AppColors.darkGreen,
                                blurRadius = 16.0f,
                                offset = Offset(15.0f, 4.0f)
                            )
                        )
                    )

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(getDisplayHeight.dp.times(0.641f)),
                        shape = RoundedCornerShape(8),
                        color = Color.White
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(getDisplayHeight.dp.times(0.119f)),
                                color = AppColors.lightLightGreen,
                                shape = RoundedCornerShape(topStartPercent = 8, topEndPercent = 8)
                            ) {
                                Row(
                                    modifier = Modifier.padding(
                                        start = 16.dp,
                                        top = 12.dp,
                                        end = 16.dp,
                                    ),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Text(
                                        text = buildAnnotatedString {
                                            withStyle(
                                                style = SpanStyle(
                                                    fontSize = 15.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                                    background = AppColors.lightLightGreen,
                                                    color = Color.White
                                                )
                                            ) {
                                                append(stringResource(id = R.string.anything_screen_what_do_you_want))
                                            }
                                            withStyle(
                                                style = SpanStyle(
                                                    fontSize = 14.sp,
                                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                                    background = AppColors.lightLightGreen,
                                                    color = Color.White
                                                )
                                            ) {
                                                append(stringResource(id = R.string.anything_screen_what_do_you_want_sub))
                                            }
                                        }
                                    )
                                }
                            }

                            TextField(
                                value = messageEntered.value,
                                onValueChange = {
                                    messageEntered.value = it
                                },
                                modifier = Modifier
                                    .fillMaxHeight(5 / 6f)
                                    .fillMaxWidth(),
                                placeholder = {
                                    Text(
                                        text = stringResource(id = R.string.enter_what_you_want),
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                            color = Color.Black.copy(0.56f),
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                },
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.Sentences,
                                    autoCorrectEnabled = true,
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions {
                                    if (!valid) return@KeyboardActions

                                    Log.d(TAG, "AnythingScreen: okay")
                                    keyboardController?.hide()
                                },
                                shape = RectangleShape,
                                colors = ExposedDropdownMenuDefaults.textFieldColors(
                                    focusedTextColor = AppColors.green,
                                    focusedContainerColor = Color.White,
                                    focusedIndicatorColor = Color.White,
                                    unfocusedIndicatorColor = Color.White
                                )
                            )

                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(),
                                color = AppColors.lightLightGreen,
                                shape = RoundedCornerShape(
                                    bottomStartPercent = 8,
                                    bottomEndPercent = 8
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(
                                            start = 16.dp,
                                            bottom = 16.dp,
                                            end = 16.dp,
                                            top = 4.dp
                                        )
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.upload_image_lbl),
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                                background = AppColors.lightLightGreen,
                                                color = Color.White
                                            )
                                        )

                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_image_icon),
                                            contentDescription = "image icon",
                                            modifier = Modifier
                                                .background(
                                                    color = AppColors.lightLightGreen,
                                                    shape = CircleShape
                                                )
                                                .size(screenArea.dp.times(0.00015f))
                                                .clickable { },
                                            tint = Color.White
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                val context = LocalContext.current

                val dpScale = getDisplayHeight.dp.times(6.dp / getDisplayHeight.dp)
                Spacer(modifier = Modifier.height(dpScale))
                // proceed to check out button
                CircularButton(
                    title = stringResource(id = R.string.next_lbl),
                    bgColor = AppColors.lightLightGreen,
                    fieldMaxWidth = 0.96f,
                    onClicked = {
                        Log.d(TAG, "AnythingScreen: Next clicked")
                        showToastMessage(context, "Request received")
                    }
                )
                Spacer(modifier = Modifier.height(getDisplayHeight.dp.times(0.045f)))
            }
        }
    }
}


// toast message
fun showToastMessage(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}
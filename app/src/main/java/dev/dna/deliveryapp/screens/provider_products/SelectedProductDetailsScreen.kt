package dev.dna.deliveryapp.screens.provider_products

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import dev.dna.deliveryapp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import dev.dna.deliveryapp.data.fake.fakeDataStore
import dev.dna.deliveryapp.navigation.Screens
import dev.dna.deliveryapp.screens.food.OgaTopAppBar
import dev.dna.deliveryapp.utils.AppColors
import dev.dna.deliveryapp.utils.AppUtils
import dev.dna.deliveryapp.utils.Constants

private const val TAG = "SelectedProductDetailsS"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SelectedProductDetailsScreen(navController: NavController) {
    val (getDisplayWidth, getDisplayHeight, _) = AppUtils.screenHeightAndWidth(LocalContext.current)
    val showPopup = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(getDisplayHeight.dp.times(0.35f)),
                color = Color.White
            ) {
                ShowImageOrProductImage(getDisplayHeight, scalingFactor = 0.35f)

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Log.d(TAG, "FoodOrProductItemScreen: custom top app bar")
                    OgaTopAppBar(
                        title = null,
                        bgColor = Color.Transparent,
                        iconBgColor = AppColors.green,
                        icon = painterResource(id = R.drawable.ic_close),
                        navController = navController,
                        iconClicked = { navController.popBackStack() },
                        onCartOrHelpIcon = { navController.navigate(Screens.CartScreen.route) }
                    )
                }
            }
        },
        containerColor = Color.White,
        floatingActionButton = {
            Button(
                onClick = {
//                    showToastMessage(context, "Item added to cart")
                    showPopup.value = true
                },
                shape = CircleShape,
                colors = buttonColors(
                    containerColor = AppColors.green,
                    disabledContainerColor = AppColors.green
                ),
                modifier = Modifier
                    .width(getDisplayWidth.dp.times(0.7f))
                    .height(getDisplayHeight.dp.times(0.051f))
            ) {
                Text(
                    text = "Add for ${Constants.CEDI} 2.00", style = TextStyle(
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val item = fakeDataStore[0]
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        )
                    ) {
                        append(item.itemName + "\n")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        )
                    ) {
                        append("${Constants.CEDI} 1.00\n\n")
                    }

                    withStyle(
                        style = SpanStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        )
                    ) {
                        append(item.itemDescription)
                    }
                },
                textAlign = TextAlign.Center
            )

            // popup shows when item added to cart
            GotoCartPopup(showPopup = showPopup, navController = navController)
        }
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun GotoCartPopup(showPopup: MutableState<Boolean>, navController: NavController) {
    val (getDisplayWidth, getDisplayHeight, _) = AppUtils.screenHeightAndWidth(LocalContext.current)
    val scope = rememberCoroutineScope()

    if (showPopup.value) {
        Popup(
            alignment = Alignment.TopCenter,
            onDismissRequest = { showPopup.value = false },
            properties = PopupProperties(
                focusable = true,
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                excludeFromSystemGesture = true
            )
        ) {
            AnimatedVisibility(
                showPopup.value,
                enter = slideIn(tween(500, easing = LinearOutSlowInEasing)) { fullSize ->
                    IntOffset(fullSize.width / 4, 100)
                },
                exit = slideOut(tween(200, easing = FastOutSlowInEasing)) {
                    IntOffset(-180, 50)
                }
            ) {
                Surface(
                    modifier = Modifier
                        .width(getDisplayWidth.dp.times(0.9f))
                        .height(getDisplayHeight.dp.times(0.098f))
                        .padding(6.dp),
                    shadowElevation = 6.dp,
                    shape = RoundedCornerShape(20),
                    color = Color.White
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Top
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.gob3_pro),
                            contentDescription = "Gob3",
                            modifier = Modifier
                                .fillMaxWidth(0.14f)
                                .height(getDisplayHeight.dp.times(0.098f)),
                            contentScale = ContentScale.FillBounds
                        )
                        Spacer(modifier = Modifier.size(ButtonDefaults.IconSize))
                        Text(
                            text = "Gob3 pro max -Large",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                textAlign = TextAlign.Start,
                                shadow = Shadow(Color.Gray.copy(0.87f), Offset(-10f, -2f), -10f)
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier.padding(end = 12.dp)
                    ) {
                        Text(
                            text = "View Cart",
                            style = TextStyle(
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                textAlign = TextAlign.Start,
                                color = AppColors.green
                            ),
                            modifier = Modifier.clickable {
                                showPopup.value = false
                                navController.also {
                                    it.popBackStack()
                                    it.navigate(Screens.CartScreen.route)
                                }
                            }
                        )
                    }
                }
            }

            scope.launch {
                delay(2000).run {
                    showPopup.value = false
                }
            }
        }
    }
}
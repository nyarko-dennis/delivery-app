package dev.dna.deliveryapp.screens.checkout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import dev.dna.deliveryapp.R
import kotlinx.coroutines.launch
import dev.dna.deliveryapp.data.fake.fake_data_cartItems
import dev.dna.deliveryapp.screens.anything.showToastMessage
import dev.dna.deliveryapp.screens.cart.CartItemCard
import dev.dna.deliveryapp.utils.AppColors
import dev.dna.deliveryapp.utils.AppUtils
import dev.dna.deliveryapp.screens.food.OgaTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckOutAndNumberScreen(navController: NavController) {
    val (_, getDisplayHeight, _) = AppUtils.screenHeightAndWidth(
        LocalContext.current
    )
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        topBar = {
            OgaTopAppBar(
                title = stringResource(id = R.string.checkout_lbl),
                bgColor = Color.White,
                iconBgColor = AppColors.green,
                isCart = null,
                icon = painterResource(id = R.drawable.ic_close),
                navController = navController,
                iconClicked = { navController.popBackStack() },
                onCartOrHelpIcon = {}
            )
        },
        sheetContent = {
            PaymentOptionBottomSheet()
        },
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(topStartPercent = 8, topEndPercent = 8),
        sheetContainerColor = Color.White,
        sheetSwipeEnabled = true,
        scaffoldState = scaffoldState
    ) {
        val verticalScroll = rememberScrollState()

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(verticalScroll, true), color = Color.White
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                YourShoppingItems(navController)

                Spacer(modifier = Modifier.height(getDisplayHeight.dp.times(0.029f)))


                DeliveryLocationMap()

                Spacer(modifier = Modifier.height(getDisplayHeight.dp.times(0.029f)))
                PickUpAndDeliveryLocation(
                    navController = navController,
                    showDropOff = true,
                    isWhereto = true
                )

                Spacer(modifier = Modifier.height(getDisplayHeight.dp.times(0.029f)))
                PaymentDetails(
                    navController = navController,
                    isCheckout = true,
                    showPaymentOption = {
                        scope.launch {
                            if (scaffoldState.bottomSheetState.isCollapsed) {
                                scaffoldState.bottomSheetState.expand()
                            } else {
                                scaffoldState.bottomSheetState.collapse()
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun YourShoppingItems(navController: NavController) {
    val showDialog = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    OgaDialogBox(
        showDialog = showDialog,
        navController = navController,
        searchLabel = "Leave a message for us",
        actionLabel = "Okay",
        toastMsg = "Request received"
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    )
                ) {
                    append(stringResource(id = R.string.your_order_lbl))
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    )
                ) {
                    append(stringResource(id = R.string.shopping_item))
                }
            },
            modifier = Modifier.padding(start = 8.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        CartItemCard(
            item = fake_data_cartItems[0],
            onAddMore = { showToastMessage(context, "1 item added to cart") },
            onRemove = { showToastMessage(context, "1 item removed from cart") }
        )


        Spacer(modifier = Modifier.height(4.dp))
        CustomButton(
            leftIcon = painterResource(id = R.drawable.ic_baseline_edit_24),
            leftIconColor = Color.White,
            title = stringResource(id = R.string.any_request_for_the_store_lbl),
            btnBgColor = AppColors.green,
            rightIcon = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
            rightIconBgColor = Color.White,
            onRightIconClicked = { showDialog.value = true }
        )
    }
}


@Composable
fun OgaDialogBox(
    showDialog: MutableState<Boolean>,
    navController: NavController,
    searchLabel: String,
    actionLabel: String,
    placeholderIcon: Int = R.drawable.ic_baseline_edit_24,
    toastMsg: String
) {
    val context = LocalContext.current
    val (getDisplayWidth, getDisplayHeight, _) = AppUtils.screenHeightAndWidth(LocalContext.current)
    val animatedVisibility by remember { showDialog }

    if (showDialog.value) {
        Dialog(
            onDismissRequest = { showDialog.value = false }, properties = DialogProperties(
                dismissOnClickOutside = true,
                dismissOnBackPress = true
            )
        ) {
            AnimatedVisibility(
                animatedVisibility,
                enter = slideIn(tween(500, easing = LinearOutSlowInEasing)) { fullSize ->
                    IntOffset(fullSize.width / 4, 100)
                },
                exit = slideOut(tween(200, easing = FastOutSlowInEasing)) {
                    IntOffset(-180, 50)
                }
            ) {
                Surface(
                    modifier = Modifier
                        .width(getDisplayWidth.dp.times(0.95f))
                        .height(getDisplayHeight.dp.times(0.29f))
                        .padding(4.dp),
                    color = Color.White,
                    shape = RoundedCornerShape(8),
                    shadowElevation = 5.dp,
                    border = BorderStroke(1.dp, color = Color.LightGray)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = searchLabel,
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Box(modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 2.dp)) {
                            SearchBox(
                                label = searchLabel,
                                placeholderIcon = placeholderIcon,
                                onSearch = {}
                            )
                        }

                        Box(
                            modifier = Modifier.padding(
                                start = 60.dp,
                                end = 60.dp,
                                top = 24.dp,
                                bottom = 8.dp
                            )
                        ) {
                            SignInAndSignOptions(
                                icon = null,
                                title = actionLabel,
                                bgColor = AppColors.green,
                                navController = navController,
                                onClicked = {
                                    showDialog.value = false
                                    showToastMessage(context, toastMsg)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
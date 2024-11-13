package dev.dna.deliveryapp.screens.package_delivery_screen

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import dev.dna.deliveryapp.R
import dev.dna.deliveryapp.components.SignInAndSignOptions
import dev.dna.deliveryapp.navigation.Screens
import kotlinx.coroutines.launch
import dev.dna.deliveryapp.screens.checkout.OgaDialogBox
import dev.dna.deliveryapp.screens.checkout.PaymentOptionBottomSheet
import dev.dna.deliveryapp.screens.food.OgaTopAppBar
import dev.dna.deliveryapp.utils.AppColors
import dev.dna.deliveryapp.utils.AppUtils
import dev.dna.deliveryapp.utils.Constants
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PackageDeliveryScreen(navController: NavController) {
    val (_, getDisplayHeight, _) = AppUtils.screenHeightAndWidth(
        LocalContext.current
    )
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        topBar = {
            OgaTopAppBar(
                title = stringResource(id = R.string.package_delivery_lbl),
                bgColor = Color.White,
                iconBgColor = AppColors.green,
                icon = painterResource(id = R.drawable.ic_close),
                navController = navController,
                isCart = null,
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
                PackageDetails(navController = navController)

                Spacer(modifier = Modifier.height(getDisplayHeight.dp.times(0.029f)))


                DeliveryLocationMap()

                Spacer(modifier = Modifier.height(getDisplayHeight.dp.times(0.029f)))
                PickUpAndDeliveryLocation(navController = navController)

                Spacer(modifier = Modifier.height(getDisplayHeight.dp.times(0.029f)))
                PaymentDetails(
                    navController = navController,
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
fun PackageDetails(navController: NavController) {
    val showDialog = remember { mutableStateOf(false) }

    OgaDialogBox(
        showDialog = showDialog,
        navController = navController,
        searchLabel = "Package name",
        actionLabel = "Add",
        placeholderIcon = R.drawable.ic_package_3,
        toastMsg = "Package confirmed"
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
                    append(stringResource(id = R.string.courier_lbl))
                }
            },
            modifier = Modifier.padding(start = 8.dp)
        )

        CustomButton(
            leftIcon = painterResource(id = R.drawable.ic_package_3),
            leftIconColor = Color.White,
            title = stringResource(id = R.string.package_name_lbl),
            btnBgColor = AppColors.green,
            rightIcon = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
            rightIconBgColor = Color.White,
            onRightIconClicked = { showDialog.value = true }
        )
        Spacer(modifier = Modifier.height(4.dp))
        CustomButton(
            leftIcon = painterResource(id = R.drawable.ic_package_3),
            leftIconColor = Color.White,
            title = stringResource(id = R.string.package_name_lbl),
            btnBgColor = AppColors.green,
            rightIcon = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
            rightIconBgColor = Color.White,
            onRightIconClicked = { showDialog.value = true }
        )
        Spacer(modifier = Modifier.height(4.dp))
        CustomButton(
            leftIcon = painterResource(id = R.drawable.ic_package_3),
            leftIconColor = Color.White,
            title = stringResource(id = R.string.add_package_lbl),
            btnBgColor = AppColors.green,
            rightIcon = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
            rightIconBgColor = Color.White,
            onRightIconClicked = { showDialog.value = true }
        )

    }
}

@Composable
fun CustomButton(
    leftIcon: Painter,
    leftIconColor: Color,
    title: String,
    secondText: String? = null,
    btnBgColor: Color,
    rightIcon: Painter,
    rightIconBgColor: Color,
    onRightIconClicked: () -> Unit
) {
    val (_, getDisplayHeight, screenArea) = AppUtils.screenHeightAndWidth(
        LocalContext.current
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(getDisplayHeight.dp.times(0.06f))
            .padding(1.dp)
            .clickable { onRightIconClicked() },
        shape = CircleShape,
        color = btnBgColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 0.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 12.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = leftIcon,
                    contentDescription = title,
                    tint = leftIconColor,
                    modifier = Modifier.size(screenArea.dp.times(0.000068f))
                )
                Spacer(modifier = Modifier.width(20.dp))
                if (secondText == null) {
                    Text(
                        text = title,
                        style = TextStyle(
                            fontSize = 14.sp,
                            textAlign = TextAlign.Left,
                            color = if (btnBgColor != Color.White) Color.White else Color.Black.copy(
                                0.87f
                            ),
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.poppins_regular))
                        )
                    )
                } else {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 14.sp,
                                    color = if (btnBgColor != Color.White) Color.White else Color.Black.copy(
                                        0.87f
                                    ),
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                                )
                            ) {
                                append(title + "\n")
                            }

                            withStyle(
                                style = SpanStyle(
                                    fontSize = 10.sp,
                                    color = if (btnBgColor != Color.White) Color.White else Color.Black.copy(
                                        0.87f
                                    ),
                                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                                )
                            ) {
                                append(secondText)
                            }
                        }
                    )
                }
            }

            Icon(
                painter = rightIcon,
                contentDescription = "go to $title icon",
                tint = rightIconBgColor,
                modifier = Modifier
                    .size(screenArea.dp.times(0.000068f))
                    .clickable { onRightIconClicked() }
            )
        }
    }
}


@Composable
fun DeliveryLocationMap() {
    val (_, getDisplayHeight, _) = AppUtils.screenHeightAndWidth(
        LocalContext.current
    )

//    val repuPos = LatLng(6.6779279, -1.5737944)
    val knustPos = LatLng(6.673175, -1.565423)
    val knust = MarkerState(position = knustPos)
//    val indecePos = LatLng(1.5720, 6.6775)
//    val indeceHall = MarkerState(position = indecePos)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(knustPos, 14.5f)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.delivery_details_lbl),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            ),
            modifier = Modifier.padding(start = 8.dp)
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(getDisplayHeight.dp.times(0.28f))
                .padding(start = 2.dp, end = 2.dp),
            color = Color.White,
            shape = RoundedCornerShape(12),
            shadowElevation = 3.dp
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                Marker(state = knust, title = "KNUST")
            }
        }
    }
}


@Composable
fun PickUpAndDeliveryLocation(
    navController: NavController,
    showDropOff: Boolean = false,
    isWhereto: Boolean = false
) {
    val showDialog = remember { mutableStateOf(false) }
    val showTimePicker = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val msg = if (isWhereto) "Enter delivery location" else "Pick up location"


    if (showTimePicker.value) {
        ShowTimePicker(context = context).also { showTimePicker.value = false }
    }

    OgaDialogBox(
        showDialog = showDialog,
        navController = navController,
        searchLabel = msg,
        actionLabel = "confirm",
        toastMsg = "Location saved",
        placeholderIcon = R.drawable.ic_pin_location
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        Alignment.Start
    ) {
        if (isWhereto) {
            // where to
            CustomButton(
                leftIcon = painterResource(id = R.drawable.ic_location_flag),
                leftIconColor = AppColors.green,
                title = stringResource(id = R.string.where_to_lbl),
                btnBgColor = Color.White,
                rightIcon = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
                rightIconBgColor = AppColors.green.copy(0.45f),
                onRightIconClicked = {
//                    showDialog.value = true
                    navController.navigate(Screens.LocationMapScreen.route + "/Where to?")
                }
            )
        } else {
            // pickup location
            CustomButton(
                leftIcon = painterResource(id = R.drawable.ic_pin_location),
                leftIconColor = AppColors.green,
                title = stringResource(id = R.string.pickup_location_lbl),
                btnBgColor = Color.White,
                rightIcon = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
                rightIconBgColor = AppColors.green.copy(0.45f),
                onRightIconClicked = {
//                    showDialog.value = true
                    navController.navigate(Screens.LocationMapScreen.route + "/Pickup location")
                }
            )
            Spacer(modifier = Modifier.height(4.dp))

            // delivery location
            CustomButton(
                leftIcon = painterResource(id = R.drawable.ic_location_flag),
                leftIconColor = AppColors.green,
                title = stringResource(id = R.string.delivery_location_lbl),
                btnBgColor = Color.White,
                rightIcon = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
                rightIconBgColor = AppColors.green.copy(0.45f),
                onRightIconClicked = {
//                    showDialog.value = true
                    navController.navigate(Screens.LocationMapScreen.route + "/Delivery location")
                }
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // delivery schedule time it takes
        CustomButton(
            leftIcon = painterResource(id = R.drawable.ic_clocky),
            leftIconColor = AppColors.green,
            title = stringResource(id = R.string.schedule_delivery_lbl),
            secondText = stringResource(id = R.string.delivery_time_lbl),
            btnBgColor = Color.White,
            rightIcon = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
            rightIconBgColor = AppColors.green.copy(0.45f),
            onRightIconClicked = {
                showTimePicker.value = true
            }
        )
        Spacer(modifier = Modifier.height(4.dp))

        // user phone number for contacting
        CustomButton(
            leftIcon = painterResource(id = R.drawable.ic_phone),
            leftIconColor = AppColors.green,
            title = stringResource(id = R.string.add_your_phone_number_lbl),
            secondText = stringResource(id = R.string.call_for_pickup_lbl),
            btnBgColor = Color.White,
            rightIcon = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
            rightIconBgColor = AppColors.green.copy(0.45f),
            onRightIconClicked = { navController.navigate(Screens.PhoneNumberScreen.route) }
        )

        if (showDropOff) {
            Spacer(modifier = Modifier.height(4.dp))

            // user phone number for contacting
            CustomButton(
                leftIcon = painterResource(id = R.drawable.ic_pin_location),
                leftIconColor = AppColors.green,
                title = stringResource(id = R.string.drop_off_lbl),
                secondText = null,
                btnBgColor = Color.White,
                rightIcon = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
                rightIconBgColor = AppColors.green.copy(0.45f),
                onRightIconClicked = { navController.navigate(Screens.DropOffOptionsScreen.route) }
            )
        }
    }
}


@Composable
fun ItemOrProductAndPriceRow(item: String, itemPrice: Float, isBold: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item, style = TextStyle(
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                textAlign = TextAlign.Left,
                color = Color.Black.copy(0.87f),
                fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
            )
        )
        Text(
            text = "${Constants.CEDI} %2.2f".format(itemPrice), style = TextStyle(
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                textAlign = TextAlign.Left,
                color = Color.Black.copy(0.87f),
                fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
            )
        )
    }
}


@Composable
fun PaymentDetails(
    navController: NavController,
    isCheckout: Boolean = false,
    showPaymentOption: () -> Unit
) {

    val (_, getDisplayHeight, _) = AppUtils.screenHeightAndWidth(LocalContext.current)
    // payment options popup
//    val showApplyPopup = rememberSaveable { mutableStateOf(false) }
//    PaymentOptionPopup(showApplyPopup)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.payment_method_lbl), style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                textAlign = TextAlign.Left,
                color = Color.Black.copy(0.87f)
            ),
            modifier = Modifier.padding(start = 12.dp, end = 12.dp)
        )
        CustomButton(
            leftIcon = painterResource(id = R.drawable.ic_payment_3),
            leftIconColor = AppColors.green,
            title = stringResource(id = R.string.select_payment_mthd_lbl),
            btnBgColor = Color.White,
            rightIcon = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
            rightIconBgColor = AppColors.green.copy(0.45f),
            onRightIconClicked = { showPaymentOption() }
        )

        Text(
            text = stringResource(id = R.string.summary_lbl), style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                textAlign = TextAlign.Left,
                color = Color.Black.copy(0.87f)
            ),
            modifier = Modifier.padding(start = 12.dp, end = 12.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 2.dp,
            color = AppColors.green.copy(0.45f)
        )
        Spacer(modifier = Modifier.height(4.dp))

        ItemOrProductAndPriceRow(item = "Products", itemPrice = 1.00f, isBold = false)
        ItemOrProductAndPriceRow(item = "Delivery", itemPrice = 5.00f, isBold = false)
        ItemOrProductAndPriceRow(item = "Total", itemPrice = 6.00f, isBold = true)

        Spacer(modifier = Modifier.height(6.dp))

        // confirm delivery
        Box(
            modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxWidth()
        ) {
            SignInAndSignOptions(
                icon = null,
                title = stringResource(id = R.string.confirm_order_lbl),
                bgColor = AppColors.green,
                maxWidthSize = 0.96f,
                height = getDisplayHeight.dp.times(0.065f),
                navController = navController,
                onClicked = {
                    if (isCheckout) {
                        // do nothing
                    } else {
                        navController.navigate(Screens.CheckOutAndNumberScreen.route)
                    }
                }
            )
        }
    }
}


@Composable
fun ShowTimePicker(context: Context) {

    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]

    val time = remember { mutableStateOf("") }
    val timePickerDialog = TimePickerDialog(
        context,
        { _, hour1: Int, minute1: Int ->
            time.value = "$hour1:$minute1"
        }, hour, minute, false
    )

    timePickerDialog.show()

}
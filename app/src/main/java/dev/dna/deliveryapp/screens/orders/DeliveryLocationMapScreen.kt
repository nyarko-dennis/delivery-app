package dev.dna.deliveryapp.screens.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
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
import dev.dna.deliveryapp.screens.food.OgaTopAppBar
import dev.dna.deliveryapp.utils.AppColors
import dev.dna.deliveryapp.utils.AppUtils


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryLocationMapScreen(navController: NavController) {
    val (_, getDisplayHeight, _) = AppUtils.screenHeightAndWidth(LocalContext.current)

    BottomSheetScaffold(
        topBar = {
            val indecePos = LatLng(6.6775, -1.5720)
            val indeceHall = MarkerState(position = indecePos)
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(indecePos, 14.5f)
            }

            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.977f * (2 / 3f)),
                cameraPositionState = cameraPositionState
            ) {
                Marker(state = indeceHall, title = "Indece")
            }
            OgaTopAppBar(
                title = null,
                bgColor = Color.Transparent,
                iconBgColor = AppColors.lightLightGreen,
                icon = painterResource(id = R.drawable.ic_close),
                navController = navController,
                iconClicked = { navController.popBackStack() },
                isCart = false,
                onCartOrHelpIcon = {}
            )
        },
        sheetSwipeEnabled = false,
        sheetContainerColor = Color.White,
        sheetShape = RoundedCornerShape(topStartPercent = 10, topEndPercent = 10),
        sheetPeekHeight = getDisplayHeight.dp.times(0.218f),
        sheetShadowElevation = 4.dp,
        sheetContent = {
            TrackDeliveryDetails()
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
            Column(
                modifier = Modifier.padding(top = 6.dp, start = 8.dp, end = 8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth(0.35f),
                    thickness = 2.5.dp,
                    color = Color.LightGray
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp, start = 8.dp, end = 8.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = stringResource(id = R.string.delivering_your_order_lbl),
                        style = MaterialTheme.typography.h5,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp
                    )

                    Text(
                        text = "Arrives between 9:00AM - 12:45AM",
                        style = MaterialTheme.typography.caption,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        modifier = Modifier.padding(start = 16.dp, bottom = 4.dp),
                        textAlign = TextAlign.Start,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}


@Composable
fun TrackDeliveryDetails() {
    val (_, _, screenArea) = AppUtils.screenHeightAndWidth(LocalContext.current)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val tmp = 50.dp
            Icon(
                painter = painterResource(id = R.drawable.ic_delivery),
                contentDescription = "delivery",
                tint = Color.White,
                modifier = Modifier
                    .background(color = AppColors.green, shape = CircleShape)
                    .size(screenArea.dp.times(0.000085f))
                    .padding(4.dp)
            )

            Divider(modifier = Modifier.width(tmp), color = AppColors.green, thickness = 2.dp)
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_shopping_bag_24),
                contentDescription = "shopping bag",
                tint = Color.White,
                modifier = Modifier
                    .background(color = AppColors.green, shape = CircleShape)
                    .size(screenArea.dp.times(0.000085f))
                    .padding(4.dp)
            )

            Divider(modifier = Modifier.width(tmp), color = AppColors.green, thickness = 2.dp)
            Icon(
                painter = painterResource(id = R.drawable.ic_delivery),
                contentDescription = "delivery",
                tint = Color.White,
                modifier = Modifier
                    .background(color = AppColors.green, shape = CircleShape)
                    .size(screenArea.dp.times(0.000085f))
                    .padding(4.dp)
            )

            Divider(modifier = Modifier.width(tmp), color = Color.LightGray, thickness = 2.dp)
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "home",
                tint = Color.Black,
                modifier = Modifier
                    .background(color = Color.LightGray, shape = CircleShape)
                    .size(screenArea.dp.times(0.000085f))
                    .padding(4.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Looking for\ndriver",
                style = MaterialTheme.typography.caption,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Getting Product",
                style = MaterialTheme.typography.caption,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Driver is on\ntheir way",
                style = MaterialTheme.typography.caption,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Package at\nyour door",
                style = MaterialTheme.typography.caption,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )

        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.width(140.dp),
                colors = buttonColors(containerColor = AppColors.green),
                shape = CircleShape
            ) {
                Text(
                    text = "Faster delivery",
                    style = MaterialTheme.typography.button,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color.White,
                    fontSize = 11.sp
                )
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.width(140.dp),
                colors = buttonColors(containerColor = Color.Red.copy(0.67f)),
                shape = CircleShape
            ) {
                Text(
                    text = "Cancel delivery",
                    style = MaterialTheme.typography.button,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color.White,
                    fontSize = 11.sp
                )
            }
        }
    }
}
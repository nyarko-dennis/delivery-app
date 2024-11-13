package dev.dna.deliveryapp.screens.orders

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.dna.deliveryapp.R
import dev.dna.deliveryapp.data.fake.OrderItemInfo
import dev.dna.deliveryapp.data.fake.orders_made
import dev.dna.deliveryapp.navigation.Screens
import dev.dna.deliveryapp.screens.food.OgaTopAppBar
import dev.dna.deliveryapp.utils.AppColors
import dev.dna.deliveryapp.utils.AppUtils
import dev.dna.deliveryapp.utils.Constants


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OrderScreen(navController: NavController) {
    Scaffold(
        topBar = {
            OgaTopAppBar(
                title = stringResource(id = R.string.orders_lbl),
                bgColor = Color.White,
                iconBgColor = AppColors.green,
                icon = painterResource(id = R.drawable.ic_close),
                navController = navController,
                iconClicked = {
                    navController.also {
                        it.popBackStack()
                        it.navigate(Screens.HomeScreen.route)
                    }
                },
                isCart = null,
                onCartOrHelpIcon = {}
            )
        }
    ) {
        val state = rememberLazyListState()
        LazyColumn(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(start = 4.dp, end = 4.dp),
            state = state
        ) {
            items(items = orders_made) { item ->
                OrderItemCard(orderItemInfo = item, navController = navController, onClick = {})
            }
        }
    }
}

@Composable
fun OrderItemCard(orderItemInfo: OrderItemInfo, navController: NavController, onClick: () -> Unit) {
    val (getDisplayWidth, getDisplayHeight, _) = AppUtils.screenHeightAndWidth(LocalContext.current)

    Surface(
        modifier = Modifier
            .width(getDisplayWidth.dp.times(0.96f))
            .height(getDisplayHeight.dp.times(0.15f))
            .padding(6.dp),
        color = AppColors.lightLightGreen,
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 6.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = orderItemInfo.item.itemName,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                EntryItem(icon = R.drawable.ic_baseline_shopping_bag_24, text = "3X")
                EntryItem(icon = R.drawable.cost, text = "${Constants.CEDI} 5.00")
                EntryItem(icon = R.drawable.payment___1, text = "Cash")
                EntryItem(icon = R.drawable.ic_date_24, text = "10:08 am")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Cancel Order",
                    style = MaterialTheme.typography.caption,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    modifier = Modifier.clickable { onClick() },
                    color = AppColors.green
                )

                Text(
                    text = "Track order",
                    style = MaterialTheme.typography.caption,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    modifier = Modifier.clickable { navController.navigate(Screens.DeliveryLocationMapScreen.route) },
                    color = AppColors.green
                )

                Text(
                    text = "Status : CONFIRMED",
                    style = MaterialTheme.typography.overline,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun EntryItem(icon: Int, text: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = text,
            modifier = Modifier.size(ButtonDefaults.IconSize),
            tint = Color.White
        )
        Text(
            text = text,
            style = MaterialTheme.typography.overline,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            color = Color.White
        )
    }
}
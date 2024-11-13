package dev.dna.deliveryapp.screens.provider_products

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.dna.deliveryapp.R
import dev.dna.deliveryapp.data.fake.fakeDataStore
import dev.dna.deliveryapp.navigation.Screens
import dev.dna.deliveryapp.screens.food.OgaTopAppBar
import dev.dna.deliveryapp.screens.food.SearchBox
import dev.dna.deliveryapp.screens.food.searchItem
import dev.dna.deliveryapp.utils.AppColors
import dev.dna.deliveryapp.utils.AppUtils
import dev.dna.deliveryapp.utils.Constants

private const val TAG = "FoodOrProductItemScreen"

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FoodOrProductItemScreen(navController: NavController) {
    Log.d(TAG, "FoodOrProductItemScreen: called")
    val (getDisplayWidth, getDisplayHeight, screenArea) = AppUtils.screenHeightAndWidth(LocalContext.current)
    val state = rememberLazyListState()

    Scaffold(
        topBar = {
            Log.d(TAG, "FoodOrProductItemScreen: inside topBar in scaffold")
            ShopAndProductTopAppBar(getDisplayHeight, navController, getDisplayWidth)
        },
        containerColor = Color.White,
        floatingActionButton = {
            Button(
                onClick = { navController.navigate(Screens.SelectedProductDetailsScreen.route) },
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
                    text = "Order 1 for ${Constants.CEDI} 2.00", style = TextStyle(
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
                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),
            verticalArrangement = Arrangement.Top,
            Alignment.Start
        ) {
            // search box
            SearchBox(label = stringResource(id = R.string.place_or_product_lbl), onSearch = {
                Log.d(TAG, "FoodOrProductItemScreen: search query is $it")
                searchItem(it)
            })

            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.trophy_b),
                    contentDescription = "top seller icon",
                    tint = Color(0xFFFFC107),
                    modifier = Modifier
                        .size(screenArea.dp.times(0.000089f))
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(id = R.string.top_sellers_lbl), style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Left
                    )
                )
            }


            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                state = state,
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(4.dp)
            ) {
                items(items = fakeDataStore) { item ->
                    ProductRow(
                        salesItem = item,
                        onClick = { navController.navigate(Screens.SelectedProductDetailsScreen.route) })
                }
            }
        }
    }
}


@Composable
fun ShopAndProductTopAppBar(
    getDisplayHeight: Float,
    navController: NavController,
    getDisplayWidth: Float
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(getDisplayHeight.dp.times(0.36f)),
        color = Color.White
    ) {
        ShowImageOrProductImage(getDisplayHeight)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
//                        .height(getDisplayHeight.dp.times(0.12f)),
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
            Spacer(modifier = Modifier.height(3.dp))
            ShopNameCard(getDisplayWidth, getDisplayHeight)
        }

    }
}


@Composable
fun ShowImageOrProductImage(getDisplayHeight: Float, scalingFactor: Float = 0.25f) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
//            .height( getDisplayHeight.dp.times(0.18f)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            color = Color.White
        ) {
            Image(
                painter = painterResource(id = R.drawable.gob3_pro),
                contentDescription = "gob3",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(getDisplayHeight.dp.times(scalingFactor))
            )
        }
    }
}

@Composable
fun ShopNameCard(getDisplayWidth: Float, getDisplayHeight: Float) {
    Surface(
        modifier = Modifier
            .width(getDisplayWidth.dp.times(0.9f))
            .fillMaxHeight(0.92f),
//                                .height(getDisplayHeight.dp.times(0.49f)),
        color = Color.White,
        shape = RoundedCornerShape(8),
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 3.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.shop_name_lbl),
                style = TextStyle(
                    fontSize = 28.sp, fontFamily = FontFamily(
                        Font(R.font.poppins_bold)
                    ),
                    textAlign = TextAlign.Center,
                    shadow = Shadow(
                        color = Color.Gray,
                        offset = Offset(2f, 8f),
                        blurRadius = 6f
                    )
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray, shape = CircleShape)
                    .height(getDisplayHeight.dp.times(0.05f)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // delivery icon
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.5f),
                    shape = CircleShape,
                    colors = buttonColors(
                        containerColor = AppColors.green,
                        disabledContainerColor = AppColors.green
                    ),
                    contentPadding = PaddingValues(2.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delivery),
                        contentDescription = "delivery",
                        tint = Color.White,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))

                    Text(
                        text = stringResource(id = R.string.delivery_lbl),
                        style = MaterialTheme.typography.button,
                        color = Color.White
                    )
                }

                // take away icon
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp,
                        disabledElevation = 0.dp,
                        focusedElevation = 0.dp
                    ),
                    shape = CircleShape,
                    colors = buttonColors(
                        containerColor = Color.LightGray,
                        disabledContainerColor = Color.LightGray
                    ),
                    contentPadding = PaddingValues(2.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.take_away_1),
                        contentDescription = "delivery",
                        tint = Color.Black,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))

                    Text(
                        text = stringResource(id = R.string.takeaway_lbl),
                        style = MaterialTheme.typography.button,
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShopDeliveryAndTime(
                    icon = R.drawable.ic_delivery,
                    text = "${Constants.CEDI} 5.00",
                    Color.Black
                )
                ShopDeliveryAndTime(
                    icon = R.drawable.ic_clocky,
                    text = stringResource(id = R.string.delivery_time_lbl),
                    Color.Black
                )
                ShopDeliveryAndTime(
                    icon = R.drawable.ic_star,
                    text = "5.0",
                    Color(0xFFFFC107)
                )
            }
        }
    }
}

@Composable
fun ShopDeliveryAndTime(icon: Int, text: String, iconColor: Color) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = text,
            tint = iconColor,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))

        Text(
            text = text,
            style = MaterialTheme.typography.button,
            color = Color.Black
        )
    }
}
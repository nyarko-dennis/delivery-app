package dev.dna.deliveryapp.screens.provider_products

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import dev.dna.deliveryapp.data.fake.SalesItem
import dev.dna.deliveryapp.data.fake.fakeDataStore
import dev.dna.deliveryapp.navigation.Screens
import dev.dna.deliveryapp.screens.anything.showToastMessage
import dev.dna.deliveryapp.screens.food.NavIconOrAddOrRemoveIcon
import dev.dna.deliveryapp.screens.food.OgaTopAppBar
import dev.dna.deliveryapp.screens.food.SearchBox
import dev.dna.deliveryapp.screens.food.searchItem
import dev.dna.deliveryapp.utils.AppColors
import dev.dna.deliveryapp.utils.AppUtils
import dev.dna.deliveryapp.utils.Constants

private const val TAG = "StoreOrShopScreen"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StoreOrShopScreen(navController: NavController) {
    val (_, _, screenArea) = AppUtils.screenHeightAndWidth(LocalContext.current)
    val state = rememberLazyListState()

    Scaffold(
        topBar = {
            OgaTopAppBar(
                title = stringResource(id = R.string.shop_name_lbl),
                bgColor = Color.White,
                iconBgColor = AppColors.green,
                icon = painterResource(id = R.drawable.ic_close),
                navController = navController,
                iconClicked = { navController.popBackStack() },
                onCartOrHelpIcon = { navController.navigate(Screens.CartScreen.route) }
            )
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // search box
                SearchBox(label = stringResource(id = R.string.place_or_product_lbl), onSearch = {
                    Log.d(TAG, "StoreOrShopScreen: search query is $it")
                    searchItem(it)
                })

                Spacer(modifier = Modifier.height(16.dp))

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
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(4.dp)
                ) {
                    items(items = fakeDataStore.subList(0, 3)) { item ->
                        ProductRow(
                            salesItem = item,
                            onClick = { navController.navigate(Screens.FoodOrProductItemScreen.route) })
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = stringResource(id = R.string.main_meal_lbl), style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Left
                        )
                    )
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        state = state,
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        contentPadding = PaddingValues(4.dp)
                    ) {
                        items(items = fakeDataStore.subList(4, fakeDataStore.size)) { item ->
                            ProductRow(
                                salesItem = item,
                                onClick = { navController.navigate(Screens.FoodOrProductItemScreen.route) })
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ProductRow(salesItem: SalesItem, onClick: () -> Unit) {
    val context = LocalContext.current
    val (getDisplayWidth, getDisplayHeight, _) = AppUtils.screenHeightAndWidth(LocalContext.current)
    var animatedVisibility by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(getDisplayHeight.dp.times(0.1f))
            .padding(top = 8.dp, end = 8.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedVisibility(animatedVisibility, enter = fadeIn(), exit = fadeOut()) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(getDisplayWidth.dp.times(0.06f))
                    .padding(start = 1.dp, end = 3.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "1X", style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 12.5.sp,
                        textAlign = TextAlign.Left
                    )
                )
                NavIconOrAddOrRemoveIcon(
                    icon = painterResource(id = R.drawable.ic_remove),
                    iconBgColor = AppColors.green,
                    btnSize = 15.dp,
                    iconClicked = {
                        animatedVisibility = false
                        showToastMessage(context, "1 item removed from cart")
                    }
                )
            }
        }


        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .width(getDisplayWidth.dp.times(0.17f)),
            color = Color.White,
            shape = RoundedCornerShape(12)
        ) {
            Image(
                painter = painterResource(id = salesItem.itemImage),
                contentDescription = salesItem.itemName,
                contentScale = ContentScale.FillBounds
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = salesItem.itemName, style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Left
                    )
                )

                Text(
                    text = "${Constants.CEDI} %2.2f".format(salesItem.price), style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Left
                    )
                )
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = salesItem.itemDescription, style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 9.5.sp,
                        textAlign = TextAlign.Left,
                        color = Color.Black.copy(0.67f)
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                NavIconOrAddOrRemoveIcon(
                    icon = painterResource(id = R.drawable.ic_add_1),
                    iconBgColor = AppColors.green,
                    btnSize = 22.dp,
                    iconClicked = {
                        animatedVisibility = true
                        showToastMessage(context, "1 item add to cart")
                    }
                )
            }
        }
    }
}
package dev.dna.deliveryapp.screens.health_and_beauty

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.dna.deliveryapp.R
import dev.dna.deliveryapp.data.fake.fake_data_health_and_beauty
import dev.dna.deliveryapp.navigation.Screens
import dev.dna.deliveryapp.screens.food.ItemOrProductCard
import dev.dna.deliveryapp.screens.food.OgaTopAppBar
import dev.dna.deliveryapp.screens.food.SearchBox
import dev.dna.deliveryapp.screens.food.searchItem
import dev.dna.deliveryapp.utils.AppColors


private const val TAG = "HealthAndBeautyScreen"


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HealthAndBeautyScreen(navController: NavController){
    Log.d(TAG, "HealthAndBeautyScreen: called")

    val state = rememberLazyListState()


    Scaffold(
        topBar = {
            OgaTopAppBar(
                title = stringResource(id = R.string.health_and_beauty_lbl),
                bgColor = Color.White,
                iconBgColor = AppColors.green,
                icon = painterResource(id = R.drawable.ic_close),
                elevation = 0.dp,
                navController = navController,
                isCart = true,
                iconClicked = {navController.popBackStack()},
                onCartOrHelpIcon = {navController.navigate(Screens.CartScreen.route)}
            )
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // search box
                SearchBox(label = stringResource(id = R.string.place_or_product_lbl), onSearch = {
                    Log.d(TAG, "HealthAndBeautyScreen: search query is $it")
                    searchItem(it)
                })

                Spacer(modifier = Modifier.height(16.dp))

                // food items available
                LazyColumn(
                    contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp),
                    state = state,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    items(items = fake_data_health_and_beauty) { item ->
                        ItemOrProductCard(item = item, isFood = false) {
                            Log.d(TAG, "HealthAndBeautyScreen: item ${item.itemImage} clicked")
                            navController.navigate(Screens.StoreOrShopScreen.route)
                        }
                    }
                }
            }
        }
    }
}
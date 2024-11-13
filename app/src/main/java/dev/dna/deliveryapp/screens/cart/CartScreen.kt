package dev.dna.deliveryapp.screens.cart

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.dna.deliveryapp.R
import dev.dna.deliveryapp.data.fake.CartItem
import dev.dna.deliveryapp.data.fake.fake_data_cartItems
import dev.dna.deliveryapp.navigation.Screens
import dev.dna.deliveryapp.screens.anything.showToastMessage
import dev.dna.deliveryapp.utils.AppColors
import dev.dna.deliveryapp.utils.Constants
import dev.dna.deliveryapp.screens.food.OgaTopAppBar
import dev.dna.deliveryapp.screens.home.CircularButton


private const val TAG = "CartScreen"


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CartScreen(navController: NavController) {
    val state = rememberLazyListState()

    Scaffold(
        topBar = {
            OgaTopAppBar(
                title = stringResource(id = R.string.cart_lbl),
                bgColor = Color.White,
                iconBgColor = AppColors.green,
                icon = painterResource(id = R.drawable.ic_close),
                navController = navController,
                isCart = null,
                iconClicked = { navController.popBackStack() },
                onCartOrHelpIcon = {}
            )
        },
        floatingActionButton = {
            // proceed to check out button
            CircularButton(
                title = stringResource(id = R.string.proceed_to_checkout_lbl),
                bgColor = AppColors.green,
                onClicked = {
                    Log.d(TAG, "CartScreen: proceed to checkout clicked")
                    navController.navigate(Screens.CheckOutAndNumberScreen.route)
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        backgroundColor = Color.White
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(8.dp),
                    state = state,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(items = fake_data_cartItems) { item ->
                        CartItemCard(item = item, onAddMore = {}, onRemove = {})
                    }
                }
            }
        }
    }
}

@Composable
fun CartItemCard(item: CartItem, onAddMore: (CartItem) -> Unit, onRemove: (CartItem) -> Unit) {
    val context = LocalContext.current

    Surface(modifier = Modifier.fillMaxWidth(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // item name and quantity
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 22.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_semibold))
                            ), block = {
                                append(item.itemQuantity.toString() + "X ")
                            })
                        withStyle(
                            style = SpanStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular))
                            ), block = {
                                append(item.itemName)
                            })
                    },
                    modifier = Modifier.padding(start = 4.dp, end = 4.dp)
                )

                // price
                Text(
                    text = "${Constants.CEDI} %2.2f".format(item.itemPrice), style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                )
            }

            // add more and remove item button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, end = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavIconOrAddOrRemoveIcon(
                    icon = painterResource(id = R.drawable.ic_remove),
                    iconBgColor = AppColors.green,
                    btnSize = 24.dp,
                    iconClicked = {
                        onRemove(item); showToastMessage(
                        context,
                        "1 items removed from cart"
                    )
                    }
                )
                NavIconOrAddOrRemoveIcon(
                    icon = painterResource(id = R.drawable.ic_add_1),
                    iconBgColor = AppColors.green,
                    btnSize = 24.dp,
                    iconClicked = {
                        onAddMore(item); showToastMessage(
                        context,
                        "1 more items added to cart"
                    )
                    }
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 2.dp,
                color = AppColors.green
            )
            Spacer(modifier = Modifier.height(6.dp))

        }
    }
}
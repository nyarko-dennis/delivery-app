package dev.dna.deliveryapp.screens.food

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import dev.dna.deliveryapp.R
import dev.dna.deliveryapp.data.fake.RestaurantType
import dev.dna.deliveryapp.data.fake.SalesItem
import dev.dna.deliveryapp.data.fake.fake_data
import dev.dna.deliveryapp.navigation.Screens
import dev.dna.deliveryapp.utils.AppColors
import dev.dna.deliveryapp.utils.AppUtils


private const val TAG = "FoodScreen"


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FoodScreen(navController: NavController) {
    Log.d(TAG, "FoodScreen: called")

    val state = rememberLazyListState()
    val showApplyPopup = remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            OgaTopAppBar(
                title = stringResource(id = R.string.food_screen_lbl),
                bgColor = Color.White,
                iconBgColor = AppColors.green,
                icon = painterResource(id = R.drawable.ic_close),
                elevation = 0.dp,
                navController = navController,
                isCart = true,
                iconClicked = { navController.popBackStack() },
                onCartOrHelpIcon = { navController.navigate(Screens.CartScreen.route) }
            )
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {

            // whether to show popup or not
            FoodFilterPopupMenu(showPopup = showApplyPopup, navController = navController)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // search box
                SearchBox(label = stringResource(id = R.string.place_or_product_lbl), onSearch = {
                    Log.d(TAG, "FoodScreen: search query is $it")
                    searchItem(it)
                    showApplyPopup.value = true
                })

                Spacer(modifier = Modifier.height(16.dp))
                // food dashboard
                FoodTypeDashBoard()

                Spacer(modifier = Modifier.height(12.dp))

                // food items available
                LazyColumn(
                    contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp),
                    state = state,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    items(items = fake_data) { item ->
                        ItemOrProductCard(item = item) {
                            Log.d(TAG, "FoodScreen: item ${item.itemImage} clicked")
                            navController.navigate(Screens.StoreOrShopScreen.route)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ItemOrProductCard(item: SalesItem, isFood: Boolean = true, onClicked: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(bottom = 12.dp)
            .clickable { onClicked() },
        shape = RoundedCornerShape(20)
    ) {
        Image(
            painter = painterResource(id = item.itemImage),
            contentDescription = item.itemName,
            contentScale = ContentScale.Crop
        )
        Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Top) {
            RoundedButton(
                label = item.rating.toString(),
                radius = 90,
                topRadius = 0,
                icon = R.drawable.ic_star
            )
        }
        Row(horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.Bottom) {
            RoundedButton(
                label = "3 min",
                radius = 90,
                topRadius = 0,
                icon = R.drawable.ic_clock,
                color = Color.Blue.copy(0.7f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item.itemName,
                    style = TextStyle(
                        fontSize = 34.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        color = Color.White
                    )
                )
                if (isFood) {
                    RoundedButton(
                        label = item.category,
                        radius = 60,
                        topRadius = 60,
                        icon = null,
                        color = AppColors.lightLightGreen.copy(0.7f)
                    )
                }
            }
        }

    }
}


@Composable
fun FoodTypeDashBoard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FoodType(title = stringResource(id = R.string.all_lbl), icon = R.drawable.ic_all) {}
        FoodType(title = stringResource(id = R.string.local_lbl), icon = R.drawable.ic_local) {}
        FoodType(
            title = stringResource(id = R.string.fast_food_lbl),
            icon = R.drawable.ic_fast_food
        ) {}
        FoodType(title = stringResource(id = R.string.healthy), icon = R.drawable.ic_healthy) {}
        FoodType(title = stringResource(id = R.string.chicken_lbl), icon = R.drawable.ic_chicken) {}
    }
}

@Composable
fun FoodType(title: String, icon: Int, onClicked: () -> Unit) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .size(40.dp)
                .clickable { onClicked() },
            shape = CircleShape,
            color = AppColors.green
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "title",
                tint = Color.White,
                modifier = Modifier.padding(5.dp)
            )
        }
        Text(
            text = title,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBox(
    label: String,
    placeholderIcon: Int = R.drawable.icon_search,
    singleLine: Boolean = true,
    onFocused: (FocusState) -> Unit = {},
    onSearch: (String) -> Unit
) {
    val searchQuery = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = searchQuery.value.trim().isNotEmpty()

    TakeUserInput(
        valueState = searchQuery,
        label = label,
        placeholderIcon = placeholderIcon,
        singleLine = singleLine,
        onFocused = onFocused,
        imeAction = ImeAction.Search,
        onAction = KeyboardActions {
            if (!valid) return@KeyboardActions

            onSearch(searchQuery.value)
            keyboardController?.hide()
        }
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TakeUserInput(
    valueState: MutableState<String>,
    label: String,
    placeholderIcon: Int = R.drawable.icon_search,
    singleLine: Boolean = true,
    onFocused: (FocusState) -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Go,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    var animatedVisibility by remember { mutableStateOf(false) }
    val (_, _, screenArea) = AppUtils.screenHeightAndWidth(LocalContext.current)


    OutlinedTextField(
        value = valueState.value,
        onValueChange = {
            valueState.value = it
            animatedVisibility = it.trim().isNotEmpty()
        },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { onFocused(it) },
        placeholder = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = placeholderIcon),
                    contentDescription = "Search icon",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = label,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color.Gray
                    )
                )
            }
        },
        trailingIcon = {
            AnimatedVisibility(animatedVisibility, enter = fadeIn(), exit = fadeOut()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "Clear text",
                    modifier = Modifier
                        .size(screenArea.dp.times(0.00008f))
                        .background(
                            color = AppColors.lightLightGreen,
                            shape = CircleShape
                        )
                        .clickable {
                            valueState.value = ""
                            animatedVisibility = false
                        },
                    tint = Color.White
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            autoCorrect = true,
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = onAction,
        singleLine = singleLine,
        shape = CircleShape,
        colors = textFieldColors(
            focusedTextColor = Color.Black.copy(0.69f),
            cursorColor = Color.Gray,
            focusedContainerColor = Color.LightGray,
            focusedIndicatorColor = Color.LightGray,
            unfocusedIndicatorColor = Color.LightGray
        )
    )
}

fun searchItem(item: String) {
    Log.d(TAG, "searchItem: item to search is $item")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OgaTopAppBar(
    title: String?,
    bgColor: Color,
    iconBgColor: Color,
    icon: Painter,
    elevation: Dp = 0.dp,
    navController: NavController,
    isCart: Boolean? = true,
    iconClicked: () -> Unit,
    onCartOrHelpIcon: () -> Unit
) {

    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 19.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = bgColor
        ),
        title = {
            Text("")
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavIconOrAddOrRemoveIcon(icon, iconBgColor, iconClicked = iconClicked)

            if (title != null) {
                Text(
                    text = title, style = TextStyle(
                        fontSize = 30.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily(
                            Font(R.font.poppins_bold)
                        ), textAlign = TextAlign.Center
                    )
                )
            } else {
                Box {}
            }

            if (isCart != null) {
                if (isCart) {
                    CartActionButton(
                        text = "0",
                        iconBgColor = if (bgColor == Color.Transparent) Color.Transparent else Color.White,
                        navController = navController,
                        onClicked = { onCartOrHelpIcon() }
                    )
                } else {
                    Row(
                        modifier = Modifier
                            .background(
                                color = AppColors.lightLightGreen,
                                shape = CircleShape
                            )
                            .clickable { onCartOrHelpIcon() }
                            .width(80.dp)
                            .height(35.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_call),
                            contentDescription = "Help icon",
                            modifier = Modifier.size(20.dp),
                            tint = Color.White
                        )
                        Text(
                            text = stringResource(id = R.string.help_lbl), style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                textAlign = TextAlign.Left,
                                color = Color.White
                            )
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .width(70.dp)
                        .height(35.dp)
                )
            }

        }

    }
}

@Composable
fun NavIconOrAddOrRemoveIcon(
    icon: Painter,
    iconBgColor: Color,
    btnSize: Dp = 34.dp,
    iconClicked: () -> Unit
) {
    Icon(
        painter = icon,
        contentDescription = "back icon",
        tint = if (iconBgColor == Color.White) Color.Black else Color.White,
        modifier = Modifier
            .background(color = iconBgColor, shape = CircleShape)
            .clickable { iconClicked() }
            .size(btnSize)
    )
}


@Composable
fun FoodFilterPopupMenu(showPopup: MutableState<Boolean>, navController: NavController) {
    Log.d(TAG, "FoodFilterPopupMenu: called")
    Log.d(TAG, "FoodFilterPopupMenu: and the boolean value is ${showPopup.value}")

    val (getScreenWidth, getScreenHeight, _) = AppUtils.screenHeightAndWidth(LocalContext.current)
    val animatedVisibility by remember { showPopup }
    Log.d(TAG, "FoodFilterPopupMenu: $animatedVisibility")

    if (showPopup.value) {
        Dialog(
            onDismissRequest = { showPopup.value = false },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
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
                        .width(getScreenWidth.dp.times(0.99f))
                        .height(getScreenHeight.dp.times(0.57f)),
                    color = Color.White,
                    shape = RoundedCornerShape(topStartPercent = 10, topEndPercent = 10),
                    shadowElevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 8.dp, end = 8.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        HorizontalDivider(
                            modifier = Modifier
                                .width(getScreenWidth.dp.times(0.25f))
                                .padding(top = 6.dp),
                            thickness = 2.dp,
                            color = Color.Gray
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, top = 4.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.sort_by_lbl), style = TextStyle(
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                    color = Color.Black.copy(0.87f)
                                )
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 12.dp, end = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RoundedButton(
                                label = stringResource(id = R.string.near_me_lbl),
                                radius = 50,
                                topRadius = 50,
                                icon = R.drawable.ic_pin_location,
                                color = AppColors.green,
                                onPress = {}
                            )

                            RoundedButton(
                                label = stringResource(id = R.string.delivery_time_text_lbl),
                                radius = 50,
                                topRadius = 50,
                                icon = R.drawable.ic_clocky,
                                color = AppColors.green,
                                onPress = {}
                            )

                            RoundedButton(
                                label = stringResource(id = R.string.best_rated_lbl),
                                radius = 50,
                                topRadius = 50,
                                icon = R.drawable.ic_star,
                                color = AppColors.green,
                                onPress = {}
                            )
                        }


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, top = 12.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.types_of_restaurants_lbl),
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                    color = Color.Black.copy(0.87f)
                                )
                            )
                        }

                        val restaurantsType = listOf(
                            RestaurantType("Local", R.drawable.ic_local),
                            RestaurantType("Fast Food", R.drawable.ic_fast_food),
                            RestaurantType("Healthy", R.drawable.ic_healthy),
                            RestaurantType("Chicken", R.drawable.ic_chicken)
                        )
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(items = restaurantsType) { item ->
                                RestaurantTypeCard(item = item, onClicked = {})
                            }
                        }

                        Box(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                            SignInAndSignOptions(
                                icon = null,
                                title = stringResource(id = R.string.apply_lbl),
                                bgColor = AppColors.green,
                                navController = navController,
                                onClicked = { showPopup.value = false }
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun RestaurantTypeCard(item: RestaurantType, onClicked: () -> Unit) {
    val (_, _, screenArea) = AppUtils.screenHeightAndWidth(LocalContext.current)

    Surface(modifier = Modifier.clickable { onClicked() }, color = Color.White) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = "title",
                tint = Color.Black,
                modifier = Modifier
                    .padding(5.dp)
                    .size(screenArea.dp.times(0.00019f))
            )
            Text(
                text = item.type,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}
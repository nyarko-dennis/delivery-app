package dev.dna.deliveryapp.screens.home

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
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
import dev.dna.deliveryapp.data.fake.FAKE_DATA
import dev.dna.deliveryapp.data.fake.StoresAround
import dev.dna.deliveryapp.navigation.Screens
import dev.dna.deliveryapp.screens.food.SearchBox
import dev.dna.deliveryapp.utils.AppColors
import dev.dna.deliveryapp.utils.AppUtils

private const val TAG = "HomeScreen"

private val dashboardItems = listOf(
    ItemCategory.FOOD,
    ItemCategory.FASHION,
    ItemCategory.PACKAGE_DELIVERY,
    ItemCategory.ANYTHING,
    ItemCategory.ELECTRONICS,
    ItemCategory.HEALTH_AND_BEAUTY
)



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val (_, getDisplayHeight) = AppUtils.screenHeightAndWidth(LocalContext.current)
    val showApplyPopup = rememberSaveable { mutableStateOf(false) }
    val scaffoldState = rememberBottomSheetScaffoldState()

    Log.d(TAG, "HomeScreen: scaffoldState is ${scaffoldState.bottomSheetState.currentValue}")


    // pick a location popup
    LocationPopup(showPopup = showApplyPopup, navController = navController)

    BottomSheetScaffold(
        containerColor = AppColors.green,
        sheetSwipeEnabled = true,
        sheetContent = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.89f),
                shape = RoundedCornerShape(
                    topStartPercent = 10,
                    topEndPercent = 10,
                    bottomEndPercent = 0,
                    bottomStartPercent = 0
                ),
                shadowElevation = 0.dp,
                color = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.stores_around_lbl),
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_semibold))
                            ),
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        CircularButtonWithIcon(
                            title = "Pick location",
                            bgColor = AppColors.green,
                            icon = R.drawable.ic_pin_location,
                            fieldWidth = 140.dp
                        ) {
                            Log.d(TAG, "HomeScreen: circularButtonWithIcon called")
//                                showApplyPopup.value = true
                            navController.navigate(Screens.LocationMapScreen.route + "/Search near location")
                        }
                    }

                    val state = rememberLazyGridState()

                    LazyVerticalGrid(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.CenterHorizontally),
                        columns = GridCells.Fixed(2),
                        state = state,
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        items(items = FAKE_DATA) { item: StoresAround ->
                            ItemCard(
                                item,
                                onPressDetails = { navController.navigate(Screens.FoodOrProductItemScreen.route) })
                        }
                    }
                }
            }
        },
        topBar = {
            OgaHomeTopAppBar(
                title = stringResource(id = R.string.search_lbl),
                userIcon = painterResource(id = R.drawable.icon_user),
                menuIcon = painterResource(id = R.drawable.icon_shopping_cart),
                navController = navController,
                onSearch = {}
            )
        },
        scaffoldState = scaffoldState,
        sheetShadowElevation = 8.dp,
        sheetPeekHeight = getDisplayHeight.dp.times(0.45f),
        sheetContainerColor = Color.White,
        sheetShape = RoundedCornerShape(
            topStartPercent = 10,
            topEndPercent = 10,
            bottomEndPercent = 0,
            bottomStartPercent = 0
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(), color = AppColors.green
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(getDisplayHeight.dp.times(0.4f))
                        .padding(bottom = 4.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CategoryDashBoardGrid(navController = navController)
                }


            }
        }
    }
}


// popup for picking location
@Composable
fun LocationPopup(showPopup: MutableState<Boolean>, navController: NavController) {
    val (getDisplayWidth, getDisplayHeight, _) = AppUtils.screenHeightAndWidth(LocalContext.current)
    val animatedVisibility by remember { showPopup }

    if (showPopup.value) {
        Dialog(
            onDismissRequest = { showPopup.value = false }, properties = DialogProperties(
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
                            text = stringResource(id = R.string.search_stores_around_lbl),
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 2.dp)) {
                            SearchBox(
                                label = stringResource(id = R.string.search_location_lbl),
                                onSearch = {}
                            )
                        }

                        Box(modifier = Modifier.padding(start = 90.dp, end = 90.dp, top = 32.dp)) {
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
fun RoundedButton(
    label: String = "Fast food",
    radius: Int = 29,
    topRadius: Int = 27,
    icon: Int? = R.drawable.ic_star,
    color: Color = Color(0xFFFAB134),
    isClickable: Boolean = false,
    onPress: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    topStartPercent = radius,
                    bottomEndPercent = radius,
                    topEndPercent = topRadius,
                    bottomStartPercent = topRadius
                )
            )
            .clickable(enabled = isClickable) { onPress.invoke() },
        color = color
    ) {
        Row(
            modifier = Modifier
                .width(90.dp)
                .heightIn(30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = label,
                    tint = Color.White,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(3.dp)
                )
            }
            Text(text = label, style = TextStyle(color = Color.White, fontSize = 11.sp))
        }
    }
}


//@Preview
@Composable
fun ItemCard(
    item: StoresAround = FAKE_DATA[0],
    onPressDetails: (String) -> Unit = {}
) {
    val salesItem = item.listOfSaleItems[0]

    Column(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Surface(
            shape = RoundedCornerShape(29.dp),
            color = Color.White,
            shadowElevation = 6.dp,
            modifier = Modifier
                .height(130.dp)
                .width(200.dp)
//                .fillMaxSize(.80f)
                .clickable { onPressDetails(salesItem.itemName) }
        ) {

            Row(horizontalArrangement = Arrangement.Center) {
                Image(
                    painter = painterResource(id = R.drawable.fast_food_3),
                    contentDescription = salesItem.itemName,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
            }

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top
            ) {
                RoundedButton(
                    label = salesItem.rating.toString(),
                    icon = R.drawable.ic_star,
                    radius = 0,
                    topRadius = 70
                )
            }

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Bottom
            ) {
                RoundedButton(
                    label = "Fast food",
                    icon = null,
                    radius = 0,
                    topRadius = 70,
                    color = AppColors.lightLightGreen
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = salesItem.itemName,
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight.W600
            ),
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}


@Composable
fun CircularButtonWithIcon(
    title: String,
    icon: Int,
    fieldWidth: Dp = 150.dp,
    bgColor: Color = AppColors.lightLightGreen,
    textColor: Color = Color.White,
    onClicked: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(top = 4.dp, start = 4.dp, bottom = 4.dp)
            .width(fieldWidth)
            .height(35.dp)
            .background(color = bgColor, shape = CircleShape)
            .clickable { onClicked() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "title",
            modifier = Modifier.size(20.dp),
            tint = Color.White
        )

        Text(
            text = title, style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = textColor
            )
        )
    }
}


@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    fieldHeight: Dp,
    fieldWidth: Dp,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    onAction: KeyboardActions
) {
    InputField(
        modifier = modifier,
        valueState = valueState,
        labelId, fieldHeight, fieldWidth, keyboardType, imeAction, onAction
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier: Modifier,
    valueState: MutableState<String>,
    labelId: String,
    fieldHeight: Dp = 62.dp,
    fieldWidth: Dp = 250.dp,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    var animatedVisibility by remember { mutableStateOf(false) }
    val (_, _, screenArea) = AppUtils.screenHeightAndWidth(LocalContext.current)

    TextField(
        modifier = modifier
            .height(fieldHeight)
            .width(fieldWidth),
        value = valueState.value,
        onValueChange = { valueState.value = it; animatedVisibility = it.trim().isNotEmpty() },
        placeholder = {
            Text(
                text = labelId, textAlign = TextAlign.Center,
                style = TextStyle(
                    color = AppColors.green,
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.icon_search),
                contentDescription = "Search icon"
            )
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
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 14.sp,
            color = AppColors.green,
            fontFamily = FontFamily(Font(R.font.poppins_regular))
        ),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            autoCorrectEnabled = true,
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = onAction,
        colors = ExposedDropdownMenuDefaults.textFieldColors(
            focusedTextColor = AppColors.green,
            focusedContainerColor = Color.White,
            cursorColor = Color.LightGray,
            focusedIndicatorColor = AppColors.green,
            unfocusedIndicatorColor = AppColors.green
        ),
        shape = CircleShape
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OgaHomeTopAppBar(
    title: String,
    userIcon: Painter,
    menuIcon: Painter,
    navController: NavController,
    onSearch: (String) -> Unit
) {
    val (getDisplayWidth, getDisplayHeight) = AppUtils.screenHeightAndWidth(LocalContext.current)

    val itemToSearch = remember {
        mutableStateOf("")
    }
    val valid = itemToSearch.value.trim().isNotEmpty()

    val keyboardController = LocalSoftwareKeyboardController.current

    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), backgroundColor = AppColors.green, elevation = 0.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = userIcon,
                contentDescription = "User profile pic",
//                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .clickable { navController.navigate(Screens.UserProfileScreen.route) }
                    .clip(CircleShape)
                    .background(color = AppColors.lightLightGreen, shape = CircleShape)
                    .size(34.dp)
                    .scale(0.67f),
                tint = AppColors.grayish
            )

            val dpScale: Float = 8.dp.div(getDisplayHeight.dp)
            val v = getDisplayWidth.dp.times(dpScale)

            SearchForm(
                modifier = Modifier.padding(start = v, end = v),
                valueState = itemToSearch,
                labelId = title,
                fieldHeight = getDisplayHeight.dp.times(0.082f),
                fieldWidth = getDisplayWidth.dp.times(0.71f),
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
                onAction = KeyboardActions {
                    if (!valid) return@KeyboardActions

                    Log.d(TAG, "OgaTopAppBar: the search item is ${itemToSearch.value}")
                    onSearch(itemToSearch.value)
                    itemToSearch.value = ""
                    keyboardController?.hide()
                }
            )

            CartActionButton(
                menuIcon,
                text = "0",
                AppColors.green,
                navController,
                onClicked = { navController.navigate(Screens.CartScreen.route) }
            )
        }

    }
}

@Composable
fun CartActionButton(
    menuIcon: Painter = painterResource(id = R.drawable.icon_shopping_cart),
    text: String? = "0",
    iconBgColor: Color,
    navController: NavController,
    onClicked: () -> Unit
) {
    Surface(
        modifier = Modifier.clickable { onClicked() },
        color = iconBgColor //AppColors.green
    ) {
        Icon(
            painter = menuIcon,
            contentDescription = "Cart icon",
            modifier = Modifier.background(color = iconBgColor),
            tint = if (iconBgColor == Color.White) AppColors.cartIconColor else Color.White
        )
        if (text != null) {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = text,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_light)),
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .background(
                            color = Color.Red.copy(0.78f),
                            shape = CircleShape
                        )
                        .size(18.dp)
                )
            }
        }
    }
}


@Composable
fun CategoryItemCard(
    icon: Int,
    title: String,
    navController: NavController,
    itemCategory: ItemCategory
) {
    val (getDisplayWidth, getDisplayHeight) = AppUtils.screenHeightAndWidth(LocalContext.current)
    val h = getDisplayHeight.dp.times(6.dp.div(getDisplayHeight.dp))
    val v = getDisplayWidth.dp.times(6.dp.div(getDisplayWidth.dp))

    Card(
        modifier = Modifier
            .clickable {
                when (itemCategory) {
                    ItemCategory.FOOD -> navController.navigate(Screens.FoodScreen.route)
                    ItemCategory.ANYTHING -> navController.navigate(Screens.AnythingScreen.route)
                    ItemCategory.FASHION -> navController.navigate(Screens.FashionScreen.route)
                    ItemCategory.ELECTRONICS -> navController.navigate(Screens.ElectronicsScreen.route)
                    ItemCategory.PACKAGE_DELIVERY -> navController.navigate(Screens.PackageDeliveryScreen.route)
                    ItemCategory.HEALTH_AND_BEAUTY -> navController.navigate(Screens.HealthAndBeautyScreen.route)
                }
            }
            .width(getDisplayWidth.dp.times(0.027f))
            .height(getDisplayHeight.dp.times(0.16f))
            .padding(start = v, end = v, top = h, bottom = h),
        shape = RoundedCornerShape(16),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, color = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 2.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = title,
                tint = AppColors.lightLightGreen,
                modifier = Modifier
                    .height(40.dp)
                    .scale(0.85f),
            )

            Text(
                text = title,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
            )

        }
    }
}

@Composable
fun CategoryDashBoardGrid(navController: NavController) {
    val icons = listOf(
        R.drawable.ic_fast_food,
        R.drawable.ic_fashion_icon,
        R.drawable.ic_delivery,
        R.drawable.ic_search_icon,
        R.drawable.ic_electroonics_icon,
        R.drawable.ic_health
    )
    val (_, getDisplayHeight) = AppUtils.screenHeightAndWidth(LocalContext.current)


    Column(
//        modifier = Modifier.height(getDisplayHeight.dp.times(0.48f)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.height(getDisplayHeight.dp.times(0.319f)),
            contentPadding = PaddingValues(start = 24.dp, end = 24.dp/*, bottom = 16.dp*/),
            verticalArrangement = Arrangement.Top,
            horizontalArrangement = Arrangement.SpaceEvenly,
            userScrollEnabled = false
        ) {
            items(items = dashboardItems) { item: ItemCategory ->
                CategoryItemCard(
                    icon = icons[item.ordinal],
                    title = item.item,
                    navController,
                    itemCategory = item
                )
            }
        }

        // last order button
        val dpScale: Float = 4.dp.div(getDisplayHeight.dp)
        Spacer(modifier = Modifier.height(getDisplayHeight.dp.times(dpScale)))

        Log.d(TAG, "CategoryDashBoardGrid: dpScale is $dpScale")
        Log.d(TAG, "CategoryDashBoardGrid: space${getDisplayHeight.dp.times(dpScale)}")
        CircularButton(title = "Last Order") {
            Log.d(TAG, "HomeScreen: last order button clicked")
            navController.navigate(Screens.OrderScreen.route)
        }
    }
}


@Composable
fun CircularButton(
    title: String,
    fieldMaxWidth: Float = 0.86f,
    bgColor: Color = AppColors.lightLightGreen,
    textColor: Color = Color.White,
    onClicked: () -> Unit = {}
) {
    Button(
        onClick = { onClicked() }, modifier = Modifier
            .fillMaxWidth(fieldMaxWidth),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            focusedElevation = 0.dp,
            disabledElevation = 0.dp
        ),
        shape = CircleShape,
        colors = buttonColors(containerColor = bgColor)
    ) {

        Text(
            text = title, style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                color = textColor,
            )
        )
    }

}
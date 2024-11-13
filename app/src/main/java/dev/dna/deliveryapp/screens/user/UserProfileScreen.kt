package dev.dna.deliveryapp.screens.user

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.dna.deliveryapp.R
import dev.dna.deliveryapp.navigation.Screens
import dev.dna.deliveryapp.screens.food.OgaTopAppBar
import dev.dna.deliveryapp.utils.AppColors
import dev.dna.deliveryapp.utils.AppUtils

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserProfileScreen(navController: NavController) {
    Scaffold(
        topBar = {
            OgaTopAppBar(
                title = null,
                bgColor = AppColors.green,
                iconBgColor = AppColors.lightLightGreen,
                icon = painterResource(id = R.drawable.ic_close),
                navController = navController,
                isCart = false,
                iconClicked = { navController.popBackStack() },
                onCartOrHelpIcon = {}
            )
        },
        containerColor = AppColors.green
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = AppColors.green) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(id = R.string.hello_user_lbl), style = TextStyle(
                        fontSize = 22.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.poppins_bold))
                    ),
                    modifier = Modifier.padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Surface(
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.White,
                    shape = RoundedCornerShape(topStartPercent = 5, topEndPercent = 5)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(start = 20.dp, end = 20.dp, bottom = 120.dp, top = 8.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = stringResource(id = R.string.app_lbl), style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_semibold))
                            )
                        )

                        // orders
                        UserScreenItemRow(
                            title = stringResource(id = R.string.orders_lbl),
                            icon = R.drawable.ic_baseline_shopping_bag_24,
                            onClick = { navController.navigate(Screens.OrderScreen.route) }
                        )

                        // app settings
                        UserScreenItemRow(
                            title = stringResource(id = R.string.app_settings_lbl),
                            icon = R.drawable.ic_baseline_settings_24,
                            onClick = {}
                        )

                        // account
                        Text(
                            text = stringResource(id = R.string.account_lbl), style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_semibold))
                            )
                        )

                        // my information
                        UserScreenItemRow(
                            title = stringResource(id = R.string.my_info_lbl),
                            icon = R.drawable.ic_baseline_person_24,
                            onClick = { navController.navigate(Screens.UserInformationScreen.route) }
                        )

                        // history
                        UserScreenItemRow(
                            title = stringResource(id = R.string.history_lbl),
                            icon = R.drawable.ic_baseline_history_24,
                            onClick = {}
                        )

                        // F. A. Q
                        UserScreenItemRow(
                            title = stringResource(id = R.string.faq_lbl),
                            icon = R.drawable.ic_faq,
                            onClick = {}
                        )

                        // notifications
                        UserScreenItemRow(
                            title = stringResource(id = R.string.notification_lbl),
                            icon = R.drawable.ic_baseline_notifications_active_24,
                            onClick = {}
                        )

                        // log out
                        UserScreenItemRow(
                            title = stringResource(id = R.string.logout_lbl),
                            icon = R.drawable.ic_log_out,
                            onClick = {}
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UserScreenItemRow(title: String, icon: Int, onClick: () -> Unit) {
    val (_, _, screenArea) = AppUtils.screenHeightAndWidth(LocalContext.current)


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = title,
            tint = Color.Black,
            modifier = Modifier
                .size(screenArea.dp.times(0.000059f))
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = title, style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                textAlign = TextAlign.Left
            )
        )
    }
}
package dev.dna.deliveryapp.screens.user

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import dev.dna.deliveryapp.navigation.Screens
import dev.dna.deliveryapp.screens.food.OgaTopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserInformationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            OgaTopAppBar(
                title = stringResource(id = R.string.my_info_lbl),
                bgColor = Color.White,
                iconBgColor = Color.White,
                icon = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                navController = navController,
                isCart = null,
                elevation = 4.dp,
                iconClicked = { navController.popBackStack() },
                onCartOrHelpIcon = {}
            )
        },
        containerColor = Color.White
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(start = 20.dp, end = 20.dp, bottom = 150.dp, top = 8.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                // User name
                UserScreenItemRow(
                    title = stringResource(id = R.string.user_name_lbl),
                    icon = R.drawable.icon_user,
                    onClick = {}
                )

                // email
                UserScreenItemRow(
                    title = stringResource(id = R.string.email_lbl),
                    icon = R.drawable.ic_baseline_email_24,
                    onClick = {}
                )

                // password
                UserScreenItemRow(
                    title = stringResource(id = R.string.password_lbl),
                    icon = R.drawable.password,
                    onClick = {}
                )

                // change number
                UserScreenItemRow(
                    title = stringResource(id = R.string.change_number_lbl),
                    icon = R.drawable.ic_phone,
                    onClick = { navController.navigate(Screens.PhoneNumberScreen.route) }
                )

                // Payment method
                UserScreenItemRow(
                    title = stringResource(id = R.string.payment_method_lbl),
                    icon = R.drawable.ic_faq,
                    onClick = {}
                )

                // manage privacy
                UserScreenItemRow(
                    title = stringResource(id = R.string.manage_privacy_lbl),
                    icon = R.drawable.privacy,
                    onClick = {}
                )
            }
        }
    }
}
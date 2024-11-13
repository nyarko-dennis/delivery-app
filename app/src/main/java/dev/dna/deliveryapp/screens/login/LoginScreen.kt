package dev.dna.deliveryapp.screens.login

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.dna.deliveryapp.R
import dev.dna.deliveryapp.components.LoginOrSignUpWithPhoneNumber
import dev.dna.deliveryapp.navigation.Screens

private const val TAG = "LoginScreen"

@Composable
fun LoginScreen(navController: NavController, registerViewModel:RegisterAndLoginViewModel = viewModel()) {
    val phoneNumber = remember { mutableStateOf("") }
    remember(phoneNumber) {
        mutableStateOf(phoneNumber.value.trim().length == 10)
    }
//    val labelId = stringResource(R.string.enter_phone_number)


    Surface(modifier = Modifier.fillMaxSize(), shadowElevation = 0.dp) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Login",
                    modifier = Modifier,
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 48.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold))
                    )
                )

                Spacer(modifier = Modifier.fillMaxHeight(0.03f))

                AllLoginOptions(navController)
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // login with phone
                Text(
                    text = stringResource(id = R.string.login_with_phone),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 26.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.015f))
                LoginOrSignUpWithPhoneNumber(navController, registerViewModel = registerViewModel) {
                    Log.d(TAG, "LoginScreen: Signing in")
                    navController.navigate(Screens.OTPCodeScreen.route)
                }
            }
        }
    }
}

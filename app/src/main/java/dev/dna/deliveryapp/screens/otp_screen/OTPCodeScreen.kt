package dev.dna.deliveryapp.screens.otp_screen

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.dna.deliveryapp.R
import dev.dna.deliveryapp.navigation.Screens
import dev.dna.deliveryapp.utils.AppColors
import dev.dna.deliveryapp.utils.AppUtils

private const val TAG = "OTPCodeScreen"


@Composable
fun OTPCodeScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(id = R.string.otp_code_lbl),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.height(30.dp))

            OTPCodeInputRow()



            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.didnt_get_code_lbl), style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    text = stringResource(id = R.string.resend_code_lbl), style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    ),
                    modifier = Modifier.clickable { /*TODO*/ },
                    color = AppColors.clickableTextColor
                )
            }
            //TODO delete this and modify OTPCodeInputFilled above
            Button(onClick = { navController.navigate(Screens.WelcomeScreen.route) }) {
                Text("Next")
            }
        }
//        Spacer(modifier = Modifier.height(120.dp))
    }
}


/*
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OTPCodeInputFilled(onCodeComplete: (String) -> Unit) {
    val fullOtpCode = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    val otpCode1 = remember { mutableStateOf("") }
    val valid1 = remember(otpCode1) {
        mutableStateOf(otpCode1.value.trim().isNotEmpty())
    }
    val otpCode2 = remember { mutableStateOf("") }
    val valid2 = remember(otpCode2) {
        mutableStateOf(otpCode2.value.trim().isNotEmpty())
    }
    val otpCode3 = remember { mutableStateOf("") }
    val valid3 = remember(otpCode3) {
        mutableStateOf(otpCode3.value.trim().isNotEmpty())
    }
    val otpCode4 = remember { mutableStateOf("") }
    val valid4 = remember(otpCode4) {
        mutableStateOf(otpCode4.value.trim().isNotEmpty())
    }
    val otpCode5 = remember { mutableStateOf("") }
    val valid5 = remember(otpCode5) {
        mutableStateOf(otpCode5.value.trim().isNotEmpty())
    }


    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        CircleTextField(
            modifier = Modifier,
            valueState = otpCode1,
            fieldHeight = 62.dp,
            fieldWidth = 62.dp,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
            onAction = KeyboardActions {
                if (!valid1.value) return@KeyboardActions

                Log.d(TAG, "OTPCodeInputFilled: code is ${otpCode1.value}")
                fullOtpCode.value += otpCode1.value

                Log.d(TAG, "TakeOneCode: full code is ${fullOtpCode.value}")
                keyboardController?.hide()

            }
        )

        CircleTextField(
            modifier = Modifier,
            valueState = otpCode2,
            fieldHeight = 62.dp,
            fieldWidth = 62.dp,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
            onAction = KeyboardActions {
                if (!valid2.value) return@KeyboardActions

                Log.d(TAG, "OTPCodeInputFilled: code is ${otpCode2.value}")
                fullOtpCode.value += otpCode2.value

                Log.d(TAG, "TakeOneCode: full code is ${fullOtpCode.value}")
                keyboardController?.hide()

            }
        )

        CircleTextField(
            modifier = Modifier,
            valueState = otpCode3,
            fieldHeight = 62.dp,
            fieldWidth = 62.dp,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
            onAction = KeyboardActions {
                if (!valid3.value) return@KeyboardActions

                Log.d(TAG, "OTPCodeInputFilled: code is ${otpCode3.value}")
                fullOtpCode.value += otpCode3.value

                Log.d(TAG, "TakeOneCode: full code is ${fullOtpCode.value}")
                keyboardController?.hide()

            }
        )

        CircleTextField(
            modifier = Modifier,
            valueState = otpCode4,
            fieldHeight = 62.dp,
            fieldWidth = 62.dp,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
            onAction = KeyboardActions {
                if (!valid4.value) return@KeyboardActions

                Log.d(TAG, "OTPCodeInputFilled: code is ${otpCode4.value}")
                fullOtpCode.value += otpCode4.value

                Log.d(TAG, "TakeOneCode: full code is ${fullOtpCode.value}")
                keyboardController?.hide()

            }
        )

        CircleTextField(
            modifier = Modifier,
            valueState = otpCode5,
            fieldHeight = 62.dp,
            fieldWidth = 62.dp,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
            onAction = KeyboardActions {
                if (!valid5.value) return@KeyboardActions

                Log.d(TAG, "OTPCodeInputFilled: code is ${otpCode5.value}")
                fullOtpCode.value += otpCode5.value

                Log.d(TAG, "TakeOneCode: full code is ${fullOtpCode.value}")
                keyboardController?.hide()

            }
        )
    }
    onCodeComplete(fullOtpCode.value.ifEmpty { "Empty" })


}
*/


@Composable
fun OTPCodeInputRow() {

    val (displayWidth, displayHeight, _) = AppUtils.screenHeightAndWidth(LocalContext.current)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val x = 0.14f
        val y = 0.08f
        val s = CircleShape


        TakeUserTextInputOTP(
            placeholderLbl = stringResource(id = R.string.otp_x_lbl),
            bgColor = Color.White,
            inputWidth = displayWidth.dp.times(x),
            inputHeight = displayHeight.dp.times(y),
            enabled = true,
            inputTextColor = Color.Black,
            capitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
            shape = s,
            singleLine = true,
            maxLine = 1,
            textEntered = {
                Log.d(TAG, "OTPCodeInputRow: $it")
            }
        )

        TakeUserTextInputOTP(
            placeholderLbl = stringResource(id = R.string.otp_x_lbl),
            bgColor = Color.White,
            inputWidth = displayWidth.dp.times(x),
            inputHeight = displayHeight.dp.times(y),
            enabled = true,
            inputTextColor = Color.Black,
            capitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
            shape = s,
            singleLine = true,
            maxLine = 1,
            textEntered = {
                Log.d(TAG, "OTPCodeInputRow: $it")
            }
        )

        TakeUserTextInputOTP(
            placeholderLbl = stringResource(id = R.string.otp_x_lbl),
            bgColor = Color.White,
            inputWidth = displayWidth.dp.times(x),
            inputHeight = displayHeight.dp.times(y),
            enabled = true,
            inputTextColor = Color.Black,
            capitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
            shape = s,
            singleLine = true,
            maxLine = 1,
            textEntered = {
                Log.d(TAG, "OTPCodeInputRow: $it")
            }
        )

        TakeUserTextInputOTP(
            placeholderLbl = stringResource(id = R.string.otp_x_lbl),
            bgColor = Color.White,
            inputWidth = displayWidth.dp.times(x),
            inputHeight = displayHeight.dp.times(y),
            enabled = true,
            inputTextColor = Color.Black,
            capitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
            shape = s,
            singleLine = true,
            maxLine = 1,
            textEntered = {
                Log.d(TAG, "OTPCodeInputRow: $it")
            }
        )

        TakeUserTextInputOTP(
            placeholderLbl = stringResource(id = R.string.otp_x_lbl),
            bgColor = Color.White,
            inputWidth = displayWidth.dp.times(x),
            inputHeight = displayHeight.dp.times(y),
            enabled = true,
            inputTextColor = Color.Black,
            capitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
            shape = s,
            singleLine = true,
            maxLine = 1,
            textEntered = {
                Log.d(TAG, "OTPCodeInputRow: $it")
            }
        )

        TakeUserTextInputOTP(
            placeholderLbl = stringResource(id = R.string.otp_x_lbl),
            bgColor = Color.White,
            inputWidth = displayWidth.dp.times(x),
            inputHeight = displayHeight.dp.times(y),
            enabled = true,
            inputTextColor = Color.Black,
            capitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
            shape = s,
            singleLine = true,
            maxLine = 1,
            textEntered = {
                Log.d(TAG, "OTPCodeInputRow: $it")
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TakeUserTextInputOTP(
    placeholderLbl: String,
    bgColor: Color,
    inputWidth: Dp,
    inputHeight: Dp,
    enabled: Boolean,
    inputTextColor: Color,
    capitalization: KeyboardCapitalization,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    shape: Shape,
    singleLine: Boolean,
    maxLine: Int,
    textEntered: (String) -> Unit
) {
    Log.d(TAG, "TakeUserTextInputOTP: called with place holder $placeholderLbl")
    val userInput = rememberSaveable { mutableStateOf("") }
    val valid = userInput.value.trim().isNotEmpty()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current



    TextField(
        value = userInput.value,
        onValueChange = {
            Log.d(TAG, "TakeUserTextInputOTP: called it == $it")
            if (it.isEmpty()) {
                userInput.value = ""
                focusManager.moveFocus(FocusDirection.Left)
            } else {
                userInput.value = it.last().toString()
                focusManager.moveFocus(FocusDirection.Right)
            }
        },
        modifier = Modifier
            .height(inputHeight)
            .width(inputWidth)
            .border(width = 3.dp, color = Color.Gray, shape = CircleShape)
            .onFocusChanged {
                textEntered(userInput.value.trim())
            },
        enabled = enabled,
        textStyle = TextStyle(
            fontSize = 23.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.poppins_regular))
        ),
        placeholder = {
            Icon(
                painter = painterResource(id = R.drawable.ic_asterisk3),
                contentDescription = null,
//                modifier = Modifier.size(200.dp)
            )
        },
        keyboardOptions = KeyboardOptions(
            capitalization = capitalization,
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions {
            if (!valid) return@KeyboardActions

            textEntered(userInput.value.trim())
            keyboardController?.hide()
        },
        singleLine = singleLine,
        maxLines = maxLine,
        shape = shape,
        colors = ExposedDropdownMenuDefaults.textFieldColors(
            focusedTextColor = inputTextColor,
            disabledIndicatorColor = bgColor,
            focusedPlaceholderColor = inputTextColor,
            disabledPlaceholderColor = inputTextColor,
            disabledTextColor = inputTextColor,
            focusedIndicatorColor = Color.Unspecified,
            unfocusedIndicatorColor = Color.Unspecified,
            cursorColor = inputTextColor,
            focusedContainerColor = bgColor,
        )
    )
}
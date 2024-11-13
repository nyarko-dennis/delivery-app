package dev.dna.deliveryapp.screens.register

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import dev.dna.deliveryapp.R
import dev.dna.deliveryapp.components.AlreadyUserOrNewUser
import dev.dna.deliveryapp.components.InputPhoneNumber
import dev.dna.deliveryapp.navigation.Screens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import dev.dna.deliveryapp.screens.anything.showToastMessage
import dev.dna.deliveryapp.utils.AppColors
import dev.dna.deliveryapp.utils.AppUtils

private const val TAG = "RegisterScreen"


@Composable
fun RegisterScreen(
    navController: NavController,
    registerViewModel: RegisterAndLoginViewModel = viewModel()
) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White, shadowElevation = 0.dp) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TakeUserInfo(navController, registerViewModel = registerViewModel)

            ColumnDivider()

            AlternateSignUpOptions(navController, registerViewModel)

            AlreadyUserOrNewUser(navController, isNewUser = false)
        }
    }
}


@Composable
fun ColumnDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.width(120.dp),
            thickness = 1.5.dp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = "or continue with")
        Spacer(modifier = Modifier.width(16.dp))
        HorizontalDivider(
            modifier = Modifier.width(120.dp),
            thickness = 1.5.dp,
            color = Color.Black
        )
    }
}

@Composable
fun TakeUserInfo(navController: NavController, registerViewModel: RegisterAndLoginViewModel) {
    val context = LocalContext.current
    val phoneNumber = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val fullName = remember { mutableStateOf("") }
    val showEmptyFieldDialog = remember { mutableStateOf(false) }

    remember(phoneNumber) {
        mutableStateOf(phoneNumber.value.trim().length == 10)
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val (_, getDisplayHeight, _) = AppUtils.screenHeightAndWidth(context)
    val focusManager = LocalFocusManager.current


    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.sign_up_heading),
            modifier = Modifier,
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.poppins_semibold))
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        // taking the full name of the user
        InputField(
            valueState = fullName,
            labelId = "Full Name",
            fieldHeight = getDisplayHeight.dp.times(0.085f),
            capitalization = KeyboardCapitalization.Words,
            onFocusChanged = {
                if (fullName.value.trim().isNotEmpty()) {
                    registerViewModel.isFullNameValid(fullName.value.trim(), context)
                }
            },
            onAction = KeyboardActions {
                focusManager.moveFocus(FocusDirection.Down)
//                keyboardController?.hide()
            }
        )
        Spacer(modifier = Modifier.height(8.dp))


        // taking email
        InputField(
            valueState = email,
            labelId = "Email",
            keyboardType = KeyboardType.Email,
            fieldHeight = getDisplayHeight.dp.times(0.085f),
            capitalization = KeyboardCapitalization.None,
            onFocusChanged = {
                if (email.value.trim().isNotEmpty()) {
                    registerViewModel.isEmailValid(email.value.trim(), context)
                }
            },
            onAction = KeyboardActions {
                focusManager.moveFocus(FocusDirection.Down)
//                keyboardController?.hide()
            }
        )
        Spacer(modifier = Modifier.height(8.dp))


        // taking phone number
        InputPhoneNumber(
            phoneNumber = phoneNumber,
            height = getDisplayHeight.dp.times(0.085f),
            onFocusChanged = {
                if (phoneNumber.value.trim().isNotEmpty()) {
                    registerViewModel.isPhoneNumberValid(phoneNumber.value.trim(), context)
                }
            },
            onAction = KeyboardActions {
                keyboardController?.hide()
            })
        Spacer(modifier = Modifier.height(16.dp))
        SignInAndSignOptions(
            null,
            stringResource(id = R.string.sign_up_lbl),
            bgColor = AppColors.green,
            height = getDisplayHeight.dp.times(0.085f),
            navController = navController,
            onClicked = {
                showEmptyFieldDialog.value = email.value.trim().isEmpty() || fullName.value.trim()
                    .isEmpty() || phoneNumber.value.trim().isEmpty()
                if (!showEmptyFieldDialog.value) {
                    navController.navigate(Screens.OTPCodeScreen.route)
                }
            }
        )

        // dialog
        EmptyInputFieldDialog(showDialog = showEmptyFieldDialog)
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun EmptyInputFieldDialog(showDialog: MutableState<Boolean>) {
    val scope = rememberCoroutineScope()


    if (showDialog.value) {
        scope.launch {
            delay(2500)
            showDialog.value = false
        }
        AnimatedVisibility(
            showDialog.value,
            enter = fadeIn(tween(300, easing = LinearOutSlowInEasing)),
            exit = scaleOut(tween(200, easing = FastOutLinearInEasing))
        ) {
            Dialog(
                onDismissRequest = { showDialog.value = false },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true
                )
            ) {
                OnInputErrorMsg(stringResource(id = R.string.inputs_not_empty_lbl))
            }
        }
    }
}

@Composable
fun OnInputErrorMsg(msg: String) {
    Surface(
        modifier = Modifier
            .height(48.dp)
            .width(150.dp),
        color = Color.White,
        shape = RoundedCornerShape(15)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = msg,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    color = Color.Red.copy(0.55f)
                )
            )
        }
    }
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    fieldHeight: Dp = 62.dp,
    isSingleLine: Boolean = true,
    onFocusChanged: () -> Unit = {},
    capitalization: KeyboardCapitalization = KeyboardCapitalization.Sentences,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    var animatedVisibility by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(fieldHeight),
        shape = CircleShape,
        border = BorderStroke(2.dp, color = AppColors.green),

        ) {

        val (_, _, screenArea) = AppUtils.screenHeightAndWidth(LocalContext.current)

        TextField(
            modifier = modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 8.dp)
                .onFocusChanged {
                    if (!it.isFocused) {
                        onFocusChanged()
                    }
                },
            value = valueState.value,
            onValueChange = {
                valueState.value = it
                animatedVisibility = it.trim().isNotEmpty()
            },
            placeholder = {
                Text(
                    text = labelId, textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = AppColors.green,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                )
            },
            singleLine = isSingleLine,
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = AppColors.green,
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = capitalization,
                keyboardType = keyboardType,
                imeAction = imeAction,
                autoCorrect = !labelId.contains("Email", ignoreCase = true)
            ),
            keyboardActions = onAction,
            colors = textFieldColors(
                focusedTextColor = AppColors.green,
                focusedContainerColor = Color.White,
            ),
            maxLines = 1,
            shape = CircleShape,
            trailingIcon = {
                AnimatedVisibility(animatedVisibility, enter = fadeIn(), exit = fadeOut()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "Clear text",
                        modifier = Modifier
                            .size(screenArea.dp.times(0.00008f))
                            .background(color = AppColors.lightLightGreen, shape = CircleShape)
                            .clickable {
                                valueState.value = ""
                                animatedVisibility = false
                            },
                        tint = Color.White
                    )
                }
            }
        )
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LoadingProgressIndicator(show: MutableState<Boolean>) {

    if (show.value) {
        Dialog(
            onDismissRequest = { show.value = false },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            AnimatedVisibility(
                show.value, enter = slideInHorizontally(
                    initialOffsetX = { 500 },
                    animationSpec = tween(500, easing = EaseInElastic)
                ) + scaleIn(animationSpec = tween(500, easing = EaseInElastic)),
                exit = slideOutHorizontally(
                    targetOffsetX = { -200 },
                    animationSpec = tween(500)
                ) + scaleOut(animationSpec = tween(500))
            ) {
                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = RoundedCornerShape(10),
                    color = Color.White,
                    shadowElevation = 8.dp
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize(0.90f)
                            .padding(8.dp),
                        color = AppColors.lightLightGreen
                    )
                }
            }
        }
    }
}


@Composable
fun AlternateSignUpOptions(
    navController: NavController,
    registerViewModel: RegisterAndLoginViewModel = viewModel()
) {
    val context = LocalContext.current
    val showLoading = remember { mutableStateOf(false) }
//    val scope = rememberCoroutineScope()


    LoadingProgressIndicator(show = showLoading)

    val result = remember {
        mutableStateOf<ActivityResult?>(null)
    }
    val googleSignInLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { res: ActivityResult ->
            result.value = res
        }
    val googleSignInClient = registerViewModel.getGoogleLoginAuth(
        context,
        stringResource(id = R.string.my_web_client_id)
    )
    if (result.value != null) {
        showLoading.value = false
        if (result.value!!.resultCode == Activity.RESULT_OK) {
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(
                    result.value!!.data
                )

            registerViewModel.handleSignInResult(task, context) { isSuccess ->
                if (isSuccess) {
                    navController.navigate(Screens.PhoneNumberScreen.route)
                } else {
                    showToastMessage(context, "SignUp failed")
                }
            }
        }
    }


    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        // login with google
        SignInAndSignOptions(
            icon = painterResource(id = R.drawable.google_login),
            bgColor = AppColors.google,
            navController = navController,
            onClick = {
                showLoading.value = true
                googleSignInLauncher.launch(googleSignInClient.signInIntent)
            }
        )

        // login with google
        SignInAndSignOptions(
            icon = painterResource(id = R.drawable.facebook_login),
            bgColor = AppColors.facebook,
            navController = navController,
            animationDelayTime = 100,
            onClick = {

            }
        )

        // login with google
        SignInAndSignOptions(
            icon = painterResource(id = R.drawable.apple_login),
            bgColor = AppColors.apple,
            navController = navController,
            onClick = {

            }
        )
    }
}

@Composable
private fun SignInAndSignOptions(
    icon: Painter,
    bgColor: Color,
    navController: NavController,
    animationDelayTime: Int = 0,
    onClick: () -> Unit
) {
    val (_, _, screenArea) = AppUtils.screenHeightAndWidth(LocalContext.current)
//    Log.d(TAG, "SignInAndSignOptions: $navController")

    val distance = with(LocalDensity.current) { 10.dp.toPx() }
//    Log.d(TAG, "SignInAndSignOptions: distance is $distance")
    val circles = remember { Animatable(initialValue = 0f) }

    val circlesValues = circles.value

    LaunchedEffect(key1 = circles, block = {
        delay(animationDelayTime * 50L)
        circles.animateTo(
            targetValue = 1f, animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = 1500
                    0.0f at 0 using LinearOutSlowInEasing
                    1.0f at 750 using LinearOutSlowInEasing
                    0.0f at 750 using LinearOutSlowInEasing
//                    0.0f at 0 with LinearOutSlowInEasing
                },
                repeatMode = RepeatMode.Reverse
            )
        )
    })

    Button(
        onClick = {
            onClick()
        },
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
            focusedElevation = 0.dp
        ),
        modifier = Modifier
            .size(screenArea.dp.times(0.00022f))
            .graphicsLayer {
                translationY = -circlesValues * distance
            },
        shape = RoundedCornerShape(20),
        colors = ButtonDefaults.buttonColors(
            containerColor = bgColor,
            disabledContainerColor = Color.White
        ),
        contentPadding = PaddingValues(4.dp)
    ) {
        Image(painter = icon, contentDescription = "", modifier = Modifier.fillMaxSize())
    }
}
package dev.dna.deliveryapp.screens.welcome

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import dev.dna.deliveryapp.utils.AppColors

private const val TAG = "WelcomeScreen"

private val imageList =
    listOf(R.drawable.welcome_page1, R.drawable.welcome_page2, R.drawable.welcome_page3)
private val nextOrPreviews = listOf("Next", "Previous")

private val welcomeMsgList =
    listOf(
        R.string.welcome_msg1,
        R.string.welcome_msg1,
        R.string.welcome_msg1,
        R.string.welcome_msg1
    )

@Composable
fun WelcomeScreen(navController: NavController) {
    Log.d(TAG, "WelcomeScreen: called")
    val currentPageState = rememberSaveable {
        mutableStateOf(Pages.PAGE_1)
    }

    WelcomePage(navController = navController, currentPage = currentPageState.value) {
        currentPageState.value = it
    }

}

@Composable
fun WelcomePage(
    navController: NavController,
    currentPage: Pages,
    nextPages: (Pages) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 42.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxHeight(.85f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(
                        id = when (currentPage) {
                            Pages.PAGE_1 -> imageList[0]
                            Pages.PAGE_2 -> imageList[1]
                            Pages.PAGE_3 -> imageList[2]
                        }
                    ),
                    contentDescription = "welcome page",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.6f)
                )
                Text(
                    text = stringResource(
                        id = when (currentPage) {
                            Pages.PAGE_1 -> welcomeMsgList[0]
                            Pages.PAGE_2 -> welcomeMsgList[1]
                            Pages.PAGE_3 -> welcomeMsgList[2]
                        }
                    ),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                )
            }

            NextAndPreviousRow(navController = navController, currentPage = currentPage, nextPages)
        }
    }
}

@Composable
fun NextAndPreviousRow(
    navController: NavController,
    currentPage: Pages,
    nextPages: (Pages) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val tmp = currentPage != Pages.PAGE_1
        if (tmp) {
            NextOrPreviousButton(
                title = nextOrPreviews[1],
                navController,
                currentPage,
                disabled = currentPage != Pages.PAGE_1,
                nextPages
            )
        } else {
            Box(modifier = Modifier.width(100.dp))
        }


        Row(modifier = Modifier) {
            imageList.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .size(15.dp)
                        .clip(CircleShape)
                        .background(
                            color = if (index == currentPage.ordinal) AppColors.lightLightGreen else AppColors.grayish,
                            shape = CircleShape
                        )
                )
                if (index < 2) {
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
        }

        NextOrPreviousButton(
            title = nextOrPreviews[0],
            navController,
            currentPage,
            disabled = true,
            nextPages
        )
    }
}

@Composable
fun NextOrPreviousButton(
    title: String,
    navController: NavController,
    currentPage: Pages,
    disabled: Boolean,
    nextPages: (Pages) -> Unit
) {
    Button(
        onClick = {
            when (currentPage) {
                Pages.PAGE_1 -> {
                    nextPages(Pages.PAGE_2)
                }
                Pages.PAGE_2 -> {
                    if (title == "Next") {
                        nextPages(Pages.PAGE_3)
                    } else {
                        nextPages(Pages.PAGE_1)
                    }
                }
                Pages.PAGE_3 -> {
                    if (title == "Next") {
                        // TODO navController
                        navController.navigate(Screens.HomeScreen.route)
                    } else {
                        nextPages(Pages.PAGE_2)
                    }
                }
            }
        },
        enabled = disabled,
        modifier = Modifier
            .width(110.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
            focusedElevation = 0.dp
        ),
        shape = CircleShape,
        colors = buttonColors(
            containerColor = AppColors.lightLightGreen
        )
    ) {
        Text(
            text = title,
            color = AppColors.grayish,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
        )
    }
}

enum class Pages {
    PAGE_1,
    PAGE_2,
    PAGE_3
}
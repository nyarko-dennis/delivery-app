package dev.dna.deliveryapp.screens.drop_off

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import dev.dna.deliveryapp.screens.food.OgaTopAppBar
import dev.dna.deliveryapp.utils.AppColors

private val choice = listOf(R.string.hand_it_to_me_lbl, R.string.leave_it_at_the_door_lbl)
private val defaultChoice = R.string.leave_it_at_the_door_lbl

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DropOffOptionsScreen(navController: NavController) {
    Scaffold(topBar = {
        OgaTopAppBar(
            title = stringResource(id = R.string.drop_off_lbl),
            bgColor = Color.White,
            iconBgColor = AppColors.green,
            isCart = null,
            icon = painterResource(id = R.drawable.ic_close),
            navController = navController,
            iconClicked = { navController.popBackStack() },
            onCartOrHelpIcon = {}
        )
    }) {
        val answerState = remember {
            mutableStateOf<Int?>(null)
        }
        var chosenAnswer by remember {
            mutableStateOf(choice[1])
        }

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(id = R.string.drop_off_message), style = TextStyle(
                        fontSize = 15.sp,
                        textAlign = TextAlign.Left,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))
                choice.forEachIndexed { index, option ->
                    answerState.value = option
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { chosenAnswer = choice[index] },
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = answerState.value == chosenAnswer,
                            onClick = { chosenAnswer = choice[index] },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = AppColors.green,
                                unselectedColor = Color.Black.copy(0.67f)
                            )
                        )

                        Text(
                            text = stringResource(id = option),
                            style = TextStyle(
                                fontSize = 13.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular))
                            )
                        )
                    }
                }

            }
        }
    }
}
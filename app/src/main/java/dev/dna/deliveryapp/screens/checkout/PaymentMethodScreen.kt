package dev.dna.deliveryapp.screens.checkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.dna.deliveryapp.R
import dev.dna.deliveryapp.utils.AppUtils


@Composable
fun PaymentOptionBottomSheet() {
    val (_, getDisplayHeight, _) = AppUtils.screenHeightAndWidth(LocalContext.current)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(getDisplayHeight.dp.times(0.35f))
            .padding(top = 12.dp, end = 12.dp),
        color = Color.White,
        shape = RoundedCornerShape(topStartPercent = 8, topEndPercent = 8),
//        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.choose_payment_mthd),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            )

            PaymentOptions(icon = R.drawable.momo, text = "Mobile Money")
            PaymentOptions(icon = R.drawable.cash, text = "Pay with cash")

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                thickness = 1.5.dp, color = Color.Gray.copy(0.67f)
            )
            PaymentOptions(icon = R.drawable.credit_card, text = "Add new card")
        }
    }
}

@Composable
fun PaymentOptions(icon: Int, text: String) {
    val (_, getDisplayHeight, _) = AppUtils.screenHeightAndWidth(LocalContext.current)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(getDisplayHeight.dp.times(0.085f))
            .padding(start = 16.dp, end = 16.dp)
            .clickable { },
        horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = text,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.18f)
                .clickable { },
            contentScale = ContentScale.FillBounds
        )

        Spacer(modifier = Modifier.width(32.dp))

        Text(
            text = text, style = TextStyle(
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 18.sp,
                textAlign = TextAlign.Left
            )
        )
    }
}

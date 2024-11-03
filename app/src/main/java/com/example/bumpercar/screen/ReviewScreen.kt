package com.example.bumpercar.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.bumpercar.R
import com.example.bumpercar.component.MapScreen
import com.example.bumpercar.component.QuoteCard
import com.example.bumpercar.component.ReviewTopBar
import com.example.bumpercar.data.Quote
import com.example.bumpercar.ui.theme.mainHeadLineColor
import com.example.bumpercar.ui.theme.mainSubLineColor
import com.example.bumpercar.viewmodel.MainViewModel

@Composable
fun ReviewScreen(
    hospitalName: String,
    hospitalPhone: String,
    hospitalLocation: String,
    navHostController: NavHostController,
    viewModel: MainViewModel
) {

    val topMainTextSize = 36.5.sp

    val topSubTextSize = 29.sp // 7.5 차이

    val context = LocalContext.current

    val reviewText = viewModel.reviewData.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        ReviewTopBar(
            onClick = {navHostController.popBackStack()}
        )

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontSize = topMainTextSize
                        )
                    ) {
                        append("${hospitalName}\n")
                    }
                    withStyle(
                        SpanStyle(
                            fontSize = topSubTextSize,
                            color = mainSubLineColor
                        )
                    ) {
                        append(hospitalPhone)
                    }
                },
                fontFamily = FontFamily(Font(R.font.notosans_bold)),
                color = mainHeadLineColor,
                lineHeight = topSubTextSize * 1.5
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                modifier = Modifier.padding(start = 10.dp,bottom = 5.dp),
                text = "리뷰 요약",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.notosans_bold)),
                    fontSize = 21.sp,
                    lineHeight = 28.sp,
                    color = mainHeadLineColor
                ),
            )
            Log.d("ReviewScreen", "Review: ${reviewText.value.answer}")
            if (reviewText.value.answer.isNotEmpty()) {
                QuoteCard(
                    text = reviewText.value.answer,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                modifier = Modifier.padding(start = 10.dp,bottom = 5.dp),
                text = "위치",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.notosans_bold)),
                    fontSize = 21.sp,
                    lineHeight = 28.sp,
                    color = mainHeadLineColor
                ),
            )
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.Red)
            ) {
                MapScreen(
                    context = context,
                    address = hospitalLocation
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = hospitalLocation,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.notosans_regular)),
                    color = mainSubLineColor,
                    fontSize = 12.sp
                )
            )
        }
    }
}

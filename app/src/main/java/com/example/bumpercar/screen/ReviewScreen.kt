package com.example.bumpercar.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bumpercar.R
import com.example.bumpercar.component.BumpercarTopBar
import com.example.bumpercar.ui.theme.mainBlueColor
import com.example.bumpercar.ui.theme.mainHeadLineColor
import com.example.bumpercar.ui.theme.mainSubLineColor

@Composable
fun ReviewScreen() {
    val topMainTextSize = 26.5.sp

    val topSubTextSize = 19.sp // 7.5 차이


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BumpercarTopBar()

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ){
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontSize = topMainTextSize
                        )
                    ) {
                        append("우리가 요약한 한방병원 후기\n")
                    }
                    withStyle(
                        SpanStyle(
                            fontSize = topSubTextSize,
                            color = mainSubLineColor
                        )
                    ) {
                        append("간편하게 요약된 후기를 이용해 보세요.")
                    }
                },
                fontFamily = FontFamily(Font(R.font.notosans_bold)),
                color = mainHeadLineColor,
                lineHeight = topSubTextSize * 1.5
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
package com.example.bumpercar.screen

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bumpercar.R

private val NotoSans = FontFamily(
    Font(R.font.notosans_bold)
)

/*val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = NotoSans,
        fontSize = 8.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )
)*/


@Preview(showBackground = true)
@Composable
fun Test() {
    Text(
        text = "반가워",
        style = TextStyle(
            fontFamily = NotoSans,
            fontSize = 8.sp,
        ),
    )
}
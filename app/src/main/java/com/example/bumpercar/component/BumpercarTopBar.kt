package com.example.bumpercar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bumpercar.R
import com.example.bumpercar.ui.theme.mainBlueColor

@Composable
fun BumpercarTopBar(
    modifier: Modifier = Modifier,
    title: String = "Bumpercar",
    icon: ImageVector? = null,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.notosans_bold)),
                    fontSize = 21.sp,
                    lineHeight = 28.sp,
                    color = mainBlueColor
                ),
            )

            if(icon != null) {
                Icon(
                    modifier = Modifier.clickable(
                        onClick = onClick
                    ),
                    imageVector = icon,
                    contentDescription = "아이콘 모양",
                    tint = mainBlueColor
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    BumpercarTopBar()
}
package com.example.bumpercar.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bumpercar.R
import com.example.bumpercar.ui.theme.mainBoxBackgroundColor
import com.example.bumpercar.ui.theme.mainBoxContentColor
import com.example.bumpercar.ui.theme.mainBoxEmergencyColor

@Composable
fun ContentBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .background(mainBoxBackgroundColor)
            .padding(horizontal = 10.dp, vertical = 10.dp )
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun ContentBoxPreview() {
    ContentBox {
        Column(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    modifier = Modifier

                        .size(20.dp),
                    painter = painterResource(id = R.drawable.ic_emergency_24),
                    contentDescription = ""
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(start = 1.dp),
                        text = "즉시 비상등을 켜기",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.notosans_medium)),
                            fontSize = 13.sp,
                            lineHeight = 22.sp,
                            letterSpacing = (-2).sp * 0.006,
                            color = Color.Black
                        )
                    )
                    Text(
                        text = "사고 직후 차량의 비상등을 빠르게 켜서 후속 차량에게 사고 발생 사실을 알립니다.\n" +
                                "비상등은 후방 차량들이 속도를 줄이고 주의를 기울이게 하는 중요한 신호입니다.",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.notosans_regular)),
                            fontSize = 8.sp,
                            lineHeight = 13.sp,
                            letterSpacing = (-2).sp * 0.006,
                            color = mainBoxContentColor
                        )
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(20.dp),
                    painter = painterResource(id = R.drawable.ic_car_24),
                    contentDescription = ""
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(start = 1.dp),
                        text = "차량을 안전한 곳으로 이동",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.notosans_medium)),
                            fontSize = 13.sp,
                            lineHeight = 22.sp,
                            letterSpacing = (-2).sp * 0.006,
                            color = Color.Black
                        )
                    )
                    Text(
                        text = "사고 차량을 도로 밖의 안전한 곳으로 옮겨 후속 차량의 통행을 방해하지 않도록 합니다. \n" +
                                "차량을 옮길 수 없는 경우에는 후속 조치에 더 많은 주의를 기울여야 합니다.",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.notosans_regular)),
                            fontSize = 8.sp,
                            lineHeight = 13.sp,
                            letterSpacing = (-2).sp * 0.006,
                            color = mainBoxContentColor
                        )
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(20.dp),
                    painter = painterResource(id = R.drawable.ic_voice_24),
                    contentDescription = ""
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(start = 1.dp),
                        text = "긴급 구조 요청",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.notosans_medium)),
                            color = Color.Black,
                            fontSize = 13.sp
                        ),
                        lineHeight = 22.sp,
                        letterSpacing = (-2).sp * 0.006,

                        )
                    Text(
                        text = "사고 상황에 맞게 경찰이나 긴급 구조대에 연락하여 사고 처리를 요청합니다. \n" +
                                "정확한 위치와 사고 상황을 설명하면 사고 처리와 후속 사고 예방에 도움이 됩니다.",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.notosans_regular)),
                            fontSize = 8.sp,
                            lineHeight = 13.sp,
                            letterSpacing = (-2).sp * 0.006,
                            color = mainBoxContentColor
                        ),
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
fun ContentBoxPreview2() {
    ContentBox {
        Column {
            Text(
                text = "보험사 별 긴급 번호",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.notosans_bold)),
                    color = mainBoxEmergencyColor,
                    fontSize = 14.sp
                ),
            )

            Spacer(modifier = Modifier.height(5.dp))

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                EmergencyBox(
                    image = R.drawable.im_hyundai,
                    phoneNumber = "1588-5656"
                )
                EmergencyBox(
                    image = R.drawable.im_hanwha,
                    phoneNumber = "1566-800"
                )
                EmergencyBox(
                    image = R.drawable.im_lotte,
                    phoneNumber = "1588-3344"
                )
                EmergencyBox(
                    image = R.drawable.im_mg,
                    phoneNumber = "1588-5959"
                )
                EmergencyBox(
                    image = R.drawable.im_heungkuk,
                    phoneNumber = "1688-1688"
                )
                EmergencyBox(
                    image = R.drawable.im_samsung,
                    phoneNumber = "1588-5114"
                )
                EmergencyBox(
                    image = R.drawable.im_meritz,
                    phoneNumber = "1566-7711"
                )
                EmergencyBox(
                    image = R.drawable.im_kb,
                    phoneNumber = "1588-0114"
                )
                EmergencyBox(
                    image = R.drawable.im_thek,
                    phoneNumber = "1566-3000"
                )
                EmergencyBox(
                    image = R.drawable.im_db,
                    phoneNumber = "1588-0100"
                )
                EmergencyBox(
                    image = R.drawable.im_axa,
                    phoneNumber = "1566-1566"
                )
                EmergencyBox(
                    image = R.drawable.im_carrot,
                    phoneNumber = "1566-0300"
                )
            }
        }
    }
}
package com.example.bumpercar.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.example.bumpercar.component.BumpercarTopBar
import com.example.bumpercar.component.ContentBox
import com.example.bumpercar.component.EmergencyBox
import com.example.bumpercar.ui.theme.mainBoxContentColor
import com.example.bumpercar.ui.theme.mainBoxEmergencyColor
import com.example.bumpercar.ui.theme.mainHeadLineColor
import com.example.bumpercar.ui.theme.mainSubLineColor

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainScreen() {

   /* val topMainTextSize = 19.5.sp
    val topSubTextSize = 12.sp // 7.5 차이*/

    val topMainTextSize = 26.5.sp
    val topSubTextSize = 19.sp // 7.5 차이

    /*val firstBoxTextSize = 13.sp
    val firstBoxContentTextSize = 8.sp // 5 차이*/

    val firstBoxTextSize = 20.sp
    val firstBoxContentTextSize = 12.sp // 5 차이

    /*val secondBoxTextSize = 9.sp
    val secondBoxContentTextSize = 8.sp // 1 차이*/

    val secondBoxTextSize = 16.sp
    val secondBoxContentTextSize = 15.sp // 1 차이

    //val thirdBoxTextSize = 14.sp
    val thirdBoxTextSize = 21.sp


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        BumpercarTopBar(onClick = { /*TODO: 버튼 클릭 처리*/ })
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(19.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontSize = topMainTextSize
                        )
                    ) {
                        append("혹시 교통사고 나셨나요?\n")
                    }
                    withStyle(
                        SpanStyle(
                            fontSize = topSubTextSize,
                            color = mainSubLineColor
                        )
                    ) {
                        append("교통사고가 나셨다면 2차 사고 예방이 제일 중요합니다.")
                    }
                },
                fontFamily = FontFamily(Font(R.font.notosans_bold)),
                color = mainHeadLineColor,
                lineHeight = topSubTextSize * 1.5
            )

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
                                    fontSize = firstBoxTextSize,
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
                                    fontSize = firstBoxContentTextSize,
                                    lineHeight = firstBoxContentTextSize * 1.3,
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
                                    fontSize = firstBoxTextSize,
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
                                    fontSize = firstBoxContentTextSize,
                                    lineHeight = firstBoxContentTextSize * 1.3,
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
                                    fontSize = firstBoxTextSize
                                ),
                                lineHeight = 22.sp,
                                letterSpacing = (-2).sp * 0.006,

                                )
                            Text(
                                text = "사고 상황에 맞게 경찰이나 긴급 구조대에 연락하여 사고 처리를 요청합니다. \n" +
                                        "정확한 위치와 사고 상황을 설명하면 사고 처리와 후속 사고 예방에 도움이 됩니다.",
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.notosans_regular)),
                                    fontSize = firstBoxContentTextSize,
                                    lineHeight = firstBoxContentTextSize * 1.3,
                                    letterSpacing = (-2).sp * 0.006,
                                    color = mainBoxContentColor
                                ),
                            )
                        }
                    }
                }
            }

            ContentBox {
                Column {
                    Text(
                        text = "긴급 구조 요청 번호",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.notosans_bold)),
                            color = mainBoxEmergencyColor
                        ),
                        fontSize = thirdBoxTextSize
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    color = Color.Black,
                                    fontSize = secondBoxTextSize
                                )
                            ) {
                                append("고속도로 순찰대\n")
                            }
                            withStyle(
                                SpanStyle(
                                    color = mainBoxContentColor,
                                    fontSize = secondBoxContentTextSize,
                                )
                            ) {
                                append(
                                    "제 1지구대(경기남부 경찰청) : 031-888-2254\n" +
                                            "제 2지구대(충청남도 경찰청) : 041-336-2154\n" +
                                            "제 3지구대(경기북도 경찰청) : 054-824-7301\n" +
                                            "제 5지구대(전라남도 경찰청) : 061-392-4048\n" +
                                            "제 6지구대(경상남도 경찰청) : 055-255-2454"
                                )
                            }
                        },
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.notosans_medium)),
                            color = mainBoxEmergencyColor
                        ),
                        lineHeight = 19.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    color = Color.Black,
                                    fontSize = secondBoxTextSize
                                )
                            ) {
                                append("도로교통공단\n")
                            }
                            withStyle(
                                SpanStyle(
                                    color = mainBoxContentColor,
                                    fontSize = secondBoxContentTextSize,
                                )
                            ) {
                                append("한국도로교통공단 고객센터 : 1577-1120")
                            }
                        },
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.notosans_medium)),
                            color = mainBoxEmergencyColor
                        ),
                        lineHeight = 19.sp
                    )
                }
            }

            ContentBox {
                Column {
                    Text(
                        text = "보험사 별 긴급 번호",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.notosans_bold)),
                            color = mainBoxEmergencyColor,
                            fontSize = thirdBoxTextSize
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
                            phoneNumber = "1566-8000"
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
                            phoneNumber = "1544-0114"
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
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
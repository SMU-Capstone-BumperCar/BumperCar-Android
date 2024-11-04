package com.example.bumpercar.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bumpercar.R
import com.example.bumpercar.component.BumpercarTopBar
import com.example.bumpercar.ui.theme.mainBlueColor
import com.example.bumpercar.ui.theme.mainHeadLineColor
import com.example.bumpercar.ui.theme.mainSubLineColor
import com.example.bumpercar.ui.theme.textFieldBackGroundColor
import com.example.bumpercar.ui.theme.textYellowColor
import com.example.bumpercar.viewmodel.MainViewModel

@Composable
fun ChatMainScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {

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
                        append("분쟁심의위원회를 진행하라고 하시나요?\n")
                    }
                    withStyle(
                        SpanStyle(
                            fontSize = topSubTextSize,
                            color = mainSubLineColor
                        )
                    ) {
                        append("아래 챗봇 시스템과 함께 빠르게 과실 비율에 대해서 알아보죠")
                    }
                },
                fontFamily = FontFamily(Font(R.font.notosans_bold)),
                color = mainHeadLineColor,
                lineHeight = topSubTextSize * 1.5
            )

            Spacer(modifier = Modifier.height(16.dp))


            Column {
                ChatBotBox(
                    onClickListener = {
                        navHostController.navigate("chat")
                        mainViewModel.resetChatMessages()
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontSize = 16.sp
                        )
                    ) {
                        append("해당 서비스를 이용하고 싶으시면 ")
                    }
                    withStyle(
                        SpanStyle(
                            fontSize = 18.sp,
                            color = mainBlueColor,
                            fontFamily = FontFamily(Font(R.font.notosans_black))
                        )
                    ) {
                        append("전송")
                    }
                    withStyle(
                        SpanStyle(
                            fontSize = 16.sp
                        )
                    ) {
                        append(" 버튼을 눌러 주세요!")
                    }
                },
                fontFamily = FontFamily(Font(R.font.notosans_medium)),
                color = mainHeadLineColor,
                lineHeight = topSubTextSize * 1.5,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            
            Spacer(modifier = Modifier.weight(1f))

            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = "해당 챗봇은 분쟁심의위원회 데이터를 학습하였습니다. 챗봇은 확실하지 않습니다.\n참고용으로 사용해 주세요. 선택은 항상 자기 자신의 몫입니다.",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.notosans_regular)),
                    color = mainSubLineColor,
                    fontSize = 12.sp
                )
            )
        }
    }
}

@Composable
private fun ChatBotBox(
    modifier: Modifier = Modifier,
    onClickListener: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, mainBlueColor, RoundedCornerShape(12.dp))
            .background(textFieldBackGroundColor)
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 18.dp)
                    .padding(top = 18.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                topStart = 2.dp,
                                topEnd = 12.dp,
                                bottomStart = 12.dp,
                                bottomEnd = 12.dp
                            )
                        )
                        .background(Color.White)
                        .border(
                            1.dp,
                            Color.White,
                            RoundedCornerShape(
                                topStart = 2.dp,
                                topEnd = 12.dp,
                                bottomStart = 12.dp,
                                bottomEnd = 12.dp
                            )
                        )
                        .padding(vertical = 14.dp, horizontal = 22.dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("AI 기반의 ")
                            withStyle(SpanStyle(color = mainBlueColor)) { append("챗봇") }
                            append("에게 빠르게 분심위의 과실 비율에 대해서 ")
                            withStyle(SpanStyle(color = mainBlueColor)) { append("대화") }
                            append("를 해보실래요?\n")
                            withStyle(SpanStyle(color = mainBlueColor)) { append("사고 상황") }
                            append("에 대해서 확실하게 말해주면 됩니다!\n예상하는 과실 비율과 판결 사례까지 같이 알아봐요")
                        },
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.notosans_regular)),
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 18.dp)
                    .padding(top = 18.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                topStart = 12.dp,
                                topEnd = 2.dp,
                                bottomStart = 12.dp,
                                bottomEnd = 12.dp
                            )
                        )
                        .background(mainBlueColor)
                        .border(
                            1.dp,
                            mainBlueColor,
                            RoundedCornerShape(
                                topStart = 12.dp,
                                topEnd = 2.dp,
                                bottomStart = 12.dp,
                                bottomEnd = 12.dp
                            )
                        )
                        .padding(vertical = 14.dp, horizontal = 22.dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(color = textYellowColor)) { append("신호등") }
                            append("이 있는")
                            withStyle(SpanStyle(color = textYellowColor)) { append(" 교차로") }
                            append("에서 내가 정상 신호에 갔지만 사고가 발생 했어요!\n해당 사고에 대한 ")
                            withStyle(SpanStyle(color = textYellowColor)) { append("과실 비율") }
                            append("을 알려주시겠어요?")
                        },
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.notosans_regular)),
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 18.dp)
                    .padding(top = 18.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                topStart = 2.dp,
                                topEnd = 12.dp,
                                bottomStart = 12.dp,
                                bottomEnd = 12.dp
                            )
                        )
                        .background(Color.White)
                        .border(
                            1.dp,
                            Color.White,
                            RoundedCornerShape(
                                topStart = 2.dp,
                                topEnd = 12.dp,
                                bottomStart = 12.dp,
                                bottomEnd = 12.dp
                            )
                        )
                        .padding(vertical = 14.dp, horizontal = 22.dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("해당 사고는 ")
                            withStyle(SpanStyle(color = mainBlueColor)) { append("(사고상황 설명)") }
                            append(" 으로 인해 ")
                            withStyle(SpanStyle(color = mainBlueColor)) { append("과실 비율") }
                            append("은 100:0 으로 예상됩니다.\n하지만 ")
                            withStyle(SpanStyle(color = mainBlueColor)) { append("(추가 과실 여부)") }
                            append(" 에 따라 과실 비율이 추가되거나 감소할 수 있습니다.\n더 궁금한 게 있으면 저를 이용해 보세요.")
                        },
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.notosans_regular)),
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    )
                }
            }

            Box(
                modifier = Modifier
                    .background(Color(0xFFFFFFFF))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 18.dp, vertical = 13.dp),
                        text = "너무 너무 궁금해요. 이용해 보고 싶어요!",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.notosans_regular)),
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    )

                    Box(
                        modifier = Modifier
                            .width(78.dp)
                            .fillMaxHeight()
                            .background(mainBlueColor)
                            .clickable {
                                onClickListener()
                            }
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "전송",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.notosans_regular)),
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        )
                    }
                }
            }
        }
    }
}
package com.example.bumpercar.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bumpercar.R
import com.example.bumpercar.component.BumpercarTopBar
import com.example.bumpercar.component.ContentBox
import com.example.bumpercar.hospital.hospitalList
import com.example.bumpercar.ui.theme.mainHeadLineColor
import com.example.bumpercar.ui.theme.mainSubLineColor
import com.example.bumpercar.viewmodel.MainViewModel
import java.net.URLEncoder

@Composable
fun ReviewMainScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {
    val topMainTextSize = 26.5.sp

    val topSubTextSize = 19.sp // 7.5 차이

    val isLoading = mainViewModel.isLoading.collectAsState()

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
            
            Text(
                text = "해당 병원들은 리뷰 수가 많은 순으로 정렬되어 있습니다",

            )

            if (isLoading.value) {
                // 로딩 중일 때 보여줄 UI
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator() // 로딩 애니메이션
                }
            } else {
                // 로딩 중이 아닐 때 LazyColumn 보여주기
                LazyColumn {
                    items(hospitalList.size) { index ->
                        val hospital = hospitalList[index]
                        ContentBox(
                            modifier = Modifier.clickable {
                                mainViewModel.getReviewResponse(hospital.hospitalName) {
                                    navHostController.navigate(
                                        "reviewScreen/${URLEncoder.encode(hospital.hospitalName, "UTF-8").replace("+", "%20")}/${URLEncoder.encode(hospital.hospitalPhone, "UTF-8")}/${URLEncoder.encode(hospital.hospitalAddress, "UTF-8").replace("+", "%20")}"
                                    )
                                }
                            }
                        ) {
                            Text(
                                text = hospital.hospitalName,
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.notosans_bold)),
                                color = mainHeadLineColor
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}
package com.example.bumpercar.screen

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
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
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
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
        ) {
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
                Text(
                    text = "해당 병원들은 리뷰 수가 많은 순으로 정렬되어 있습니다",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.notosans_bold)),
                        fontSize = 14.sp,
                        color = Color.Black
                    )

                )

                Spacer(modifier = Modifier.height(10.dp))
                // 로딩 중이 아닐 때 LazyColumn 보여주기
                LazyColumn {
                    items(hospitalList.size) { index ->
                        val hospital = hospitalList[index]
                        ContentBox(
                            modifier = Modifier.clickable {
                                mainViewModel.getReviewResponse(hospital.hospitalName) {
                                    navHostController.navigate(
                                        "reviewScreen/${
                                            URLEncoder.encode(
                                                hospital.hospitalName,
                                                "UTF-8"
                                            ).replace("+", "%20")
                                        }/${
                                            URLEncoder.encode(
                                                hospital.hospitalPhone,
                                                "UTF-8"
                                            )
                                        }/${
                                            URLEncoder.encode(hospital.hospitalAddress, "UTF-8")
                                                .replace("+", "%20")
                                        }"
                                    )
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .padding(3.dp),
                                    painter = painterResource(id = R.drawable.ic_hospital),
                                    contentDescription = "병원"
                                )
                                Text(
                                    modifier = Modifier,
                                    text = hospital.hospitalName,
                                    style = TextStyle(
                                        fontFamily = FontFamily(Font(R.font.notosans_bold)),
                                        fontSize = 20.sp,
                                        color = mainHeadLineColor
                                    )
                                )
                                Box(
                                    modifier = Modifier.weight(1f),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Text(
                                        text = "${hospital.reviewCount}개의 리뷰",
                                        style = TextStyle(
                                            fontFamily = FontFamily(Font(R.font.notosans_bold)),
                                            fontSize = 12.sp,
                                            color = mainHeadLineColor
                                        )
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

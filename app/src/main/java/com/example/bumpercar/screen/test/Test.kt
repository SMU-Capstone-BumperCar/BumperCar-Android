package com.example.bumpercar.screen.test

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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

@Composable
fun TypewriteText(
    text: String,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    spec: AnimationSpec<Int> = tween(durationMillis = text.length * 100, easing = LinearEasing),
    style: TextStyle = LocalTextStyle.current,
    preoccupySpace: Boolean = true
) {
    // 현재 애니메이션 중인 텍스트를 유지하는 상태
    var textToAnimate by remember { mutableStateOf("") }

    // 애니메이션의 진행을 제어하는 애니메이션 가능한 인덱스
    val index = remember {
        Animatable(initialValue = 0, typeConverter = Int.VectorConverter)
    }

    // 가시성 변경 시 애니메이션을 처리하는 효과
    LaunchedEffect(isVisible) {
        if (isVisible) {
            // 가시성이 true일 때 애니메이션 시작
            textToAnimate = text
            index.animateTo(text.length, spec)
        } else {
            // 가시성이 false일 때 시작 부분으로 스냅
            index.snapTo(0)
        }
    }

    // 텍스트 내용이 변경될 때 애니메이션을 처리하는 효과
    LaunchedEffect(text) {
        if (isVisible) {
            // 가시성이 true일 때 애니메이션을 초기화하고 텍스트 업데이트
            index.snapTo(0)
            textToAnimate = text
            index.animateTo(text.length, spec)
        }
    }

    // 애니메이션된 텍스트와 정적인 텍스트를 포함하는 Box 컴포저블
    Box(modifier = modifier) {
        if (preoccupySpace && index.isRunning) {
            // 애니메이션이 진행 중이고 공간 점유가 활성화된 경우
            // 투명한 텍스트를 표시하여 공간을 미리 차지
            // 텍스트가 채워질 공간의 플레이스홀더 역할을 함.
            Text(
                text = text,
                style = style,
                modifier = Modifier.alpha(0f)
            )
        }

        // 현재 인덱스 값에 따라 애니메이션된 텍스트를 표시
        Text(
            text = textToAnimate.substring(0, index.value),
            style = style
        )
    }
}

@Composable
fun quoteTextStyle() = TextStyle(
    fontSize = 16.sp,
    lineHeight = 18.sp,
    fontFamily = FontFamily(Font(R.font.notosans_regular)),
    color = MaterialTheme.colorScheme.onSurface
)

data class Quote(
    val text: String,
)

@Composable
fun QuoteCard(
    quote: Quote,
    modifier: Modifier = Modifier,
    millisPerSymbol: Int = 30,
) {
    val textDuration = (quote.text.length * millisPerSymbol)

    Surface(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            TypewriteText(
                text = quote.text,
                style = quoteTextStyle(),
                spec = tween(
                    durationMillis = textDuration,
                    easing = LinearEasing
                )
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun Test() {
    val quote_text = Quote(text = "안녕하세요 저는 한방병원 리뷰를 요약한 채팅 봇 입니다. 반가워요! 당신의 이름은 무엇이죠? 이것은 테스트 버전 입니다 반가워요^^")

    QuoteCard(
        quote = quote_text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

package com.example.bumpercar.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.bumpercar.R
import com.example.bumpercar.component.BumpercarTextField
import com.example.bumpercar.component.ChatTopBar
import com.example.bumpercar.component.TypewriteText
import com.example.bumpercar.data.AuthorData
import com.example.bumpercar.ui.theme.mainBlueColor
import com.example.bumpercar.ui.theme.textFieldBackGroundColor
import com.example.bumpercar.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(
    mainViewModel: MainViewModel,
    navHostController: NavHostController
) {

    val interactionSource = remember { MutableInteractionSource() }

    val textField = mainViewModel.textField.collectAsStateWithLifecycle()

    val chatMessageData = mainViewModel.chatMessageData.collectAsStateWithLifecycle()

    val chatMessageWithAuthor = mainViewModel.chatMessageDataWithAuthor.collectAsStateWithLifecycle()

    val isLoading = mainViewModel.isLoading.collectAsStateWithLifecycle()

    var showLoading = remember { mutableStateOf(false) }

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    
    LaunchedEffect(isLoading.value) {
        if (isLoading.value) {
            delay(1000)
            showLoading.value = true
        } else {
            showLoading.value = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .imePadding()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        ChatTopBar(
            onClick = { navHostController.popBackStack() }
        ) // 상단바를 화면에 고정
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(horizontal = 13.dp),
            state = listState
        ) {
            items(chatMessageWithAuthor.value) { chatMessageWithAuthor ->
                val isSentByUser = chatMessageWithAuthor.authorData.id == AuthorData.localUser.id
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = if (isSentByUser) Arrangement.End else Arrangement.Start
                ) {
                    Box(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    topStart = if (isSentByUser) 12.dp else 2.dp,
                                    topEnd = if (isSentByUser) 2.dp else 12.dp,
                                    bottomEnd = 12.dp,
                                    bottomStart = 12.dp
                                )
                            )
                            .widthIn(max = 300.dp)
                            .background(if (isSentByUser) mainBlueColor else textFieldBackGroundColor)
                            .padding(horizontal = 22.dp, vertical = 14.dp)
                    ) {
                        if(chatMessageWithAuthor.authorData.id == AuthorData.localUser.id){
                            Text(
                                text =chatMessageWithAuthor.messageData.query,
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.notosans_regular)),
                                    color = Color.White
                                )
                            )
                        } else {
                            TypewriteText2(
                                text = formatMessageText(chatMessageWithAuthor.messageData.query),
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.notosans_regular)),
                                    color = if (isSentByUser) Color.White else Color.Black
                                ),
                                spec = tween(
                                    durationMillis = (formatMessageText(chatMessageWithAuthor.messageData.query).length * 30),
                                    easing = LinearEasing
                                )
                            )
                        }
                    }
                }
            }
            if (showLoading.value) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(
                                    RoundedCornerShape(
                                        topStart = 2.dp,
                                        topEnd = 12.dp,
                                        bottomEnd = 12.dp,
                                        bottomStart = 12.dp
                                    )
                                )
                                .widthIn(max = 300.dp)
                                .background(textFieldBackGroundColor)
                                .padding(horizontal = 22.dp, vertical = 14.dp)
                        ) {
                            Text(
                                text = "잠시만 기다려주세요!",
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.notosans_regular)),
                                    color = Color.Black
                                )
                            )
                        }
                    }
                }
            }
        }
        Box(
            modifier = Modifier.height(70.dp),
        ) {
            BumpercarTextField(
                modifier = Modifier,
                value = textField.value,
                onValueChange = { mainViewModel.getTextField(it) },
                interactionSource = interactionSource,
                onSendClick = { mainViewModel.sendUserMessage(textField.value) },
                isLoading = isLoading.value
            )
        }

        LaunchedEffect(chatMessageData.value.messages.size) {
            coroutineScope.launch {
                listState.animateScrollToItem(chatMessageData.value.messages.size)
            }
        }
    }
}

@Composable
fun TypewriteText2(
    text: AnnotatedString,
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
            textToAnimate = text.toString()
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
            textToAnimate = text.toString()
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

// 서버에서 받아온 텍스트를 포맷하는 함수
fun formatMessageText(text: String): AnnotatedString {
    return buildAnnotatedString {
        val boldPattern = "\\*\\*(.*?)\\*\\*".toRegex()
        val italicPattern = "\\*(.*?)\\*".toRegex()
        var currentIndex = 0

        // 굵게 처리
        boldPattern.findAll(text).forEach { matchResult ->
            val matchStart = matchResult.range.first
            val matchEnd = matchResult.range.last + 1

            if (currentIndex < matchStart) {
                append(text.substring(currentIndex, matchStart))
            }

            withStyle(SpanStyle(
                fontFamily = FontFamily(Font(R.font.notosans_bold)),
                fontWeight = FontWeight.Bold)
            ) {
                append(matchResult.groupValues[1])
            }

            currentIndex = matchEnd + 1
        }

        // 기울임꼴 처리
        italicPattern.findAll(text).forEach { matchResult ->
            val matchStart = matchResult.range.first
            val matchEnd = matchResult.range.last + 1

            if (currentIndex < matchStart) {
                append(text.substring(currentIndex, matchStart))
            }

            withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                append(matchResult.groupValues[1])
            }

            currentIndex = matchEnd + 1
        }

        // 남은 부분 추가
        if (currentIndex < text.length) {
            append(text.substring(currentIndex))
        }
    }
}

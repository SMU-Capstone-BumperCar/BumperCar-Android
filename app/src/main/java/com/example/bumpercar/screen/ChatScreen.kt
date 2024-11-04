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

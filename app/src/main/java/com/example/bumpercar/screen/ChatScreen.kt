package com.example.bumpercar.screen

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.bumpercar.R
import com.example.bumpercar.component.BumpercarTextField
import com.example.bumpercar.component.BumpercarTopBar
import com.example.bumpercar.component.ChatTopBar
import com.example.bumpercar.data.AuthorData
import com.example.bumpercar.data.ChatMessageData
import com.example.bumpercar.data.MessageData
import com.example.bumpercar.ui.theme.mainBlueColor
import com.example.bumpercar.ui.theme.textFieldBackGroundColor
import com.example.bumpercar.viewmodel.ChatViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(
    chatViewModel: ChatViewModel,
    navHostController: NavHostController
) {

    val interactionSource = remember { MutableInteractionSource() }

    val textField = chatViewModel.textField.collectAsStateWithLifecycle()

    val chatMessageData = chatViewModel.chatMessageData.collectAsStateWithLifecycle()

    val chatMessageWithAuthor = chatViewModel.chatMessageDataWithAuthor.collectAsStateWithLifecycle()

    val isLoading = chatViewModel.isLoading.collectAsStateWithLifecycle()

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
                        Text(
                            text = chatMessageWithAuthor.messageData.query,
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.notosans_regular)),
                                color = if (isSentByUser) Color.White else Color.Black
                            )
                        )
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
                onValueChange = { chatViewModel.getTextField(it) },
                interactionSource = interactionSource,
                onSendClick = { chatViewModel.sendUserMessage(textField.value) }
            )
        }

        LaunchedEffect(chatMessageData.value.messages.size) {
            coroutineScope.launch {
                listState.animateScrollToItem(chatMessageData.value.messages.size)
            }
        }
    }
}

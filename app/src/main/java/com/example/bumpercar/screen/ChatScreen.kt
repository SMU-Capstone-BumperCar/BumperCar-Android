package com.example.bumpercar.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bumpercar.component.BumpercarTextField
import com.example.bumpercar.component.BumpercarTopBar

@Composable
fun ChatScreen() {

    val isTestTextField = remember { mutableStateOf("") }

    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .imePadding()
    ) {
        BumpercarTopBar()
        Column(
            modifier = Modifier.fillMaxSize().weight(1f)
        ) {

        }
        Box(
            modifier = Modifier.height(70.dp),
        ){
            BumpercarTextField(modifier = Modifier,value = isTestTextField.value, onValueChange = { isTestTextField.value = it }, interactionSource = interactionSource)
        }
    }
}
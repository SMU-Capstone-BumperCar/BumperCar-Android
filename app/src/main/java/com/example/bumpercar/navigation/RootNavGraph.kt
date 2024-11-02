package com.example.bumpercar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bumpercar.screen.ChatInfoScreen
import com.example.bumpercar.screen.ChatScreen
import com.example.bumpercar.screen.MainScreen
import com.example.bumpercar.screen.ReviewScreen
import com.example.bumpercar.viewmodel.ChatViewModel

@Composable
fun RootNavGraph(
    navHostController: NavHostController
) {
    val chatViewModel = ChatViewModel()

    NavHost(navController = navHostController, startDestination = Route.HOME) {
        composable(Route.HOME) {
            MainScreen()
        }
        composable(Route.CHAT_INFO) {
            ChatInfoScreen(navHostController = navHostController)
        }
        composable(Route.REVIEW) {
            ReviewScreen()
        }
        composable(Route.CHAT) {
            ChatScreen(chatViewModel)
        }
    }
}
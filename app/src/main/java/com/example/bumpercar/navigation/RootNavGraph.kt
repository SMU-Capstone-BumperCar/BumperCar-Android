package com.example.bumpercar.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bumpercar.screen.ChatMainScreen
import com.example.bumpercar.screen.ChatScreen
import com.example.bumpercar.screen.MainScreen
import com.example.bumpercar.screen.ReviewMainScreen
import com.example.bumpercar.screen.ReviewScreen
import com.example.bumpercar.viewmodel.MainViewModel

@Composable
fun RootNavGraph(
    navHostController: NavHostController
) {
    val mainViewModel: MainViewModel = viewModel()

    NavHost(navController = navHostController, startDestination = Route.HOME) {
        composable(Route.HOME) {
            MainScreen()
        }
        composable(Route.CHAT_INFO) {
            ChatMainScreen(navHostController = navHostController)
        }
        composable(Route.REVIEW) {
            ReviewMainScreen(navHostController = navHostController, viewModel = mainViewModel)
        }
        composable(
            "reviewScreen/{hospitalName}/{hospitalPhone}/{hospitalLocation}",
            arguments = listOf(
                navArgument("hospitalName") { type = NavType.StringType },
                navArgument("hospitalPhone") { type = NavType.StringType },
                navArgument("hospitalLocation") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val hospitalName = backStackEntry.arguments?.getString("hospitalName")
            val hospitalPhone = backStackEntry.arguments?.getString("hospitalPhone")
            val hospitalLocation = backStackEntry.arguments?.getString("hospitalLocation")

            ReviewScreen(
                hospitalName = hospitalName ?: "",
                hospitalPhone = hospitalPhone ?: "",
                hospitalLocation = hospitalLocation ?: "",
                navHostController = navHostController,
                viewModel = mainViewModel
            )
        }
        composable(Route.CHAT) {
            ChatScreen(mainViewModel, navHostController)
        }
    }
}
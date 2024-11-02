package com.example.bumpercar.navigation.bottomnavigation

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bumpercar.R
import com.example.bumpercar.ui.theme.iconSelctedColor
import com.example.bumpercar.ui.theme.iconUnselectedColor
import com.example.bumpercar.ui.theme.textFieldBackGroundColor

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.ChatInfoScreen,
        BottomNavItem.HomeScreen,
        BottomNavItem.ReviewScreen
    )

    var isSelectedTab by remember { mutableStateOf("home") }

    val interactionSource = remember { MutableInteractionSource() }

    NavigationBar(
        modifier = Modifier
            .height(60.dp)
            .border(1.dp, textFieldBackGroundColor)
            .fillMaxWidth(),
        containerColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
                NavigationBarItem(
                    selected = item.route == currentRoute,
                    onClick = {
                        navController.navigate(item.route) { popUpTo(navController.graph.startDestinationId) { saveState } }
                        isSelectedTab = item.route
                    },
                    icon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(top = 4.dp) // 원하는 만큼 간격 조절 가능
                        ) {
                            item.icon(item.route == isSelectedTab)
                            Spacer(modifier = Modifier.height(4.dp)) // 아이콘과 텍스트 사이 간격
                            Text(
                                text = item.title,
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.notosans_regular)),
                                    fontSize = 12.sp,
                                    color = if (item.route == isSelectedTab) iconSelctedColor else iconUnselectedColor
                                )
                            )
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.White
                    ),
                    interactionSource = interactionSource
                )
            }
        }
    }
}

private object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Transparent

    @Composable
    override fun rippleAlpha() = RippleAlpha(0F, 0F, 0F, 0F)
}
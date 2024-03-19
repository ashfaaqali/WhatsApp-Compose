package com.ali.whatsappcompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ali.whatsappcompose.presentation.screens.ChatsScreen
import com.ali.whatsappcompose.presentation.screens.ContactsScreen
import com.ali.whatsappcompose.presentation.screens.RecentChats

@Composable
fun NavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Route.RecentChats.route
    ) {
        composable(Route.RecentChats.route){
            RecentChats {
                navController.navigate(Route.ChatScreen.route)
            }
        }

        composable(Route.ChatScreen.route){
            ChatsScreen {
                navController.navigate(Route.RecentChats.route)
            }
        }

        composable(Route.Contacts.route){
            ContactsScreen {
                navController.navigate(Route.ChatScreen.route)
            }
        }
    }
}
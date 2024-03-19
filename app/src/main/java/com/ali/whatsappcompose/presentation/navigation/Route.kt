package com.ali.whatsappcompose.presentation.navigation

sealed class Route(val route: String) {
    object RecentChats: Route(route = "recent_chats")
    object ChatScreen: Route(route = "chat_screen")
    object Contacts: Route(route = "contacts")
}
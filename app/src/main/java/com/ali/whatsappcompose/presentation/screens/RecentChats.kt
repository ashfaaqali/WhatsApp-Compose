package com.ali.whatsappcompose.presentation.screens

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ali.whatsappcompose.presentation.navigation.Route
import com.ali.whatsappcompose.presentation.viewmodel.RecentChatsViewModel

@Composable
fun RecentChats(onClick: () -> Unit) {
    val viewModel: RecentChatsViewModel = viewModel()
    val recentChats by viewModel.recentChats.observeAsState(initial = emptyList())
    Log.d("RecentChats", recentChats.toString())

    if (recentChats.isNotEmpty()) {
        LazyColumn(content = {
            items(recentChats) { chat ->
                RecentChatsItem(recentChat = chat, onClick)
            }
        })
    }
}

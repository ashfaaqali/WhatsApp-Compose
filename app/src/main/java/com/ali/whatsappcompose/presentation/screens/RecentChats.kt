package com.ali.whatsappcompose.presentation.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.ali.whatsappcompose.presentation.viewmodel.RecentChatsViewModel

@Composable
fun RecentChats(viewModel: RecentChatsViewModel) {
    val recentChats by viewModel.recentChats.observeAsState(initial = emptyList())

    if (recentChats.isNotEmpty()) {
        LazyColumn(content = {
            items(recentChats) { chat ->
                RecentChatsItem(recentChat = chat)
            }
        })
    } else {
        Text(text = "Loading...")
    }
}

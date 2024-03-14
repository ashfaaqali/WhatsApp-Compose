package com.ali.whatsappcompose.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ali.whatsappcompose.R
import com.ali.whatsappcompose.data.model.RecentChats

@Composable
fun RecentChatsItem(recentChat: RecentChats) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                modifier = Modifier.size(55.dp)
            )

            Column() {
                Text(
                    text = recentChat.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(2.dp)
                )
                Text(
                    text = recentChat.lastMessage,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(2.dp)
                )
            }
        }

        Text(
            text = recentChat.timestamp,
            fontSize = 11.sp,
            textAlign = TextAlign.End,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

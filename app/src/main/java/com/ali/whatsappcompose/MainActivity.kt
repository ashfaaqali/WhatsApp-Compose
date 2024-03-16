package com.ali.whatsappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ali.whatsappcompose.presentation.screens.Calls
import com.ali.whatsappcompose.presentation.screens.RecentChats
import com.ali.whatsappcompose.presentation.screens.Updates
import com.ali.whatsappcompose.presentation.viewmodel.RecentChatsViewModel
import com.ali.whatsappcompose.ui.theme.PrimaryColor
import com.ali.whatsappcompose.ui.theme.WhatsappComposeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val recentChatsViewModel: RecentChatsViewModel by viewModels()

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsappComposeTheme {
                val pagerState = rememberPagerState(pageCount = 3)
                Scaffold(
                    topBar = { Toolbar() },
                    floatingActionButton = { FabButton() }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(it)
                    ) {
                        Tabs(pagerState = pagerState)
                        TabsContent(pagerState = pagerState, recentChatsViewModel)
                    }
                }
            }
        }
        recentChatsViewModel.loadRecentChatsFromAssets(
            context = applicationContext,
            fileName = "recentChats.json"
        )
    }
}

@Preview
@ExperimentalPagerApi
@Composable
fun Toolbar() {
    Column(
        modifier = Modifier.background(Color.White)
    ) {
        TopAppBar(backgroundColor = PrimaryColor) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Text(
                        text = "WhatsApp",
                        style = TextStyle(color = Color.White),
                        fontSize = 22.sp,
                        modifier = Modifier
                            .padding(12.dp, 0.dp, 0.dp, 0.dp)
                            .weight(1f),
                        textAlign = TextAlign.Start
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_camera_outlined),
                        contentDescription = null, // Add description if needed
                        modifier = Modifier.padding(horizontal = 12.dp),
                        tint = Color.White,
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null, // Add description if needed
                        modifier = Modifier.padding(horizontal = 6.dp),
                        tint = Color.White
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_options),
                        contentDescription = null, // Add description if needed
                        modifier = Modifier.padding(horizontal = 6.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf(
        "Chats",
        "Updates",
        "Calls"
    )

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = PrimaryColor,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color.White
            )
        }
    ) {

        list.forEachIndexed { index, _ ->
            Tab(
                text = { Text(list[index]) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.LightGray,
                selected = pagerState.currentPage == index,

                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState, recentChatsViewModel: RecentChatsViewModel) {
    HorizontalPager(
        state = pagerState,
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize(1f)
    ) { page ->
        when (page) {
            0 -> RecentChats(recentChatsViewModel)
            1 -> Updates(tabName = "Updates")
            2 -> Calls(tabName = "Calls")
        }
    }
}

@Preview
@Composable
fun FabButton() {
    FloatingActionButton(
        onClick = {}, modifier = Modifier.padding(8.dp),
        containerColor = PrimaryColor
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_chat),
            contentDescription = null,
            tint = Color.White,
        )
    }
}

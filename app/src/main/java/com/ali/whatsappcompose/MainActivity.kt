package com.ali.whatsappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ali.whatsappcompose.presentation.screens.Calls
import com.ali.whatsappcompose.presentation.screens.RecentChats
import com.ali.whatsappcompose.presentation.screens.Updates
import com.ali.whatsappcompose.ui.theme.PrimaryColor
import com.ali.whatsappcompose.ui.theme.WhatsappComposeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsappComposeTheme {
                Toolbar()
            }
        }
    }
}

@Preview
@ExperimentalPagerApi
@Composable
fun Toolbar() {

    val pagerState = rememberPagerState(pageCount = 3)

    Column(
        modifier = Modifier.background(Color.White)
    ) {
        TopAppBar (backgroundColor = PrimaryColor) {

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
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState)
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
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> RecentChats(tabName = "Recent Chats")
            1 -> Updates(tabName = "Updates")
            2 -> Calls(tabName = "Calls")
        }
    }
}

//@Composable
//fun TabContentScreen(data: String) {
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = data,
//
//            style = MaterialTheme.typography.h5,
//
//            color = greenColor,
//
//            fontWeight = FontWeight.Bold,
//
//            textAlign = TextAlign.Center
//        )
//    }
//}

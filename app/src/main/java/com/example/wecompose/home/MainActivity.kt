package com.example.wecompose.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wecompose.R
import com.example.wecompose.ui.theme.WeComposeTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<HomeViewModel>()

    val items = listOf("主页", "发现", "我")

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge() 是否保留状态栏
        setContent {
            Scaffold(topBar = {
                TopAppBar(title = {
                    Text("首页")
                }, navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                })
            }) {
                WeComposeTheme(viewModel.theme) {
                    home()
//                ChatPage()
                }
            }
        }
    }


    @Composable
    @OptIn(ExperimentalFoundationApi::class)
    private fun home() {
        Column {
            val pagerState = rememberPagerState(pageCount = { 4 })
            //                    HorizontalPager(pagerState, Modifier.padding(48.dp)) {
            HorizontalPager(pagerState, Modifier.weight(1f)) {
                when (pagerState.currentPage) {
                    0 -> {
                        chatList(viewModel.chats)
                    }

                    1 -> {
                        Box(modifier = Modifier.fillMaxSize())
                    }

                    2 -> {
                        Box(modifier = Modifier.fillMaxSize())
                    }

                    3 -> {
                        Box(modifier = Modifier.fillMaxSize())
                    }
                }
            }

            homeTab(viewModel.selectedTab) {
                viewModel.selectedTab = it
            }
        }
    }

    override fun onBackPressed() {
        if (!viewModel.endChat()) {
            super.onBackPressed()
        }
    }
}



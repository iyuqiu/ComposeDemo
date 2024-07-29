package com.example.wecompose.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wecompose.R
import com.example.wecompose.ui.theme.WeComposeTheme

@Composable
fun homeTab(selected: Int, onSelectedChange: (Int) -> Unit) {
    Row(Modifier.background(WeComposeTheme.colors.bottomBar).height(56.dp)) {
        tabItem(if (selected == 0) R.drawable.ic_chat_filled else R.drawable.ic_chat_outlined,
            "聊天",
            if (selected == 0) WeComposeTheme.colors.iconCurrent else WeComposeTheme.colors.icon,
            Modifier
                .weight(1f)
                .clickable {
                    onSelectedChange(0)
                })
        tabItem(if (selected == 1) R.drawable.ic_contacts_filled else R.drawable.ic_contacts_outlined,
            "通讯录",
            if (selected == 1) WeComposeTheme.colors.iconCurrent else WeComposeTheme.colors.icon,
            Modifier
                .weight(1f)
                .clickable {
                    onSelectedChange(1)
                })
        tabItem(if (selected == 2) R.drawable.ic_discovery_filled else R.drawable.ic_discovery_outlined,
            "发现",
            if (selected == 2) WeComposeTheme.colors.iconCurrent else WeComposeTheme.colors.icon,
            Modifier
                .weight(1f)
                .clickable {
                    onSelectedChange(2)
                })
        tabItem(if (selected == 3) R.drawable.ic_me_filled else R.drawable.ic_me_outlined,
            "我",
            if (selected == 3) WeComposeTheme.colors.iconCurrent else WeComposeTheme.colors.icon,
            Modifier
                .weight(1f)
                .clickable {
                    onSelectedChange(3)
                })
//            tabItem(
//                if (selected == 3) R.drawable.ic_me_filled else R.drawable.ic_me_outlined,
//                "我",
//                if (selected == 3) green3 else black,
//                Modifier.weight(1f)
//            )
    }
}

@Composable
private fun tabItem(@DrawableRes iconId: Int, title: String, tint: Color, modifier: Modifier) {
    Column(
        modifier.padding(vertical = 8.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painterResource(iconId),
            title,
            Modifier
                .unread(true, Color.Red)
                .size(22.dp),
            tint = tint
        )
        Text(title, fontSize = 11.sp, color = tint)
    }
}

@Preview(showBackground = true)
@Composable
private fun test() {
    //暗色主题
//        WeComposeTheme(WeComposeTheme.Theme.Dark){
//            homeTab(selected = 1)
//        }
    // remember 使用场景是在Composable函数里使用
    var selectedTab by remember {
        mutableIntStateOf(0)
    }
    homeTab(selected = selectedTab) {
        selectedTab = it
    }
}

@Preview(showBackground = true)
@Composable
private fun testDarkTheme() {
    //暗色主题
    var selectedTab by remember {
        mutableIntStateOf(0)
    }
    WeComposeTheme(WeComposeTheme.Theme.Dark) {
        homeTab(selected = 1) {
            selectedTab = it
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun testTab() {
//        CompositionLocal<Color>  主题颜色
    tabItem(R.drawable.ic_chat_filled, "聊天", WeComposeTheme.colors.icon, Modifier)
}
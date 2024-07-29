package com.example.wecompose.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wecompose.data.Chat
import com.example.wecompose.ui.theme.WeComposeTheme

/**
 *  Image 和 Icon 区别
 *  Image显示图片
 *  Icon显示纯色图标
 */
@Composable
fun chatList(chats: List<Chat>) {

    Column(
        Modifier
            .background(WeComposeTheme.colors.background)
            .fillMaxSize()
    ) {
        WeTopBar(title = "小马")
        LazyColumn(Modifier.background(WeComposeTheme.colors.listItem)) {
//            items(chats) { chat ->
            itemsIndexed(chats) { index, chat ->
                ChatListItem(chat)
                if (index < chats.lastIndex) {
                    HorizontalDivider(thickness = 2.dp, color = Color.Red)
                }

//                Image(
//                    painterResource(id = chat.friend.avatar),
//                    chat.friend.name,
//                    Modifier
//                        .padding(4.dp)
//                        .size(48.dp)
//                        .unread(!chat.msgs.last().read, Color.Red)
//                        .clip(
//                            RoundedCornerShape(4.dp)
//                        )
//                )

            }
        }
    }
}

@Composable
private fun ChatListItem(chat: Chat) {
    val viewModel: HomeViewModel = viewModel()
    Row(
        Modifier
            .clickable { viewModel.startChat(chat) }
            .fillMaxWidth()) {
        Image(
            painterResource(chat.friend.avatar),
            chat.friend.name,
            Modifier
                .padding(8.dp)
                .size(48.dp)
                .unread(!chat.msgs.last().read, WeComposeTheme.colors.badge)
                .clip(RoundedCornerShape(4.dp))
        )
        Column(
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(chat.friend.name, fontSize = 17.sp, color = WeComposeTheme.colors.textPrimary)
            Text(
                chat.msgs.last().text, fontSize = 14.sp, color = WeComposeTheme.colors.textSecondary
            )
        }
        Text(
            chat.msgs.last().time,
            Modifier.padding(24.dp, 8.dp, 12.dp, 8.dp),
            fontSize = 11.sp,
            color = WeComposeTheme.colors.textSecondary
        )
    }
}

fun Modifier.unread(show: Boolean, color: Color): Modifier = this.drawWithContent {
    drawContent()
    if (show) {
        drawCircle(
            color, 4.dp.toPx(), Offset(size.width - 1.dp.toPx(), 1.dp.toPx())
            /**
             * WeComposeTheme.colors.badge, 5.dp.toPx(), Offset(size.width - 1.dp.toPx(), 1.dp.toPx())
             *  不能使用的原因是不在Composeable注释下，而WeComposeTheme.colors.badge的调用范围有强关联
             */
        )
    }
}
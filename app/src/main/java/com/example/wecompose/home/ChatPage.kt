package com.example.wecompose.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import kotlin.math.roundToInt

@Composable
fun ChatPage() {
    val viewModel = HomeViewModel()

    val setPercentX by animateFloatAsState(if (viewModel.chatting) 0f else 1f, label = "")
    Box(
        Modifier
            .background(Color.Magenta)
            .offSetPercent(0f)
            .fillMaxSize()
    )
}

//        Modifier
//            .offSetPercent(offsetPercentX)
//            .layout { measurable, constraints -> //自定义布局
//                val placeable = measurable.measure(constraints)
////                val offset = if (viewModel.chatting) 0 else placeable.width
//                layout(placeable.width, placeable.height) {
//                    val offSetX = (offsetPercentX * placeable.width).roundToInt()
//                    placeable.placeRelative(offSetX, 0)
//                }
//            }
//            .offset(16.dp)

fun Modifier.offSetPercent(offsetPercentX: Float = 0f, offsetPercentY: Float = 0f) =
    this.layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.width, placeable.height) {
            val offSetX = (offsetPercentX * placeable.width).roundToInt()
            val offSetY = (offsetPercentY * placeable.height).roundToInt()
            placeable.placeRelative(offSetX, offSetY)
        }
    }

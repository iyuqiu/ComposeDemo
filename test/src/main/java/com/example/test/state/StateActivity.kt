package com.example.test.state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class StateActivity : ComponentActivity() {

    private var user by mutableStateOf("小马哥")  // MutableState
    private val nameState = mutableStateOf("小马")  // MutableState

    /**
     * MutableState 和 mustableStateOf()
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            testState()
            var name by remember {
                mutableStateOf("1")
            }
            //name从外往里传； newValue 从里往外传； 叫单项数据流
            TextField(value = name, onValueChange = { newValue ->
                //若不设置，则内容无法更新
                name = newValue
            })
        }
//        lifecycleScope.launch {
//            delay(3000)
//            name.value = "小马奔腾"
//            user = "小马哥来了by"
    }

    @Composable
    private fun testState() {
        Box(
            Modifier
                .padding(16.dp)
                .clickable {
                    nameState.value = "2"
                }) {
            //Compose过程
            Text(text = nameState.value)
            /**
             *  如果有下面这一行，则显示3； 但是点击后没有反应
             *   如果没有下面这一行，点击后有反应，显示小马；
             *   原因： 组合过程中发生改变，发生失效；具体原因见mutableStateOf 源代码的订阅过程
             */
            nameState.value = "3"
        }
    }
}


//    @Composable
//    private fun testState() {
//        Column {
//            Text(text = user)
//            Text(text = name.value)
//        }
//    }
//}
package com.example.test.state

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RememberActivity : ComponentActivity() {
    /**
     *   重组作用域 被重新执行，因为初始化被重新执行
     *
     *   remember 起到缓存作用，防止多次初始化；全部包上就没问题了
     *    只能在composable环境里
     */

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var name by remember {
                //remember起到缓存的作用，首次初始化，二次获取缓存数据
                mutableStateOf("小马")
            }
//            Button(onClick = { /*TODO*/ }) {
//                Text(text = name) //被包裹后，作用域只有Button里
//            }
            Text(text = name)
            lifecycleScope.launch {
                delay(3000)
                name = "3"
            }
        }
    }
    
    @Composable
    fun showCharCount(value: String){
        /**
         * remember不带参数，则一直取的是缓存
         * 增加参数后，如果一致则取缓存，不一致则重新计算
         */
        val length = remember(key1 = value) {
            value.length
        }
        Text(text = "字符串长度$length")
    }
}
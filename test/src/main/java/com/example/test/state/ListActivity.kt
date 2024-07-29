package com.example.test.state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

class ListActivity : ComponentActivity() {

    private var num by mutableIntStateOf(1)

    //    private var results by mutableStateOf(mutableListOf(1, 2, 3))
    private val bestResults = mutableStateListOf(1, 2, 3) //注意 by 和= 的区别
    val map = mutableStateMapOf(1 to "one", 2 to "two")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /**
             *  mutableStateOf(): 读的时候会注册坚挺； 写的时候会去标记失效，实现刷新：
             * 1、 组合过程中的写
             *  2、 组合过程之后的写 + 应用
             *
             *  Compose 里面用mutalbeStateOf() 创造出的 MutableState： 监听是否重新赋值
             *  可以使用 mutableStateMapOf 和 mutableStateListOf
             */
            Column {
                Text(text = num.toString(), Modifier.clickable {
                    //点击后列表刷新了？
                    num++
                })
                Button(onClick = {
                    //如果没有重新赋值操作，则无法刷新
//                    results.add(results.last() + 1)
                    //重新赋值操作，可以刷新
//                    results = results.toMutableList()
//                    results = results.toMutableList().apply {
//                        add(results.last() + 1)
                    bestResults.add(bestResults.last() + 1)
//                    }
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "list增加数据")
                }
                bestResults.forEach { result ->
                    Text(text = "当前的值是$result")
                }
//                results.forEach { result ->
//                    Text(text = "当前的值是$result")
//                }
            }

        }
    }
}


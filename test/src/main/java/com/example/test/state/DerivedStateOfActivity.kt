package com.example.test.state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

class DerivedStateOfActivity : ComponentActivity() {

    /**
     *    mutableStateOf()
     *    mutableStateListOf()   都是 StateObject
     *
     *
     *    derivedStateOf() 与remember 区别
     *
     *
     */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var name by remember {
                mutableStateOf("xiaoma")
            }
            // 由于derivedStateOf 导致remember没有值业自动更新
            val processName by remember { derivedStateOf { name.uppercase() } }
            val processName1 = remember(name) { name.uppercase() }

            Text(text = processName, modifier = Modifier.clickable {
                name = "小马奔腾"
            })

            var names = remember {
                mutableStateListOf("xiaoma", "niuya")
            }
            // 由于derivedStateOf 导致remember没有值业自动更新
//            val processName by remember { derivedStateOf { name.uppercase() } }
//            val processName1 = remember(name) { name.uppercase() }
            Column {
                names.forEach { listName ->
                    Text(text = listName, modifier = Modifier.clickable {
                        names.add("哈哈哈哈")
                    })
                }
            }

        }

    }
}

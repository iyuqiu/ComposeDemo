package com.example.test.state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

class ReComposetActivity : ComponentActivity() {

    /**
     *  1、触发重组  变量改变
     *  2、重组  Text点击后 改变 重组
     *
     *  name值修改后 setContent内的所有内容都被打印 引出  重组的性能风险
     *
     *  自动更新 -> 更新范围过大，超过需求 -> 跳过没必要的需求
     *
     *  Structual Equality 结构性相等 Kotlin的 == 与 Java的equals ，对比空对象
     *        Kotlin的 === 与 Java的 ==  引用是否相等
     *
     *
     *   结论：
     *   对于不用重组的部分，可以单独冲出来；否则全部重组UI
     */

    private var name by mutableStateOf("小马哥")
    private var user2 = User("对象")
    private var user1 = User("对象")
    private var user = user1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            println("Recompose 范围测试 1")
            Column {
                println("Recompose 范围测试 2")
                heavy(user)
                Text(text = name, modifier = Modifier.clickable {
                    name = "小马奔腾"
                    user = user2
//                    user.name = "厉害"
                })
            }
        }
    }

    @Composable
    private fun heavy(result: User) {
        // name值改变，如果没有用，则没有打印； 如果使用，则打印； 是否更新需要看对象的结构性是否相等
        println("Recompose 范围测试性能测试，好重呀")
        Text(text = "Heavy：${result.name}")
    }

    /**
     *   val可靠，不会更新  var 不可靠，也会更新
     *
     *   类似HashMap中key是对象时，如果对象参数变化，则hashMapkey变化；
     *
     *   不可靠 var的不可靠性可以通过注解@Stable处理,会对象判断， user更改的对象如果一致，heavy范围不变
     *
     *   Compose对可靠性
     *   公开属性需要全部稳定 ->可靠属性
     *
     *   当公开属性改变的时候，通知到用到这个属性的Compositin
     */


//    @Stable
   data class User(var name: String) {
//        var name by mutableStateOf(name)

//        var company by mutableStateOf(company) //因为Company不可靠，导致User不可靠
    }

    class Company(var address: String)
}
package com.example.test.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.test.R

class MainActivity : ComponentActivity() {

    /**
     * 声明UI，传统写法是命令写法
     */
    private var name by mutableStateOf("hello")

    private var imageUlr =
        "https://lovol-bjfy.oss-cn-beijing.aliyuncs.com/public/farm/image/2023/39/d41bccc5-4317-4e4b-b83b-f4074bae8239.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            PaddingDemo()
        }
    }

    @Preview
    @Composable
    fun testPadding(){
        PaddingDemo()
    }


    @Composable
    fun PaddingDemo() {
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.Red, RoundedCornerShape(8.dp))
                    .padding(all = 8.dp)
            ) {
                /**
                 *  layout_width 和 layout_height
                 *  默认是wrap_content
                 *  match_parent Modifier.fillMaxWidth
                 */
                AsyncImage(  // Canvas.drawBitmap  Canvas.drawColor()  原生控件交互
                    model = imageUlr,
                    contentDescription = null,
                    Modifier
                        .clip(CircleShape)
                        .size(160.dp)
                )
                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                // 通用的设置用Modifier，专项的设置用函数参数； clickable的点击范围根据位置不同点击位置不同
                Text(
                    text = "padding", Modifier
//                        .clickable {
                        //该位置包含margin部分
//                        }
                        .padding(10.dp)
//                        .clickable {
                        //正常点击的按钮显示部分
//                        }
                        .background(Color.Green)
                        .padding(horizontal = 18.dp, vertical = 8.dp)
                        .clickable {
                            //去掉padding部分的
                        }, fontSize = 18.sp, color = Color.Black

                )

            }
            OutlinedButton(onClick = {
            }) {
                Text(text = "小马奔腾")
            }
            TextField(value = "请输入内容", onValueChange = {

            } ,Modifier.padding(8.dp))
//            Button(onClick = {
//
//            }) { //内部默认横排
//                Text(text = "奔腾")
//                Image(painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "",Modifier.size(24.dp))
//            }
        }
    }


    /**
     *  独立于平台，不依赖于android
     *  RecyclerView,ConstraintLayout 独立于最新版Android
     *  Bitmap 位图 像素  Image  放大后有锯齿
     *  矢量图 - 规则描述 - 占用存储空间小，放大后无锯齿
     *
     *   Flutter  跨平台 android 和 Ios
     *   Compose  多平台
     *
     *   谷歌和JetBrains配合
     *
     *   kotlin 是JetBrain 推广多平台 桌面版，web
     *
     */
    @Composable
    fun textImage() {
        //            Column(Modifier.verticalScroll(rememberScrollState() )) {
        Column(Modifier.horizontalScroll(rememberScrollState())) {
            Text(text = "小马哥")  // drawText 底层使用api
            Image(
                painter = painterResource(R.drawable.ic_bg_newyear_top),
                contentDescription = "小马哥"
            )
//                ImageBitmap()
//                ImageVector
            // Coil compose图片库

            val names = listOf("小马哥", "Jack 马", "马小七")
            LazyColumn {
                item {
                    AsyncImage(  // Canvas.drawBitmap  Canvas.drawColor()  原生控件交互
                        model = imageUlr, contentDescription = null, Modifier.size(128.dp)
                    )
                }
                items(names) { name ->
                    Box {
                        Text(text = name)
                    }

                }
            }
        }
    }
}



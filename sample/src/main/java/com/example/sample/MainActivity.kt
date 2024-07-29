package com.example.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.sample.ui.theme.WeComposeTheme

class MainActivity : ComponentActivity() {

    private var openDialog by mutableStateOf(false)
    private var confirmDialog by mutableStateOf(false)

    private var clickResult by mutableIntStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            WeComposeTheme {
                TestList()
            }
        }
    }

    @Composable
    private fun TestList() {
        Column(
            Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(text = "$clickResult")

            Button(onClick = {
                openDialog = true
            }, Modifier.fillMaxWidth()) {
                Text(text = "确认取消对话框")
            }

            Button(onClick = {
                confirmDialog = true
            }, Modifier.fillMaxWidth()) {
                Text(text = "确认对话框")
            }
        }

        ShowDialog()
        ShowSingleBtnDialog()
    }

    @Composable
    fun ShowDialog() {
        if (openDialog) {
            AlertDialog(modifier = Modifier.fillMaxWidth(), onDismissRequest = {
                openDialog = false
            }, shape = RoundedCornerShape(8.dp), confirmButton = {
                TextButton(
                    onClick = {
                        clickResult += 1
                        openDialog = false
                    },
                ) {
                    Text(
                        "确认",
                        fontWeight = FontWeight.W700,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }, dismissButton = {
                TextButton(onClick = {
                    openDialog = false
                }) {
                    Text(
                        "取消",
                        fontWeight = FontWeight.W700,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }, title = {
                Text(
                    text = "开启位置服务",
                    fontWeight = FontWeight.W700,
                    style = MaterialTheme.typography.titleLarge
                )
            }, text = {
                Text(
                    text = "这将意味着，我们会给您提供精准的位置服务，并且您将接受关于您订阅的位置信息",
                    fontSize = 16.sp
                )
            })
        }
    }


    @Composable
    fun ShowSingleBtnDialog() {
        if (confirmDialog) {
            Surface(Modifier.fillMaxWidth().background(Color.White),shape = RoundedCornerShape(8.dp)) {
                Dialog(onDismissRequest = { confirmDialog = false }) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Text(text = "开启位置服务")
                            Spacer(Modifier.padding(vertical = 8.dp))
                            Text(text = "这将意味着，我们会给您提供精准的位置服务，并且您将接受关于您订阅的位置信息")
//                        Spacer(Modifier.padding(vertical = 8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Button(
                                    onClick = { confirmDialog = false },
                                    modifier = Modifier
                                        .weight(0.5f)
                                        .padding(8.dp)
                                ) {
                                    Text(text = "取消")
                                }
                                Spacer(Modifier.padding(horizontal = 8.dp))
                                Button(
                                    onClick = {
                                        confirmDialog = false
                                        clickResult += 1
                                    }, modifier = Modifier
                                        .weight(0.5f)
                                        .padding(8.dp)
                                ) {
                                    Text(text = "确认")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

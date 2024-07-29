package com.example.test.state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class EmpytActivity:ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {  }
    }
}
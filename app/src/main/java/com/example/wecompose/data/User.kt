package com.example.wecompose.data

import androidx.annotation.DrawableRes
import com.example.wecompose.R

class User(
  val id: String,
  val name: String,
  @DrawableRes val avatar: Int
) {
  companion object {
    val Me: User = User("xiaoma", "小马奔腾", R.drawable.avatar_rengwuxian)
  }
}
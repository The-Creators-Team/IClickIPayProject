package com.example.iclickipay.presentation.chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.iclickipay.R

@Composable
fun UserImage(userImage: Int) {
    Image(
        modifier = Modifier.size(60.dp).padding(start = 8.dp),
        painter = painterResource(id = userImage),
        contentDescription = "")
}

@Preview(showBackground = true)
@Composable
fun UserImagePreview(){
    UserImage(userImage = R.drawable.rebecca)
}
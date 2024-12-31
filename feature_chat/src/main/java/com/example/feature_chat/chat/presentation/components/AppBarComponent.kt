package com.example.feature_chat.chat.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature_chat.R

@Composable
fun AppBarComponent(){
    //Spacer(modifier = Modifier.size(16.dp).background(Color.LightGray))
    Row(modifier = Modifier.fillMaxWidth().height(80.dp).background(Color.LightGray).padding(top = 30.dp,start = 16.dp),verticalAlignment = Alignment.CenterVertically){

        Text(text = stringResource(id = R.string.chat_title), style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.SemiBold))

        Spacer(modifier = Modifier.weight(1f))
        IconComponent(R.drawable.ic_account_circle)
        Spacer(modifier = Modifier.size(10.dp))
        IconComponent(R.drawable.ic_search)
        Spacer(modifier = Modifier.size(8.dp))
        IconComponent(R.drawable.ic_more_menu)
    }
}

@Composable
fun IconComponent(drawableId: Int){
    Icon(painter = painterResource(id = drawableId),contentDescription = "", tint = Color.Gray)
}

@Preview
@Composable
fun AppBarComponentPreview(){
    AppBarComponent()
}
package com.example.iclickipay.presentation.reuseable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iclickipay.ui.theme.btnClr


@Composable
fun CustomButton(text: String,onClick: () -> Unit){
    androidx.compose.material3.Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(35.dp)
            .size(257.dp, 56.dp), // Width 257dp, Height 56dp

        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(btnClr.value)) // Orange color
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
    }
}
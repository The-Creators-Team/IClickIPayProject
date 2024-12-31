package com.example.feature_pet.pet

import android.media.Rating
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature_pet.R

@Composable
@Preview(showBackground = true)
fun PetFilter(){
    Column(

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()


    ) {
        DividerWithText("Preferred Price")

        // Track the selected radio button
        val dayPrice = remember { mutableIntStateOf(50) }
        Row(verticalAlignment = Alignment.CenterVertically
        ){
            RadioButton(
                selected = dayPrice.intValue==50,
                onClick = { dayPrice.intValue = 50 }
            )
            Text("50")
            RadioButton(
                selected = dayPrice.intValue==100,
                onClick = { dayPrice.intValue = 100 }
            )
            Text("100")


        }


        DividerWithText("Rating")

        val selectedSize = remember { mutableStateOf("Small") }
        Row(verticalAlignment = Alignment.CenterVertically
        ){
            RadioButton(
                selected = selectedSize.value=="Small",
                onClick = { selectedSize.value = "Small" }
            )
            Text("Small")
            RadioButton(
                selected = selectedSize.value=="Medium",
                onClick = { selectedSize.value = "Medium" }
            )
            Text("Medium")
            RadioButton(
                selected = selectedSize.value=="Large",
                onClick = { selectedSize.value = "Large" }
            )
            Text("Large")
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
        )
        Button(
            onClick = {
                //navigateToPetList()
            }
        ) {
            Text(text = "Back to Pet Map")
        }


    }
}
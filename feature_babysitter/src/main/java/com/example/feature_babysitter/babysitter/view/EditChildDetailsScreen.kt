package com.example.feature_babysitter.babysitter.view

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.feature_babysitter.babysitter.data.BabySitterViewModel
import com.example.feature_babysitter.babysitter.data.Child
import com.example.feature_babysitter.babysitter.navigation.BabySitterScreen

@Composable
fun EditChildDetailsScreen(
    navController: NavController,
    viewModel: BabySitterViewModel,
    onNavigateBack: () -> Unit,
    index: String?
) {
    var name by remember { mutableStateOf("") }
    var isMale by remember { mutableStateOf(true) }
    var age by remember { mutableStateOf("") }
    val context = LocalContext.current

    var child = viewModel.children[index?.toInt()!!]
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Edit Child Info $index",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 35.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { input ->
                if (input.all { it.isLetter() || it.isWhitespace() }) {
                    name = input
                }
            },
            label = { Text(child.name) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Gender:", modifier = Modifier.padding(end = 8.dp))
            Checkbox(
                checked = isMale,
                onCheckedChange = { isMale = it },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = if (isMale) "Male" else "Female")
        }

        // Age Entry
        OutlinedTextField(
            value = age,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    val inputAge = it.toIntOrNull() ?: 0
                    if (inputAge in 0..17) age = it
                }
            },
            label = { Text(child.age.toString()) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                if (name.isNullOrBlank() || age.isNullOrBlank()) {
                    Toast.makeText(context, "Name or Age cannot be empty", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    child = Child(
                        name = name,
                        gender = if (isMale) "Male" else "Female",
                        age = age.toIntOrNull() ?: 0,
                        imageResId = child.imageResId
                    )
                    viewModel.updateChild(index.toInt(), child) // Add child to ViewModel

                    navController.navigate(BabySitterScreen.TakeAPhotoScreen.route)
                }
            }
        ) {
            Text(text = "Update")
        }
        Button(
            onClick = {
                navController.navigate(BabySitterScreen.ChildListScreen.route)
            }
        ) {
            Text(text = "Back")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Back to Home Button
        Button(
            onClick = onNavigateBack

        ) {
            Text(text = "Back to All Apps")
        }
    }
}
package com.example.common.reuseable


import androidx.compose.foundation.background

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdown(
    label: String,
    selectedValue: String,
    onValueChange: (String) -> Unit,
    options: List<String>,
    isError: Boolean = false,
    errorMessage: String = "",
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var hoveredOption by remember { mutableStateOf<String?>(null) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedValue,
            onValueChange = {}, // Prevent user typing
            label = { Text(label) },
            readOnly = true,
            isError = isError,
            modifier = modifier
                .fillMaxWidth()
                .menuAnchor()
                .padding(vertical = 8.dp)
                .clickable { expanded = true },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEachIndexed { index, option ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (hoveredOption == option) Color.LightGray else Color.Transparent
                        )
                        .clickable {
                            onValueChange(option)
                            expanded = false
                        }
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    Text(text = option, color = Color.Black)
                }

                if (index < options.size - 1) {
                    Divider(color = Color.Gray, thickness = 1.dp)
                }
            }
        }
    }

    if (isError) {
        Text(
            text = errorMessage,
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCustomDropdownDemo() {
    CustomDropdown(
        label = TODO(),
        selectedValue = TODO(),
        onValueChange = TODO(),
        options = TODO(),
        isError = TODO(),
        errorMessage = TODO(),
        modifier = TODO()
    )
}

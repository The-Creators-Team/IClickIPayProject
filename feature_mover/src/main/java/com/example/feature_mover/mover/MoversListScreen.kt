package com.example.feature_mover.mover

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MoversListScreen(navController: NavController){
    // Bar with title "Babysitters" and filter icon
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Babysitters")
        Icon(modifier = Modifier
            .clickable {  },
            imageVector = Icons.Default.Menu, contentDescription = "Filter", tint = MaterialTheme.colorScheme.primary)
    }

    // Lazy list of babysitters
    LazyColumn(modifier = Modifier) {
        items(babysitters) { babysitter ->
            MoverCard(babysitter = babysitter,navController)
        }
    }

}
package com.example.feature_learn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.common.reuseable.DividerWithText
import com.example.feature_learn.model.TutorLevel
import com.example.feature_learn.model.TutorSubject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun LearnFilter() {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text("Tutor Filter") },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            navigationIcon = {
                IconButton(onClick = { /*navigateBack()*/ }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back button",
                        tint = Color.White
                    )
                }
            })
    }) { paddingValues ->
        Column(

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)


        ) {

            DividerWithText("Subject")
            val subjects = listOf(
                TutorSubject.ENGLISH,
                TutorSubject.MATH,
                TutorSubject.SCIENCE,
                TutorSubject.ART,
                TutorSubject.MUSIC
            )

            // Track the selected radio button
            var selectedSubject by remember { mutableStateOf(TutorSubject.ENGLISH) }

            LazyRow(
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(subjects.size) { index ->
                    val option = subjects[index]
                    RadioButton(selected = selectedSubject == option,
                        onClick = { selectedSubject = option })
                    Text(option.toString().lowercase())
                }
            }
            DividerWithText("Level")

            // Track the selected radio button
            val levels = listOf(
                TutorLevel.ELEMENTARY,
                TutorLevel.MIDDLE,
                TutorLevel.HIGH,
                TutorLevel.COLLEGE
            )

            // Track the selected radio button
            var selectedLevel by remember { mutableStateOf(levels[0]) }

            LazyRow(
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(subjects.size) { index ->
                    val option = levels[index]
                    RadioButton(selected = selectedLevel == option,
                        onClick = { selectedLevel = option })
                    Text(option.toString().lowercase())
                }
            }
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth()
            )
            Button(onClick = {
                //navigateToPetList()
            }) {
                Text(text = "Back to Tutor Map")
            }
        }
    }
}

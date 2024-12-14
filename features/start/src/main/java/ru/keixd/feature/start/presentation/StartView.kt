package ru.keixd.feature.start.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun StartView() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Text(text = "Hello World", modifier = Modifier.padding(paddingValues))
    }
}
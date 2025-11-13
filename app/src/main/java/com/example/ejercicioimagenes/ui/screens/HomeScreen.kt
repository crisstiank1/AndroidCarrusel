package com.example.ejercicioimagenes.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ejercicioimagenes.model.ImageRepository
import com.example.ejercicioimagenes.ui.components.ImageCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onOpenDetails: (Int) -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("Galería") }) }) { padding ->
        LazyRow(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 12.dp,
                top = padding.calculateTopPadding() + 12.dp,
                end = 12.dp,
                bottom = 12.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp) // separación entre cards
        ) {
            items(ImageRepository.items) { item ->
                ImageCard(
                    title = item.title,
                    drawableRes = item.drawableRes,
                    onClick = { onOpenDetails(item.id) },
                    // Tamaño “vertical” (retrato) para que todas se vean altas
                    modifier = Modifier
                        .width(180.dp)
                        .aspectRatio(3f / 4f)
                )
            }
        }
    }
}
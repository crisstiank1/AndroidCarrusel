package com.example.ejercicioimagenes.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ejercicioimagenes.model.ImageRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(id: Int, onBack: () -> Unit) {
    val item = ImageRepository.getById(id)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = item?.title ?: "Detalle") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(android.R.drawable.ic_media_previous),
                            contentDescription = "Atrás"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            if (item != null) {
                Image(
                    painter = painterResource(id = item.drawableRes),
                    contentDescription = item.title,
                    modifier = Modifier.fillMaxWidth().height(240.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(16.dp))
                Text(text = item.title)
                Spacer(Modifier.height(8.dp))
                Text(text = item.description)
            } else {
                Text("Elemento no encontrado")
            }
        }
    }
}

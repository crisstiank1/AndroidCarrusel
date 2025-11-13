// app/src/main/java/com/example/ejercicioimagenes/model/ImageRepository.kt
package com.example.ejercicioimagenes.model
import com.example.ejercicioimagenes.R

object ImageRepository {

    val items = listOf(
        ImageItem(
            id = 1,
            title = "CR7",
            description = "Cristiano Ronaldo",
            drawableRes = R.drawable.cr7
        ),
        ImageItem(
            id = 2,
            title = "Salah",
            description = "Mohamed Salah",
            drawableRes = R.drawable.salah
        ),
        ImageItem(
            id = 3,
            title = "Benzema",
            description = "Karim Benzema",
            drawableRes = R.drawable.benzema
        ),
        ImageItem(
            id = 4,
            title = "Luchito",
            description = "Luis Fernando Diaz Marulanda",
            drawableRes = R.drawable.luchito
        ),
        ImageItem(
            id = 5,
            title = "Arnold",
            description = "Trent Alexander-Arnold",
            drawableRes = R.drawable.arnold
        )
    )

    fun getById(id: Int): ImageItem? = items.firstOrNull { it.id == id }
}

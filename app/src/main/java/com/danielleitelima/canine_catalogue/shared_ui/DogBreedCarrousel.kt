package com.danielleitelima.canine_catalogue.shared_ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danielleitelima.canine_catalogue.domain.catalog.model.DogBreed
import com.danielleitelima.canine_catalogue.domain.catalog.model.DogPhoto

@Composable
fun DogBreedCarrousel(
    dogBreed: DogBreed,
    onPhotoSelected: ((String) -> Unit),
    onFavoriteSelected: (DogPhoto) -> Unit
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = dogBreed.name,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LazyRow(
                modifier = Modifier.fillMaxSize()
                    .height(150.dp),
                contentPadding = PaddingValues(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(dogBreed.photos) { _, item ->
                    ImageCard(
                        contentDescription = "",
                        imageUrl = item.url,
                        onFavoriteSelected = {
                            onFavoriteSelected.invoke(item)
                        },
                        modifier = Modifier
                            .width(150.dp)
                            .clickable {
                                onPhotoSelected.invoke(item.id)
                            }
                    )
                }
            }
        }
    }
}
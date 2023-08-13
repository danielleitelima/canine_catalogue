package com.danielleitelima.canine_catalogue.shared_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danielleitelima.canine_catalogue.domain.catalog.model.DogBreed

@Composable
fun DogBreedView(
    title: String,
    dogBreeds: List<DogBreed>,
    modifier: Modifier = Modifier,
    onPhotoSelected: ((Long?) -> Unit)? = null
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Spacer(modifier = Modifier.height(32.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = title,
                    modifier = Modifier.align(Alignment.Center),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                )
            }
        }

        itemsIndexed(dogBreeds) { _, item ->
            DogBreedCarrousel(
                dogBreed = item,
                onItemSelect = onPhotoSelected
            )
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}


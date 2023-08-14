package com.danielleitelima.canine_catalogue.presentation.favorite

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.danielleitelima.canine_catalogue.shared_ui.DogBreedView

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onPhotoSelected: (String) -> Unit
) {
    DogBreedView(
        title = "Favorites",
        dogBreeds = viewModel.state.dogBreeds,
        modifier = modifier,
        onFavoriteSelected = { dogPhoto ->
            viewModel.updateFavoriteState(dogPhoto)
        },
        onPhotoSelected = onPhotoSelected
    )
}

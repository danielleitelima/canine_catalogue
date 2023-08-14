package com.danielleitelima.canine_catalogue.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.danielleitelima.canine_catalogue.shared_ui.DogBreedView

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onPhotoSelected: (String) -> Unit,
) {
    DogBreedView(
        title = "All dogs",
        dogBreeds = viewModel.state.dogBreeds,
        modifier = modifier,
        onPhotoSelected = onPhotoSelected,
        onFavoriteSelected = { dogPhoto ->
            viewModel.updateFavoriteState(dogPhoto)
        }
    )
}

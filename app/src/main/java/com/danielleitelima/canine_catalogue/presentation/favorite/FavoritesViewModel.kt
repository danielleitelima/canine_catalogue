package com.danielleitelima.canine_catalogue.presentation.favorite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielleitelima.canine_catalogue.domain.catalog.model.DogPhoto
import com.danielleitelima.canine_catalogue.domain.catalog.use_case.GetFavorites
import com.danielleitelima.canine_catalogue.domain.catalog.use_case.ReverseImageFavoriteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    getFavorites: GetFavorites,
    private val reverseImageFavoriteState: ReverseImageFavoriteState
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UiEvent>(replay = 0)
    val uiEvent: SharedFlow<UiEvent> = _uiEvent

    var state by mutableStateOf(FavoritesState())
        private set

    sealed class UiEvent

    init {
        getFavorites()
            .onEach { dogBreeds ->
                state = state.copy(
                    dogBreeds = dogBreeds,
                )
            }
            .launchIn(viewModelScope)
    }

    fun updateFavoriteState(dogPhoto: DogPhoto) {
        viewModelScope.launch {
            reverseImageFavoriteState(dogPhoto)
        }
    }

}
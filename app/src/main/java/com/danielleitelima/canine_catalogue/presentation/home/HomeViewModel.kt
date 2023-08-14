package com.danielleitelima.canine_catalogue.presentation.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielleitelima.canine_catalogue.domain.catalog.model.DogPhoto
import com.danielleitelima.canine_catalogue.domain.catalog.use_case.GetDogBreeds
import com.danielleitelima.canine_catalogue.domain.catalog.use_case.ReverseImageFavoriteState
import com.danielleitelima.canine_catalogue.domain.catalog.use_case.UpdateDogBreedsWithFavorite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getDogBreeds: GetDogBreeds,
    private val reverseImageFavoriteState: ReverseImageFavoriteState,
    private val updateDogBreedsWithFavorite: UpdateDogBreedsWithFavorite,
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UiEvent>(replay = 0)
    val uiEvent: SharedFlow<UiEvent> = _uiEvent

    var state by mutableStateOf(HomeState())
        private set

    sealed class UiEvent

    init {
        getDogBreeds()
            .onEach {
                Log.d("HomeViewModel", "getAll update")
            }
            .onEach { dogBreeds ->
                state = state.copy(
                    dogBreeds = dogBreeds,
                )
            }
            .launchIn(viewModelScope)
    }

    fun updateFavoriteState(dogPhoto: DogPhoto) {
        viewModelScope.launch {

//            Immediately updates the UI
            state = state.copy(
                dogBreeds = updateDogBreedsWithFavorite(
                    dogBreeds = state.dogBreeds,
                    favoriteId = dogPhoto.id,
                )
            )

//            Triggers a side effect
            reverseImageFavoriteState(dogPhoto)
        }
    }

}
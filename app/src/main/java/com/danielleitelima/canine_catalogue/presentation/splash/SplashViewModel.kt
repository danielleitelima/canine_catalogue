package com.danielleitelima.canine_catalogue.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielleitelima.canine_catalogue.domain.catalog.use_case.RefreshDogBreeds
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val refreshDogBreeds: RefreshDogBreeds,
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<SplashEvent>(replay = 0)
    val uiEvent: SharedFlow<SplashEvent> = _uiEvent

    init {
        viewModelScope.launch {
            refreshDogBreeds()
            _uiEvent.emit(SplashEvent.Loaded)
        }
    }

}
package com.danielleitelima.canine_catalogue.datasource.local.datastore.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
@Parcelize
data class DogBreeds(
    val items: List<DogBreed> = emptyList(),
) : Parcelable

@Serializable
@Parcelize
data class DogBreed(
    val id: String  = UUID.randomUUID().toString(),
    val name: String,
    val photos: List<DogPhotoCache> = emptyList(),
) : Parcelable


@Serializable
@Parcelize
data class DogPhotoCache(
    val id: String  = UUID.randomUUID().toString(),
    val url: String,
    val isFavorite: Boolean = false,
) : Parcelable
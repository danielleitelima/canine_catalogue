package com.danielleitelima.canine_catalogue.datasource.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface DogBreedAPI {
    @GET("list/all")
    suspend fun getBreeds(): DogBreedsDTO

    @GET("{breedName}/images")
    suspend fun getPhotos(@Path("breedName") breedName: String): DogPhotoDTO?

}
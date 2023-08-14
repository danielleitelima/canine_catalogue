package com.danielleitelima.canine_catalogue

import android.content.Context
import com.danielleitelima.canine_catalogue.data.catalog.repository.DogPhotoRepositoryImpl
import com.danielleitelima.canine_catalogue.data.catalog.repository.SnapshotDogBreedRepositoryImpl
import com.danielleitelima.canine_catalogue.datasource.local.AppDatabase
import com.danielleitelima.canine_catalogue.datasource.local.dao.DogBreedDAO
import com.danielleitelima.canine_catalogue.datasource.local.dao.DogPhotoDAO
import com.danielleitelima.canine_catalogue.datasource.local.datastore.access.DogBreedsCache
import com.danielleitelima.canine_catalogue.datasource.remote.DogBreedAPI
import com.danielleitelima.canine_catalogue.datasource.remote.SnapshotDogBreedAPI
import com.danielleitelima.canine_catalogue.domain.catalog.repository.DogBreedRepository
import com.danielleitelima.canine_catalogue.domain.catalog.repository.DogPhotoRepository
import com.danielleitelima.canine_catalogue.domain.catalog.use_case.GetDogBreeds
import com.danielleitelima.canine_catalogue.domain.catalog.use_case.GetFavorites
import com.danielleitelima.canine_catalogue.domain.catalog.use_case.RefreshDogBreeds
import com.danielleitelima.canine_catalogue.domain.catalog.use_case.ReverseImageFavoriteState
import com.danielleitelima.canine_catalogue.domain.catalog.use_case.UpdateDogBreedsWithFavorite
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Singleton
//    @Provides
//    fun provideBaseUrl(): String {
//        return "https://dog.ceo/api/breeds/"
//    }

    @Singleton
    @Provides
    fun provideBaseUrl(): String {
        return "https://danielleitelima.github.io/canine_catalogue/"
    }

    @Singleton
    @Provides
    fun provideConvertorFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): DogBreedAPI {
        return retrofit.create(DogBreedAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        baseUrl: String,
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
        return retrofit.build()
    }

    @Singleton
    @Provides
    fun provideSnapshotDogBreedAPI(retrofit: Retrofit): SnapshotDogBreedAPI {
        return retrofit.create(SnapshotDogBreedAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideDogBreedDAO(@ApplicationContext context: Context): DogBreedDAO {
        return AppDatabase.getInstance(context).dogBreedDAO
    }

    @Singleton
    @Provides
    fun provideDogPhotoDAO(@ApplicationContext context: Context): DogPhotoDAO {
        return AppDatabase.getInstance(context).dogPhotoDAO
    }

    @Singleton
    @Provides
    fun provideDogBreedsCache(@ApplicationContext context: Context): DogBreedsCache {
        return DogBreedsCache(context)
    }

    @Singleton
    @Provides
    fun provideDogPhotoRepository(
        dogBreedsCache: DogBreedsCache,
    ): DogPhotoRepository {
        return DogPhotoRepositoryImpl(
            dogBreedsCache,
        )
    }

    @Singleton
    @Provides
    fun provideDogBreedRepository(
        snapshotDogBreedAPI: SnapshotDogBreedAPI,
        dogBreedsCache: DogBreedsCache,
    ): DogBreedRepository {
        return SnapshotDogBreedRepositoryImpl(
            snapshotDogBreedAPI,
            dogBreedsCache
        )
    }

    @Singleton
    @Provides
    fun provideUpdateDogBreedsWithFavorite(): UpdateDogBreedsWithFavorite {
        return UpdateDogBreedsWithFavorite()
    }

    @Singleton
    @Provides
    fun provideGetDogBreeds(
        dogBreedRepository: DogBreedRepository
    ): GetDogBreeds {
        return GetDogBreeds(dogBreedRepository)
    }

    @Singleton
    @Provides
    fun provideRefreshDogBreeds(
        dogBreedRepository: DogBreedRepository
    ): RefreshDogBreeds {
        return RefreshDogBreeds(dogBreedRepository)
    }

    @Singleton
    @Provides
    fun provideGetFavorites(
        dogBreedRepository: DogBreedRepository
    ): GetFavorites {
        return GetFavorites(dogBreedRepository)
    }

    @Singleton
    @Provides
    fun provideSetImageFavoriteState(
        dogPhotoRepository: DogPhotoRepository
    ): ReverseImageFavoriteState {
        return ReverseImageFavoriteState(dogPhotoRepository)
    }

}
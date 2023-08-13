package com.danielleitelima.canine_catalogue.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.danielleitelima.canine_catalogue.datasource.local.model.DogPhotoEntity

@Dao
interface DogPhotoDAO {

    @Query("SELECT * FROM tb_dog_photo WHERE id = :id LIMIT 1")
    fun get(id: String): DogPhotoEntity

    @Update
    suspend fun update(photo: DogPhotoEntity)

    @Query("DELETE FROM tb_dog_photo")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(breedList: List<DogPhotoEntity>)

    @Query("SELECT * FROM tb_dog_photo WHERE dogBreedId = :dogBreedId")
    suspend fun getByDogBreedId(dogBreedId: Long): List<DogPhotoEntity>

}
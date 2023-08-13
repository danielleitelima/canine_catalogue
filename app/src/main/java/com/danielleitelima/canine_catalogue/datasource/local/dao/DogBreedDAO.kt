package com.danielleitelima.canine_catalogue.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.danielleitelima.canine_catalogue.datasource.local.model.DogBreedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DogBreedDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(breedList: List<DogBreedEntity>)

    @Query("SELECT * FROM tb_dog_breed ORDER BY name ASC")
    fun getAll(): Flow<List<DogBreedEntity>>

    @Query("SELECT * FROM tb_dog_breed WHERE id = :id LIMIT 1")
    fun get(id: String): Flow<DogBreedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(breed: DogBreedEntity): Long

    @Update
    suspend fun update(breed: DogBreedEntity)

    @Query("DELETE FROM tb_dog_breed")
    suspend fun deleteAll()

}
package com.danielleitelima.canine_catalogue.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.danielleitelima.canine_catalogue.datasource.local.dao.DogBreedDAO
import com.danielleitelima.canine_catalogue.datasource.local.dao.DogPhotoDAO
import com.danielleitelima.canine_catalogue.datasource.local.model.DogBreedEntity
import com.danielleitelima.canine_catalogue.datasource.local.model.DogPhotoEntity

@Database(
    entities = [
        DogBreedEntity::class,
        DogPhotoEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val dogBreedDAO: DogBreedDAO
    abstract val dogPhotoDAO: DogPhotoDAO

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_db"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }

}
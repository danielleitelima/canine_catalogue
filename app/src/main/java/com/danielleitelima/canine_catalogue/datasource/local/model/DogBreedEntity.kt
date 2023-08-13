package com.danielleitelima.canine_catalogue.datasource.local.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tb_dog_breed")
data class DogBreedEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val name: String
) : Parcelable

@Parcelize
@Entity(
    tableName = "tb_dog_photo",
    foreignKeys = [
        ForeignKey(
            entity = DogBreedEntity::class,
            parentColumns = ["id"],
            childColumns = ["dogBreedId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DogPhotoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val url: String,
    val dogBreedId: Long, // This is the foreign key linking to DogBreedEntity
    var isFavorite: Boolean = false
) : Parcelable

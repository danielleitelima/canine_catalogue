package com.danielleitelima.canine_catalogue.datasource.local.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
@Entity(tableName = "tb_dog_breed")
data class DogBreedEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val name: String
) : Parcelable

@Parcelize
@Entity(
    tableName = "tb_dog_photo",
    foreignKeys = [
        ForeignKey(
            entity = DogBreedEntity::class,
            parentColumns = ["id"],
            childColumns = ["breedId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DogPhotoEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val url: String,
    val dogBreedId: String, // This is the foreign key linking to DogBreedEntity
    var isFavorite: Boolean = false
) : Parcelable

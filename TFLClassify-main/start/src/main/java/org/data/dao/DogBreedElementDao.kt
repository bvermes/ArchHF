package org.data.dao

import androidx.room.*
import org.data.entities.DogBreedElement

@Dao
interface DogBreedElementDao {
    @Query("SELECT * FROM dogbreedelement")
    fun getAll(): List<DogBreedElement>

    @Insert
    fun insert(dogBreedElement: DogBreedElement): Long

    @Update
    fun update(dogBreedElement: DogBreedElement)

    @Delete
    fun deleteItem(dogBreedElement: DogBreedElement)
}
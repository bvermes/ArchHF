package org.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.data.dao.DogBreedElementDao
import org.data.entities.DogBreedElement

@Database(entities = [DogBreedElement::class], version = 1)
abstract class DogBreedListDatabase : RoomDatabase() {
    abstract fun dogBreedElementDao(): DogBreedElementDao

    companion object {
        fun getDatabase(applicationContext: Context): DogBreedListDatabase {
            return Room.databaseBuilder(
                applicationContext,
                DogBreedListDatabase::class.java,
                "dogbreed-list"
            ).build();
        }
    }
}
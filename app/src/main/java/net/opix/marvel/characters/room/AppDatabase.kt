package net.opix.marvel.characters.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CharacterEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): CharacterDao
}
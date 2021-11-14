package net.opix.marvel.characters.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import net.opix.marvel.characters.model.Converters

@TypeConverters(Converters::class)
@Database(entities = [CharacterEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): CharacterDao
}
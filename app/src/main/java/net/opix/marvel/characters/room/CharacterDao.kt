package net.opix.marvel.characters.room

import androidx.room.*

@Dao
interface CharacterDao {
    @Query("SELECT * FROM CharacterEntity")
    suspend fun getAll(): List<CharacterEntity>

    @Query("DELETE FROM CharacterEntity")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(events: List<CharacterEntity>)

    @Delete
    suspend fun delete(event: CharacterEntity)
}
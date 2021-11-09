package net.opix.marvel.characters.room

interface DatabaseHelper {
    suspend fun getCharacters(): List<CharacterEntity>
    suspend fun insertAll(events: List<CharacterEntity>)
    suspend fun deleteAll()
}
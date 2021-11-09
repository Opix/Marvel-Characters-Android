package net.opix.marvel.characters.room

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {
    override suspend fun getCharacters(): List<CharacterEntity> = appDatabase.eventDao().getAll()
    override suspend fun insertAll(events: List<CharacterEntity>) = appDatabase.eventDao().insertAll(events)
    override suspend fun deleteAll() = appDatabase.eventDao().deleteAll()
}
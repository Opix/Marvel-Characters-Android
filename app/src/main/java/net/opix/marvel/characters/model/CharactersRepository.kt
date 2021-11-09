package net.opix.marvel.characters.model

import net.opix.marvel.characters.room.DatabaseHelper
import net.opix.marvel.characters.service.CharacterService
import net.opix.marvel.characters.service.RepositoryRequestStatus
import net.opix.marvel.characters.service.RepositoryResult
import net.opix.marvel.characters.service.ServiceFactory
import java.lang.Exception

class CharactersRepository(private val service: CharacterService = ServiceFactory.createService()) {
    private var cachedCharacters = emptyList<Character>()

    suspend fun getCharacterDtos(): List<CharacterDto> {
        return service.getCharacters()
    }

    suspend fun getCharacters(dbHelper: DatabaseHelper): RepositoryResult<List<Character>> {
        return try {
            val tempo = getCharacterDtos()
            cachedCharacters = tempo.map { event -> Character.fromDto(event) }.sortedByDescending { event -> event.date }

            val entities = tempo.map { event -> event.toEntity() }
            dbHelper.insertAll(entities)

            RepositoryResult(cachedCharacters, RepositoryRequestStatus.COMPLETE)
        } catch (e: Exception) {
            cachedCharacters = dbHelper.getCharacters().map { event -> Character.fromEntity(event) }.sortedByDescending { event -> event.date }

            if (cachedCharacters.count() == 0)
                RepositoryResult(emptyList(), RepositoryRequestStatus.Error(e))
            else
                RepositoryResult(cachedCharacters, RepositoryRequestStatus.COMPLETE)
        }
    }

    fun getCharacter(id: Int): Character? {
        return cachedCharacters.firstOrNull {
            it.id == id
        }
    }

    companion object {
        @Volatile
        private var instance: CharactersRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: CharactersRepository(ServiceFactory.createService()).also { instance = it }
        }
    }
}
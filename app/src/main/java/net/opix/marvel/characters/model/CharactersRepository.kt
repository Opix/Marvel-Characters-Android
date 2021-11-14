package net.opix.marvel.characters.model

import net.opix.marvel.characters.BuildConfig
import net.opix.marvel.characters.extensions.md5Hash
import net.opix.marvel.characters.room.DatabaseHelper
import net.opix.marvel.characters.service.CharacterService
import net.opix.marvel.characters.service.RepositoryRequestStatus
import net.opix.marvel.characters.service.RepositoryResult
import net.opix.marvel.characters.service.ServiceFactory
import java.lang.Exception

class CharactersRepository(private val service: CharacterService = ServiceFactory.createService()) {
    private var cachedCharacters = emptyList<Character>()

    suspend fun getApiCharacters(offset: Int = 0): ApiResponse {
        val (timestamp, hash) = makeHash()
        return service.getCharacters(timestamp, hash, offset)
    }

    suspend fun getCharacters(dbHelper: DatabaseHelper, offset: Int = 0): RepositoryResult<List<Character>> {
        return try {
            val tempo = getApiCharacters(offset)
            cachedCharacters = tempo.data.results.map { character -> character.toCharacter() }.sortedBy { character -> character.name }

            val entities = tempo.data.results.map { character -> character.toCharacter().toEntity() }
            dbHelper.insertAll(entities)

            RepositoryResult(cachedCharacters, RepositoryRequestStatus.COMPLETE)
        } catch (e: Exception) {
            cachedCharacters = dbHelper.getCharacters().map { character -> character.toCharacter() }.sortedBy { character -> character.name }

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
    // Returns timestamp and hash
    private fun makeHash(): Pair<String, String> {
        val timeStamp = System.currentTimeMillis().toString()

        return Pair(timeStamp, "$timeStamp${BuildConfig.MARVEL_PRIVATE_KEY}${BuildConfig.MARVEL_PUBLIC_KEY}".md5Hash)
    }

    companion object {
        @Volatile
        private var instance: CharactersRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: CharactersRepository(ServiceFactory.createService()).also { instance = it }
        }
    }
}
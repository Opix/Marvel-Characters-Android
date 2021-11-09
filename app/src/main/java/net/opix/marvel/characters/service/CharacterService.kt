package net.opix.marvel.characters.service

import net.opix.marvel.characters.model.CharacterDto
import retrofit2.http.GET

interface CharacterService {
    @GET("feed.json")
    suspend fun getCharacters(): List<CharacterDto>
}
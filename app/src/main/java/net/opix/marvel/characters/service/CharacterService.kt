package net.opix.marvel.characters.service

import net.opix.marvel.characters.BuildConfig
import net.opix.marvel.characters.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {
    @GET("characters")
    suspend fun getCharacters(@Query("ts") timestamp: String,
                              @Query("hash") hash: String,
                              @Query("offset") offset: Int = 0,
                              @Query("limit") limit: Int = 100,
                              @Query("apikey") apikey: String = BuildConfig.MARVEL_PUBLIC_KEY): ApiResponse
}
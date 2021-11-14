package net.opix.marvel.characters.model

import com.google.gson.annotations.SerializedName

data class ApiData(
    @SerializedName("results") val results: List<ApiCharacter>,
    @SerializedName("total") val total: Int)

package net.opix.marvel.characters.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("data") val data: ApiData,
    @SerializedName("code") val code: Int,
    @SerializedName("etag") val etag: String)

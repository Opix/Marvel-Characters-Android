package net.opix.marvel.characters.model

import com.google.gson.annotations.SerializedName

data class ApiSummary (
    @SerializedName("items") val items: List<ApiItem>?,
    @SerializedName("available") val available: Int,
    @SerializedName("collectionURI") val collectionURI: String)
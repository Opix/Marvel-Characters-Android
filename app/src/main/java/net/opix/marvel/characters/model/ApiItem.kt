package net.opix.marvel.characters.model

import com.google.gson.annotations.SerializedName

data class ApiItem(
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("name") val name: String)

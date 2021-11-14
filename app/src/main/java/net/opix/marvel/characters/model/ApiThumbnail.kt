package net.opix.marvel.characters.model

import com.google.gson.annotations.SerializedName

const val thumbnailKey = "standard_small" // 65 x 45px
const val largeImageKey = "landscape_large" // 190 x 140px

data class ApiThumbnail(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val ext: String) {

    val thumbnailUrl: String
        get() {
            return "$path/$thumbnailKey.$ext".replace("http:", "https:", true)
        }

    val largeImageUrl: String
        get() {
            return "$path/$largeImageKey.$ext".replace("http:", "https:", true)
        }
}

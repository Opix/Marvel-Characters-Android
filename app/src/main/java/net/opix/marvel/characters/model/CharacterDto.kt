package net.opix.marvel.characters.model

import com.google.gson.annotations.SerializedName
import net.opix.marvel.characters.room.CharacterEntity

// Data Transfer Object
data class CharacterDto(
    @SerializedName("id") val id: Int,
    @SerializedName("description") val description: String?,
    @SerializedName("title") val title: String,
    @SerializedName("timestamp") val timestamp: String,
    @SerializedName("image") val image: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("date") val date: String,
    @SerializedName("locationline1") val locationLine1: String?,
    @SerializedName("locationline2") val locationLine2: String?) {

    fun toEntity(): CharacterEntity {
        return CharacterEntity(id, description, title, timestamp, image, phone, date, locationLine1, locationLine2)
    }
}
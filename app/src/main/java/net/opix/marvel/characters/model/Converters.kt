package net.opix.marvel.characters.model

import androidx.room.TypeConverter
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    @ExperimentalSerializationApi
    @TypeConverter
    fun fromList(value : List<String>) = Json.encodeToString(value)

    @ExperimentalSerializationApi
    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<List<String>>(value)
}
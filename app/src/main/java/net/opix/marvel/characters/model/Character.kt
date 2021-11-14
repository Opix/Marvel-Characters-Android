package net.opix.marvel.characters.model

import net.opix.marvel.characters.extensions.localDateAsString
import net.opix.marvel.characters.room.CharacterEntity
import java.io.Serializable
import java.util.*

// Domain Object
data class Character(
    val id: Int,
    val description: String,
    val name: String,
    val modified: Date,
    val thumbnail: String,
    val largeImage: String,
    val comics: List<String>,
    val stories: List<String>,
    val events: List<String>,
    val series: List<String>) : Serializable {

    val firstComic: String
        get() {
            if (comics.size > 0) {
                return comics[0]
            }
            return ""
        }

    fun toEntity(): CharacterEntity {
        return CharacterEntity(id,
            description,
            name,
            modified.localDateAsString,
            thumbnail,
            largeImage,
            comics,
            stories,
            events,
            series)
    }
}

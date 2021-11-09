package net.opix.marvel.characters.model

import net.opix.marvel.characters.extensions.eventDateFormat
import net.opix.marvel.characters.extensions.dateFromISO8601WithMilliseconds
import net.opix.marvel.characters.extensions.localDateAsString
import net.opix.marvel.characters.room.CharacterEntity
import java.io.Serializable
import java.util.*

// Domain Object
data class Character(
    val id: Int,
    val description: String?,
    val title: String,
    val timestamp: Date,
    val image: String?,
    val phone: String?,
    val date: Date,
    val locationLine1: String?,
    val locationLine2: String?) : Serializable {

    companion object {
        fun fromDto(item: CharacterDto): Character {
            return Character(
                item.id,
                item.description,
                item.title,
                item.timestamp.dateFromISO8601WithMilliseconds ?: Date(),
                item.image,
                item.phone,
                item.date.dateFromISO8601WithMilliseconds ?: Date(),
                item.locationLine1,
                item.locationLine2)
        }

        fun fromEntity(item: CharacterEntity): Character {
            return Character(
                item.id,
                item.description,
                item.title,
                item.timestamp.dateFromISO8601WithMilliseconds ?: Date(),
                item.image,
                item.phone,
                item.date.dateFromISO8601WithMilliseconds ?: Date(),
                item.locationLine1,
                item.locationLine2)
        }
    }

    val dateAsString: String
        get() {
            return date.eventDateFormat
        }

    val combinedLocation: String?
        get() {
            if (!locationLine1.isNullOrBlank()) {
                if (!locationLine2.isNullOrBlank())
                    return "${locationLine1}, ${locationLine2}"
                else
                    return locationLine1
            }
            return locationLine2
        }
}

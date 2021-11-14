package net.opix.marvel.characters.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import net.opix.marvel.characters.extensions.dateFromISO8601WithMilliseconds
import net.opix.marvel.characters.model.Character
import java.util.*

@Entity
data class CharacterEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "modified") val modified: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "largeImage") val largeImage: String,
    @ColumnInfo(name = "comics") val comics: List<String>,
    @ColumnInfo(name = "stories") val stories: List<String>,
    @ColumnInfo(name = "events") val events: List<String>,
    @ColumnInfo(name = "series") val series: List<String>
) {

    fun toCharacter(): Character {
        return Character(id,
            description,
            name,
            modified.dateFromISO8601WithMilliseconds ?: Date(),
            thumbnail,
            largeImage,
            comics,
            stories,
            events,
            series)
    }
}

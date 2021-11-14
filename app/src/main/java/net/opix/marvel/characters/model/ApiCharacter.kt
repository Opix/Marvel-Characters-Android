package net.opix.marvel.characters.model

import com.google.gson.annotations.SerializedName
import net.opix.marvel.characters.extensions.dateFromISO8601WithMilliseconds
import java.util.*

// Data Transfer Object
data class ApiCharacter(
    @SerializedName("id") val id: Int,
    @SerializedName("description") val description: String,
    @SerializedName("name") val name: String,
    @SerializedName("modified") val modified: String,
    @SerializedName("thumbnail") val thumbnail: ApiThumbnail,
    @SerializedName("comics") val comics: ApiSummary,
    @SerializedName("stories") val stories: ApiSummary,
    @SerializedName("events") val events: ApiSummary,
    @SerializedName("series") val series: ApiSummary) {

    fun toCharacter(): Character {
        val flattenedComics = comics.items?.filter { comic -> !comic.name.isEmpty() }?.map { one -> one.name } ?: emptyList<String>()
        val flattenedStories = stories.items?.filter { story -> !story.name.isEmpty() }?.map { one -> one.name } ?: emptyList<String>()
        val flattenedEvents = events.items?.filter { event -> !event.name.isEmpty() }?.map { one -> one.name } ?: emptyList<String>()
        val flattenedSeries = series.items?.filter { series1 -> !series1.name.isEmpty() }?.map { one -> one.name } ?: emptyList<String>()

        return Character(id,
            description,
            name,
            modified.dateFromISO8601WithMilliseconds ?: Date(),
            thumbnail.thumbnailUrl,
            thumbnail.largeImageUrl,
            flattenedComics,
            flattenedStories,
            flattenedEvents,
            flattenedSeries)
    }
}
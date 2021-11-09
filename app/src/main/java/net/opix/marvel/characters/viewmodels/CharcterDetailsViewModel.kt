package net.opix.marvel.characters.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import net.opix.marvel.characters.model.Character
import net.opix.marvel.characters.model.CharactersRepository

class CharacterDetailsViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    var eventState = mutableStateOf<Character?>(null)
    private val repository: CharactersRepository = CharactersRepository.getInstance()

    init {
        val eventId = savedStateHandle.get<Int>("event_id") ?: 0
        eventState.value = repository.getCharacter(eventId)
    }

    val event: Character?
        get() {
            return eventState.value
        }

    fun getPhone(): String? {
        return event?.phone
    }

    fun generateMessage(): String? {
        event?.let { event ->
            var message = event.title
            message += System.lineSeparator()
            message += System.lineSeparator()
            message += event.dateAsString
            message += System.lineSeparator()
            message += System.lineSeparator()
            message += event.phone ?: ""
            message += System.lineSeparator()
            message += System.lineSeparator()
            message += event.description ?: ""

            return message
        } ?: return null
    }
}
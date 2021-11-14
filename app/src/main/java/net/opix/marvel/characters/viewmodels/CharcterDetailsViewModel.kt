package net.opix.marvel.characters.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import net.opix.marvel.characters.model.Character
import net.opix.marvel.characters.model.CharactersRepository

class CharacterDetailsViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    var characterState = mutableStateOf<Character?>(null)
    private val repository: CharactersRepository = CharactersRepository.getInstance()

    init {
        val characterId = savedStateHandle.get<Int>("character_id") ?: 0
        characterState.value = repository.getCharacter(characterId)
    }

    val character: Character?
        get() {
            return characterState.value
        }

    val name: String
        get() {
            return character?.name ?: "N/A"
        }

    val description: String
        get() {
            return character?.description ?: "N/A"
        }

    val thumbnail: String
        get() {
            return character?.thumbnail ?: ""
        }

    val largeImage: String
        get() {
            return character?.largeImage ?: ""
        }
}
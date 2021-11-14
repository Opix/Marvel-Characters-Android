package net.opix.marvel.characters.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import net.opix.marvel.characters.model.CharactersRepository
import net.opix.marvel.characters.model.Character
import net.opix.marvel.characters.room.DatabaseHelper
import net.opix.marvel.characters.service.RepositoryRequestStatus
import net.opix.marvel.characters.service.RepositoryResult

class CharactersViewModel(private val repository: CharactersRepository = CharactersRepository.getInstance()) :
    ViewModel() {
    private val mutableCharactersState: MutableState<RepositoryResult<List<Character>>> = mutableStateOf(RepositoryResult(emptyList(), RepositoryRequestStatus.FETCHING))
    // This is exposed as immutable in MainActivity.  mutableCharactersState above is private so MainActivity will not see directly.
    // Another approach:
    // private val resultState: State<RepositoryResult<List<Character>>> = mutableCharactersState
    val charactersState: RepositoryResult<List<Character>>
        get() {
            return mutableCharactersState.value
        }

    fun fetchCharacters(dbHelper: DatabaseHelper) {
        viewModelScope.launch(Dispatchers.IO) {
            mutableCharactersState.value = repository.getCharacters(dbHelper)
        }
    }
}
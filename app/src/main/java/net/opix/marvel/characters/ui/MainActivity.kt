package net.opix.marvel.characters.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import net.opix.marvel.characters.R
import net.opix.marvel.characters.room.DatabaseBuilder
import net.opix.marvel.characters.room.DatabaseHelperImpl
import net.opix.marvel.characters.service.RepositoryRequestStatus
import net.opix.marvel.characters.ui.theme.MarvelCharactersTheme
import net.opix.marvel.characters.ui.theme.defaultPadding
import net.opix.marvel.characters.viewmodels.CharacterDetailsViewModel
import net.opix.marvel.characters.viewmodels.CharactersViewModel
import net.opix.marvel.characters.ui.misc.ShowError
import net.opix.marvel.characters.ui.misc.ShowCircularProgress
import net.opix.marvel.characters.model.Character

class MainActivity : ComponentActivity() {
    @ExperimentalUnitApi
    @ExperimentalCoilApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MarvelCharactersTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    CharacterScreen()
                }
            }
        }
    }
}

@ExperimentalUnitApi
@ExperimentalCoilApi
@Composable
private fun CharacterScreen() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = "character_list") {
        composable(route = "character_list") {
            CharacterListScreen { eventId ->
                navigationController.navigate("character_details/$eventId")
            }
        }

        composable(
            route = "character_details/{character_id}",
            arguments = listOf(navArgument("character_id") {
                type = NavType.IntType
            })
        ) {
            val characterDetailsViewModel: CharacterDetailsViewModel = viewModel()
            CharacterDetailsPage(characterDetailsViewModel, onBack = {
                navigationController.popBackStack()
            })
        }
    }
}

@ExperimentalUnitApi
@ExperimentalCoilApi
@Composable
fun CharacterListScreen(navigationCallback: (Int) -> Unit) {
    val charactersViewModel: CharactersViewModel = viewModel()
    val dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(LocalContext.current))

    charactersViewModel.fetchCharacters(dbHelper)
    val allCharacters = charactersViewModel.charactersState

    when (allCharacters.requestStatus) {
        is RepositoryRequestStatus.FETCHING -> ShowCircularProgress()
        is RepositoryRequestStatus.COMPLETE -> Content(allCharacters.data, navigationCallback)
        is RepositoryRequestStatus.Error -> ShowError()
    }
}

@ExperimentalUnitApi
@ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MarvelCharactersTheme {
        CharacterListScreen {

        }
    }
}

@ExperimentalUnitApi
@ExperimentalCoilApi
@Composable
fun Content(list: List<Character>, navigationCallback: (Int) -> Unit) {
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp.dp >= 600.dp

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = Color.White
                    )
                },
                navigationIcon = null,
                backgroundColor = colorResource(id = R.color.top_app_bar),
                contentColor = Color.White,
                elevation = 12.dp
            )
        }, content = {
            LazyColumn (contentPadding = PaddingValues(defaultPadding)) {
                if (isTablet) {
                    itemsIndexed(list) { index, item ->
                        val i = index * 2

                        if (i < list.count()) {
                            val i2 = i + 1

                            val event2: Character? = if (i2 >= list.count()) null else list[i2]
                            CharacterRowDouble(list[i], event2, navigationCallback)
                        }
                    }
                } else {
                    items(list) { event ->
                        CharacterRow(event, navigationCallback)
                    }
                }
            }
        })
}
package net.opix.marvel.characters.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import coil.annotation.ExperimentalCoilApi
import net.opix.marvel.characters.model.Character
import net.opix.marvel.characters.ui.theme.defaultHalfPadding

@ExperimentalUnitApi
@ExperimentalCoilApi
@Composable
fun CharacterRowDouble(event1: Character, event2: Character?, navigationCallback: (Int) -> Unit) {
    Row (horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()) {
        Column (Modifier.weight(1f).padding(end = defaultHalfPadding)) {
            CharacterRow(event1, navigationCallback)
        }
        Column (Modifier.weight(1f).padding(start = defaultHalfPadding)) {
            if (event2 != null)
                CharacterRow(event2, navigationCallback)
        }
    }
}
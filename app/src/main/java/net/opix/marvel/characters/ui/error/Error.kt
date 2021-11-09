package net.opix.marvel.characters.ui.misc

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.opix.marvel.characters.R
import net.opix.marvel.characters.ui.theme.defaultPadding

@Composable
fun ShowError() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.error),
            modifier = Modifier
                .padding(defaultPadding)
                .align(Alignment.CenterHorizontally)
                .size(64.dp),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.Red))

        Text(
            stringResource(id = R.string.network_error),
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(defaultPadding),
            Color.Red,
            24.sp,
            textAlign = TextAlign.Center)
    }
}
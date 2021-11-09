package net.opix.marvel.characters.ui

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.skydoves.landscapist.glide.GlideImage
import net.opix.marvel.characters.R
import net.opix.marvel.characters.ui.theme.defaultHalfPadding
import net.opix.marvel.characters.ui.theme.defaultPadding
import net.opix.marvel.characters.ui.theme.defaultIconSize
import net.opix.marvel.characters.viewmodels.CharacterDetailsViewModel

@ExperimentalUnitApi
@Composable
fun DetailsBody(viewModel: CharacterDetailsViewModel) {
    Column(verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.verticalScroll(rememberScrollState())) {
        GlideImage(
            imageModel = viewModel.event?.image,
            contentScale = ContentScale.FillWidth,
            placeHolder = ImageBitmap.imageResource(R.drawable.placeholder_nomoon),
            error = ImageBitmap.imageResource(R.drawable.placeholder_nomoon),
            modifier = Modifier.fillMaxWidth())

        // The image above is not padded intentioanlly.
        Column (Modifier.padding(horizontal = defaultPadding)) {
                DetailsText(viewModel.event?.dateAsString, 15)

                DetailsText(viewModel.event?.title, 28, FontWeight.Bold)

                DetailsText(viewModel.event?.combinedLocation, 15)

                DetailsText(viewModel.event?.description,17)
            }
        }
}

@ExperimentalUnitApi
@Composable
fun CharacterDetailsPage(viewModel: CharacterDetailsViewModel, onBack: () -> Unit) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = {
                        onBack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back),
                            Modifier.size(defaultIconSize),
                            tint = Color.White
                        )
                    }
                },

                actions = {
                    ShowActions(onCall = {
                        viewModel.getPhone()?.let {
                            val intent = Intent(Intent.ACTION_DIAL)
                            intent.setData(Uri.parse("tel:" + it))
                            context.startActivity(intent)
                        }
                    }, onShare = {
                        viewModel.generateMessage()?.let {
                            share(context, it)
                        }
                    })
                },

                elevation = 0.dp,
                backgroundColor = Color.Transparent,
            )
        }) {
        DetailsBody(viewModel)
    }
}

@SuppressLint("QueryPermissionsNeeded")
fun share(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(
        Intent.EXTRA_TEXT,
        message)

    shareIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.share_event))

    // Verify that the intent will resolve to an activity.
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        try {
            context.startActivity(shareIntent)
        } catch (e: ActivityNotFoundException) {
        }
    }
}

@Composable
private fun ShowActions(onCall: () -> Unit, onShare: () -> Unit) {
    Row (horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.padding(defaultPadding)) {

        IconButton(onClick = {
            onCall()
        }, Modifier
            .size(defaultIconSize)) {
            Icon(
                imageVector = Icons.Filled.Call,
                contentDescription = stringResource(id = R.string.call),
                Modifier.size(defaultIconSize),
                tint = Color.White
            )
        }

        IconButton(onClick = {
            onShare()
        }, Modifier
            .padding(horizontal = defaultPadding)
            .size(defaultIconSize)) {
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = stringResource(id = R.string.share),
                Modifier.size(defaultIconSize),
                tint = Color.White
            )
        }
    }
}

@ExperimentalUnitApi
@Composable
fun DetailsText(text: String?, fontSize: Int, fontWeight: FontWeight = FontWeight.Normal) {
    Text(text ?: stringResource(id = R.string.not_applicable),
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .padding(vertical = defaultHalfPadding),
        style = TextStyle(Color.White, fontSize = fontSize.sp, fontWeight = fontWeight, lineHeight = TextUnit(24f, TextUnitType.Sp))
    )
}

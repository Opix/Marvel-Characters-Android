package net.opix.marvel.characters.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import coil.annotation.ExperimentalCoilApi
import com.skydoves.landscapist.glide.GlideImage
import net.opix.marvel.characters.R
import net.opix.marvel.characters.model.Character
import net.opix.marvel.characters.ui.theme.background
import net.opix.marvel.characters.ui.theme.defaultHalfPadding
import net.opix.marvel.characters.ui.theme.defaultPadding

@ExperimentalUnitApi
@ExperimentalCoilApi
@Composable
fun CharacterRow(event: Character, navigationCallback: (Int) -> Unit) {
    Card (shape = RoundedCornerShape(12.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .clickable {
                navigationCallback(event.id)
            }
            .padding(vertical = defaultHalfPadding)
    ) {
        Box {
            GlideImage(
                imageModel = event.image,
                contentScale = ContentScale.Crop,
                placeHolder = ImageBitmap.imageResource(R.drawable.placeholder_nomoon),
                error = ImageBitmap.imageResource(R.drawable.placeholder_nomoon),
                modifier = Modifier.background(background).fillMaxHeight().alpha(0.8f))

            Column(verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(defaultPadding)
                    .wrapContentHeight()) {
                CardText(event.dateAsString, 14)

                CardText(event.title, 24, FontWeight.Bold)

                CardText(
                    event.combinedLocation,
                    14,)
                CardText(event.description, 16,)
            }
        }
    }
}

@ExperimentalUnitApi
@Composable
fun CardText(text: String?, fontSize: Int, fontWeight: FontWeight = FontWeight.Normal) {
    val shadow = Shadow(color = Color.Black, offset = Offset(2f, 2f), blurRadius = 2f)

    Text(text ?: stringResource(id = R.string.not_applicable),
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .padding(vertical = defaultHalfPadding),
        style = TextStyle(Color.White, fontSize = fontSize.sp, shadow = shadow, fontWeight = fontWeight, lineHeight = TextUnit(24f, TextUnitType.Sp))
    )
}

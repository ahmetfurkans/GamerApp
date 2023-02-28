package com.huawei.gamer.feed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.huawei.gamer.remote.Game
import com.huawei.gamer.ui.theme.LocalSpacing


@Composable
fun GameCard(game: Game) {
    val spacing = LocalSpacing.current

    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color(0XFF1D1D1D),
        modifier = Modifier
            .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceSmall)
            .size(180.dp, 200.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
        ) {
            AsyncImage(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                contentScale = ContentScale.Crop,
                model = game.backgroundImage, contentDescription = game.name
            )
            Text(
                modifier = androidx.compose.ui.Modifier
                    .padding(spacing.spaceSmall),
                color = MaterialTheme.colors.onSurface,
                text = game.name,
                style = MaterialTheme.typography.h5,
                maxLines = 1
            )
            Box(
                modifier = Modifier
                    .padding(horizontal = spacing.spaceSmall, vertical = spacing.spaceExtraSmall),
            ) {
                RatingText(
                    rating = game.rating
                )
            }

        }
    }
}


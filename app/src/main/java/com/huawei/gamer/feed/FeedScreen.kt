package com.huawei.gamer.feed

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.huawei.gamer.ui.theme.LocalSpacing

@Composable
fun FeedScreen(viewModel: FeedViewModel, context: Context) {
    val spacing = LocalSpacing.current

    LaunchedEffect(viewModel) {
        viewModel.loadGames(context)
    }

    val list by viewModel.items.observeAsState(emptyList())

    Column() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(MaterialTheme.colors.background)
        ) {
            Text(
                "Games",
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(spacing.spaceSmall),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onSurface
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp)
        ) {
            items(list) { item ->
                when (item) {
                    is FeedItem.Ad -> AdCard(nativeAd = item.nativeAd)
                    is FeedItem.Feed -> GameCard(game = item.game)
                }
            }
        }
    }
}

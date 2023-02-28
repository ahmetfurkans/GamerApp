package com.huawei.gamer.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.huawei.gamer.ui.theme.LocalSpacing
import com.huawei.hms.ads.nativead.NativeAd
import com.huawei.hms.ads.nativead.NativeView

@Composable
fun AdCard(nativeAd: NativeAd) {
    val spacing = LocalSpacing.current

    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier
            .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceSmall)
            .size(180.dp, 200.dp)
    ) {
        AndroidView(
            factory = { context ->
                NativeView(context)
            },
            update = {
                val composeView = ComposeView(it.context)
                it.setNativeAd(nativeAd)
                it.removeAllViews()
                it.addView(composeView)
                composeView.setContent {
                    Column {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp),
                            contentScale = ContentScale.Crop,
                            model = nativeAd.images[0].uri,
                            contentDescription = "null"
                        )
                        Text(
                            modifier = androidx.compose.ui.Modifier.padding(spacing.spaceSmall),
                            text = nativeAd.title,
                            style = MaterialTheme.typography.h5,
                            maxLines = 1,
                            color = MaterialTheme.colors.onSurface
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(
                                    horizontal = spacing.spaceSmall,
                                    vertical = spacing.spaceExtraSmall
                                ),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = nativeAd.adSource,
                                style = MaterialTheme.typography.caption,
                                maxLines = 1,
                                color = MaterialTheme.colors.onSurface
                            )
                            // Ad flag
                            Spacer(modifier = androidx.compose.ui.Modifier.width(spacing.spaceSmall))
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .background(
                                        color = Color(0xFFA6A6A6), shape = RoundedCornerShape(4.dp)
                                    )
                                    .padding(horizontal = 2.dp, vertical = 1.dp)
                            ) {
                                Text(
                                    text = "Ad",
                                    color = MaterialTheme.colors.onSurface,
                                    style = MaterialTheme.typography.button,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            })
    }
}

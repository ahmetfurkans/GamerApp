package com.huawei.gamer.feed

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RatingText(
    rating: Double,
) {
    val color = if (rating >= 4.0) Color(0xFF5FAA41)
    else if (rating >= 3.0) Color(0xFFC59D4C)
    else Color(0xFFB00020)

    Box(
        modifier = Modifier
            .size(width = 25.dp, height = 20.dp)
            .clip(RoundedCornerShape(3.dp))
            .border(BorderStroke(1.5.dp, color = color)),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            fontSize = 12.sp,
            text = ((rating.times(20).toInt().toString())),
            color = color,
        )
    }
}
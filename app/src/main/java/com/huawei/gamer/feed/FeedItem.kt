package com.huawei.gamer.feed

import com.huawei.gamer.remote.Game
import com.huawei.hms.ads.nativead.NativeAd

sealed class FeedItem {
    data class Feed(val game: Game) : FeedItem()
    data class Ad(val nativeAd: NativeAd) : FeedItem()
}

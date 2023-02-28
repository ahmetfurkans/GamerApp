package com.huawei.gamer.feed

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huawei.gamer.remote.RetrofitInstance
import com.huawei.hms.ads.AdListener
import com.huawei.hms.ads.AdParam
import com.huawei.hms.ads.nativead.NativeAdConfiguration
import com.huawei.hms.ads.nativead.NativeAdLoader
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {

    companion object {
        const val AD_START_POSITION = 3
        const val AD_FREQUENCY = 10
    }

    private val _items = MutableLiveData<List<FeedItem>>()
    val items: LiveData<List<FeedItem>>
        get() = _items

    private var position: Int = AD_START_POSITION

    fun loadGames(context: Context) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getAllGames()
                _items.value = response.results.map {
                    FeedItem.Feed(game = it)
                }
                if (response.results.isNotEmpty()) loadNativeAd(context)
            } catch (e: Exception) {
                Toast.makeText(context, "Failed to load games: ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun loadNativeAd(context: Context) {
        val adLoader = NativeAdLoader.Builder(context, "testy63txaom86")
            .setNativeAdLoadedListener { ad ->
                val items = _items.value.orEmpty().toMutableList()
                items.add(position, FeedItem.Ad(ad))
                _items.value = items
                position += AD_FREQUENCY
            }.setAdListener(object : AdListener() {
                override fun onAdFailed(errorCode: Int) {
                    // Called when an ad fails to be loaded.
                }
            }).setNativeAdOptions(
                NativeAdConfiguration.Builder().setRequestCustomDislikeThisAd(true).build()
            )
            .build()
        adLoader.loadAds(AdParam.Builder().build(), 5)
    }
}
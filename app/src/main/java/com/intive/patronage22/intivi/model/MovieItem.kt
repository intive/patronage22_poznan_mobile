package com.intive.patronage22.intivi.model

import com.intive.patronage22.intivi.ViewModels.HomeViewModel

data class MovieItem(
    val title: String,
    val image: Int,
    var type: HomeViewModel.HomeItemType
) {
    val movieId: Long = currentId++

    companion object {
        // todo: this needs to be definitely fixed
        var currentId: Long = 0
    }
}
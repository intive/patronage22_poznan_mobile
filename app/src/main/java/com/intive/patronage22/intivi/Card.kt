package com.intive.patronage22.intivi

import com.intive.patronage22.intivi.Fragments.HomeFragment

var cardList = mutableListOf<Card>()

class Card (var movieAvatar: Int, var movieTitle: String, var movieType: HomeFragment.Type, val id: Int? = cardList.size){
}
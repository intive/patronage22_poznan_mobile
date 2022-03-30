package com.intive.patronage22.intivi.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.intive.patronage22.intivi.Adapter.RecyclerAdapter
import com.intive.patronage22.intivi.Card
import com.intive.patronage22.intivi.DetailsActivity
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.cardList
import com.intive.patronage22.intivi.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    var allCardList = mutableListOf<Card>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        allCards()

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(activity,2)
            adapter = RecyclerAdapter(cardList)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickNavigate(view.findViewById<ImageView>(R.id.app_logo), DetailsActivity::class.java)
    }

    private fun clickNavigate(view: View, activity: Class<*>){
        view.setOnClickListener{
            startActivity(Intent(getActivity(), activity))
        }
    }

    enum class Type{
        MOVIES, SERIES, KIDS
    }

    private fun allCards() {
        val card_moonfal = Card(R.drawable.avatar_moonfall,"Moonfal", Type.MOVIES)
        allCardList.add(card_moonfal)
        val card_sing = Card(R.drawable.avatar_sing_2, "Sing 2", Type.KIDS)
        allCardList.add(card_sing)
        val card_theExpanse = Card(R.drawable.avatar_the_expanse, "The Expanse", Type.SERIES)
        allCardList.add(card_theExpanse)
        val card_deathOnTheNile = Card(R.drawable.avatar_death_on_the_nile, "Death on the Nile", Type.MOVIES)
        allCardList.add(card_deathOnTheNile)
        val card_littleNicholasTreasure = Card(R.drawable.avatar_little_nicholas_treasure, "Little Nicholas' Treasure", Type.KIDS)
        allCardList.add(card_littleNicholasTreasure)

        allCardList.add(card_moonfal)
        allCardList.add(card_sing)
        allCardList.add(card_theExpanse)
        allCardList.add(card_deathOnTheNile)
        allCardList.add(card_littleNicholasTreasure)

        allCardList.add(card_moonfal)
        allCardList.add(card_sing)
        allCardList.add(card_theExpanse)
        allCardList.add(card_deathOnTheNile)
        allCardList.add(card_littleNicholasTreasure)

        cardList = allCardList
    }
}
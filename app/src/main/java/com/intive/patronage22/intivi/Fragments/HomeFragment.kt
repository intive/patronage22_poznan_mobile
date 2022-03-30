package com.intive.patronage22.intivi.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.intive.patronage22.intivi.Adapter.RecyclerAdapter
import com.intive.patronage22.intivi.Card
import com.intive.patronage22.intivi.DetailsActivity
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.cardList
import com.intive.patronage22.intivi.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    var allCardList = mutableListOf<Card>()
    var seriesCardList = mutableListOf<Card>()
    var moviesCardList = mutableListOf<Card>()
    var kidsCardList = mutableListOf<Card>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        allCardsCreate()
        recycleViewApply()

        binding.filterButtonMovies.setOnClickListener{
            moviesCardFilter()
            recycleViewApply()
        }
        binding.filterButtonSeries.setOnClickListener{
            seriesCardFilter()
            recycleViewApply()
        }
        binding.filterButtonKids.setOnClickListener{
            kidsCardFilter()
            recycleViewApply()
        }

        val act_footer_tab = activity?.findViewById<TabLayout>(R.id.footer_tab)
        act_footer_tab?.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                allCardsCreate()
                recycleViewApply()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                allCardsCreate()
                recycleViewApply()
            }
        })
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

    enum class CardType{
        MOVIES, SERIES, KIDS
    }

    private fun allCardsCreate() {
        allCardList.clear()
        val card_moonfal = Card(R.drawable.avatar_moonfall,"Moonfal", CardType.MOVIES)
        allCardList.add(card_moonfal)
        val card_sing = Card(R.drawable.avatar_sing_2, "Sing 2", CardType.KIDS)
        allCardList.add(card_sing)
        val card_theExpanse = Card(R.drawable.avatar_the_expanse, "The Expanse", CardType.SERIES)
        allCardList.add(card_theExpanse)
        val card_deathOnTheNile = Card(R.drawable.avatar_death_on_the_nile, "Death on the Nile", CardType.MOVIES)
        allCardList.add(card_deathOnTheNile)
        val card_littleNicholasTreasure = Card(R.drawable.avatar_little_nicholas_treasure, "Little Nicholas' Treasure", CardType.KIDS)
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

    private fun recycleViewApply(){
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(activity,2)
            adapter = RecyclerAdapter(cardList)
        }
    }

    private fun moviesCardFilter(){
        moviesCardList.clear()
        for(card in allCardList) {
            if(card.movieType == CardType.MOVIES){
                moviesCardList.add(card)
            }
        }
        cardList = moviesCardList
    }

    private fun seriesCardFilter(){
        seriesCardList.clear()
        for(card in allCardList) {
            if(card.movieType == CardType.SERIES){
                seriesCardList.add(card)
            }
        }
        cardList = seriesCardList
    }

    private fun kidsCardFilter(){
        kidsCardList.clear()
        for(card in allCardList) {
            if(card.movieType == CardType.KIDS){
                kidsCardList.add(card)
            }
        }
        cardList = kidsCardList
    }
}
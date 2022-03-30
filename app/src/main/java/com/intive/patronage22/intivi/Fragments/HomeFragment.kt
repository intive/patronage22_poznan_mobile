package com.intive.patronage22.intivi.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.intive.patronage22.intivi.Adapter.RecyclerAdapter
import com.intive.patronage22.intivi.DetailsActivity
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.databinding.FragmentHomeBinding
import com.intive.patronage22.intivi.model.MovieItem
import com.intive.patronage22.intivi.model.movieList

class HomeFragment : Fragment() {
    private lateinit var bind: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentHomeBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickNavigate(view.findViewById<ImageView>(R.id.app_logo), DetailsActivity::class.java)
        bind.recyclerView.adapter=RecyclerAdapter()
        bind.recyclerView.layoutManager=GridLayoutManager(activity,2)
        setData()

    }

    private fun clickNavigate(view: View, activity: Class<*>){
        view.setOnClickListener{
            startActivity(Intent(getActivity(), activity))
        }
    }

    private fun setData() {
        movieList.add(MovieItem("Moonfall", R.drawable.avatar_moonfall))
        movieList.add(MovieItem("Sing 2", R.drawable.avatar_sing_2))
        movieList.add(MovieItem("The Expanse", R.drawable.avatar_the_expanse))
        movieList.add(MovieItem("Death on the Nile", R.drawable.avatar_death_on_the_nile))
        movieList.add(MovieItem("Little Nicholas' Treasure",R.drawable.avatar_little_nicholas_treasure))
        movieList.add(MovieItem("Moonfall", R.drawable.avatar_moonfall))
        movieList.add(MovieItem("Sing 2", R.drawable.avatar_sing_2))
        movieList.add(MovieItem("The Expanse", R.drawable.avatar_the_expanse))
        movieList.add(MovieItem("Death on the Nile", R.drawable.avatar_death_on_the_nile))
        movieList.add(MovieItem("Little Nicholas' Treasure",R.drawable.avatar_little_nicholas_treasure))
        movieList.add(MovieItem("Moonfall", R.drawable.avatar_moonfall))
        movieList.add(MovieItem("Sing 2", R.drawable.avatar_sing_2))
        movieList.add(MovieItem("The Expanse", R.drawable.avatar_the_expanse))
        movieList.add(MovieItem("Death on the Nile", R.drawable.avatar_death_on_the_nile))
        movieList.add(MovieItem("Little Nicholas' Treasure",R.drawable.avatar_little_nicholas_treasure))
        movieList.add(MovieItem("Moonfall", R.drawable.avatar_moonfall))
        movieList.add(MovieItem("Sing 2", R.drawable.avatar_sing_2))
        movieList.add(MovieItem("The Expanse", R.drawable.avatar_the_expanse))
        movieList.add(MovieItem("Death on the Nile", R.drawable.avatar_death_on_the_nile))
        movieList.add(MovieItem("Little Nicholas' Treasure",R.drawable.avatar_little_nicholas_treasure))
        movieList.add(MovieItem("Moonfall", R.drawable.avatar_moonfall))
        movieList.add(MovieItem("Sing 2", R.drawable.avatar_sing_2))
        movieList.add(MovieItem("The Expanse", R.drawable.avatar_the_expanse))
        movieList.add(MovieItem("Death on the Nile", R.drawable.avatar_death_on_the_nile))
        movieList.add(MovieItem("Little Nicholas' Treasure",R.drawable.avatar_little_nicholas_treasure))
        movieList.add(MovieItem("Moonfall", R.drawable.avatar_moonfall))
        movieList.add(MovieItem("Sing 2", R.drawable.avatar_sing_2))
        movieList.add(MovieItem("The Expanse", R.drawable.avatar_the_expanse))
        movieList.add(MovieItem("Death on the Nile", R.drawable.avatar_death_on_the_nile))
        movieList.add(MovieItem("Little Nicholas' Treasure",R.drawable.avatar_little_nicholas_treasure))
        movieList.add(MovieItem("Moonfall", R.drawable.avatar_moonfall))
        movieList.add(MovieItem("Sing 2", R.drawable.avatar_sing_2))
        movieList.add(MovieItem("The Expanse", R.drawable.avatar_the_expanse))
        movieList.add(MovieItem("Death on the Nile", R.drawable.avatar_death_on_the_nile))
        movieList.add(MovieItem("Little Nicholas' Treasure",R.drawable.avatar_little_nicholas_treasure))
        movieList.add(MovieItem("Moonfall", R.drawable.avatar_moonfall))
        movieList.add(MovieItem("Sing 2", R.drawable.avatar_sing_2))
        movieList.add(MovieItem("The Expanse", R.drawable.avatar_the_expanse))
        movieList.add(MovieItem("Death on the Nile", R.drawable.avatar_death_on_the_nile))
        movieList.add(MovieItem("Little Nicholas' Treasure",R.drawable.avatar_little_nicholas_treasure))
        movieList.add(MovieItem("Moonfall", R.drawable.avatar_moonfall))
        movieList.add(MovieItem("Sing 2", R.drawable.avatar_sing_2))
        movieList.add(MovieItem("The Expanse", R.drawable.avatar_the_expanse))
        movieList.add(MovieItem("Death on the Nile", R.drawable.avatar_death_on_the_nile))
        movieList.add(MovieItem("Little Nicholas' Treasure",R.drawable.avatar_little_nicholas_treasure))
    }
}
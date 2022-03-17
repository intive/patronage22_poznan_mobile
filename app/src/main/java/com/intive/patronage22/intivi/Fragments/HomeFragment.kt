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
import com.intive.patronage22.intivi.Adapter.RecyclerAdapater
import com.intive.patronage22.intivi.DetailsActivity
import com.intive.patronage22.intivi.R

class HomeFragment : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapater: RecyclerView.Adapter<RecyclerAdapater.ViewHolder>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutManager = GridLayoutManager(view?.context, 2)
        view?.findViewById<RecyclerView>(R.id.recyclerView)?.layoutManager
        adapater = RecyclerAdapater()
        view?.findViewById<RecyclerView>(R.id.recyclerView)?.adapter =adapater
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
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
}
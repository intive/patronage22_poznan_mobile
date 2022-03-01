package com.intive.patronage22.intivi.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.intive.patronage22.intivi.Fragments.SignInFragments
import com.intive.patronage22.intivi.Fragments.SignUpFragments

internal class FragmentsAdapter (fm: FragmentManager, var totalTabs: Int): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0-> {
                SignInFragments()
            }
            1-> {
                SignUpFragments()
            }
            else-> getItem(position)
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}
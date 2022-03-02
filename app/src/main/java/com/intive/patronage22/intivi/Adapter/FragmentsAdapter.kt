package com.intive.patronage22.intivi.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.intive.patronage22.intivi.Fragments.SignInFragment
import com.intive.patronage22.intivi.Fragments.SignUpFragment

internal class FragmentsAdapter (fm: FragmentManager, var totalTabs: Int): FragmentPagerAdapter(fm) {

    enum class ActiveTab {
        SIGN_IN, SIGN_UP
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            ActiveTab.SIGN_IN.ordinal -> {
                SignInFragment()
            }
            ActiveTab.SIGN_UP.ordinal-> {
                SignUpFragment()
            }
            else-> getItem(position)
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}
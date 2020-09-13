package com.missnalgas.phr2.viewpager

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.missnalgas.phr2.phrase.Phrase
import com.missnalgas.phr2.viewpager.fragments.ContentFragment
import com.missnalgas.phr2.viewpager.fragments.MenuFragment
import com.missnalgas.phr2.viewpager.fragments.TitleFragment

class ViewAdapter(@NonNull fm : FragmentManager, var data : Phrase) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                TitleFragment(data)
            }
            1 -> {
                ContentFragment(data)
            }
            2 -> {
                MenuFragment()
            }
            else -> TitleFragment(data)
        }
    }
}
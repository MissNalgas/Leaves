package com.missnalgas.phr2.viewpager

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.missnalgas.phr2.phrase.Phrase
import com.missnalgas.phr2.viewmodel.MainViewModel
import com.missnalgas.phr2.viewpager.fragments.ContentFragment
import com.missnalgas.phr2.viewpager.fragments.MenuFragment
import com.missnalgas.phr2.viewpager.fragments.TitleFragment

class ViewAdapter(activity: FragmentActivity, var data : Phrase) : FragmentStateAdapter(activity) {


    override fun getItemCount(): Int = MainViewModel.FRAGMENTS_COUNT

    override fun createFragment(position: Int): Fragment {
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
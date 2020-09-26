package com.missnalgas.phr2.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.missnalgas.phr2.viewmodel.MainViewModel
import com.missnalgas.phr2.viewpager.fragments.ContentFragment
import com.missnalgas.phr2.viewpager.fragments.MenuFragment
import com.missnalgas.phr2.viewpager.fragments.TitleFragment

class ViewAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {


    override fun getItemCount(): Int = MainViewModel.FRAGMENTS_COUNT

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                TitleFragment()
            }
            1 -> {
                ContentFragment()
            }
            2 -> {
                MenuFragment()
            }
            else -> TitleFragment()
        }
    }
}
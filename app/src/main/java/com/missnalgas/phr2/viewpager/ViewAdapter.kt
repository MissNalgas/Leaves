package com.missnalgas.phr2.viewpager

import android.util.Log
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.missnalgas.phr2.phrase.Phrase
import com.missnalgas.phr2.viewpager.fragments.ContentFragment
import com.missnalgas.phr2.viewpager.fragments.TitleFragment

class ViewAdapter(@NonNull fm : FragmentManager,@NonNull val data : Phrase) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                Log.i("asddsa", "first fragment")
                TitleFragment(data)
            }
            1 -> {
                Log.i("asddsa", "second fragment")
                ContentFragment(data)
            }
            else -> TitleFragment(data)
        }
    }
}
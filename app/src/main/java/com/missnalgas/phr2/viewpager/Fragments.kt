package com.missnalgas.phr2.viewpager

import android.graphics.Color
import com.missnalgas.phr2.viewmodel.FragmentStyle

object Fragments {

    const val FRAGMENTS_COUNT = 3

    val FRAGMENTS = List(FRAGMENTS_COUNT) {
        return@List when(it) {
            0-> FragmentStyle(intArrayOf(Color.parseColor("#FF8A65"), Color.parseColor("#FF7043"), Color.parseColor("#FF5722")))
            1 -> FragmentStyle(intArrayOf(Color.parseColor("#009688"), Color.parseColor("#00897B"), Color.parseColor("#00796B")))
            2 -> FragmentStyle(intArrayOf(Color.parseColor("#00796B"), Color.parseColor("#00695C"), Color.parseColor("#004D40")))
            else -> FragmentStyle()
        }
    }

}
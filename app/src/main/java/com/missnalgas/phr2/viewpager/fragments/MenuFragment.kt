package com.missnalgas.phr2.viewpager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.missnalgas.phr2.R
import com.missnalgas.phr2.menu.MenuSpanSizeLookup
import com.missnalgas.phr2.menu.PMenuItem
import com.missnalgas.phr2.menu.recyclerview.MenuAdapter

class MenuFragment : Fragment() {

    private val items : ArrayList<PMenuItem> by lazy {
        val list = ArrayList<PMenuItem>()
        list.add(PMenuItem("Hello"))
        list.add(PMenuItem("World"))
        list.add(PMenuItem("Juan"))
        list.add(PMenuItem("Sebastian"))
        list.add(PMenuItem("Journey Before destination you know a long ass phrase and things"))
        list.add(PMenuItem("Sebastian"))
        list.add(PMenuItem("Aragon", 2))
        return@lazy list
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.menu_layout, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view?.let {view ->
            val recyclerview = view.findViewById<RecyclerView>(R.id.menu_recyclerview)
            val adapter = MenuAdapter(items)
            recyclerview.setHasFixedSize(true)
            val layoutManager = GridLayoutManager(context, 2)
            layoutManager.spanSizeLookup = MenuSpanSizeLookup(items)
            recyclerview.layoutManager = layoutManager
            recyclerview.adapter = adapter
        }



    }

}
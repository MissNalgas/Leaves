package com.missnalgas.phr2.menu

import androidx.recyclerview.widget.GridLayoutManager

class MenuSpanSizeLookup(private val items :  List<PMenuItem>) : GridLayoutManager.SpanSizeLookup() {
    override fun getSpanSize(position: Int): Int  = items[position].span
}
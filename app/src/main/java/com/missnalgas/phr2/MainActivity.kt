package com.missnalgas.phr2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.missnalgas.phr2.phrase.Phrase
import com.missnalgas.phr2.viewpager.ViewAdapter

class MainActivity : AppCompatActivity() {

    private val data = Phrase("knights radiants", "Journey before destination")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewpager = this.findViewById<ViewPager>(R.id.main_viewpager)
        viewpager.adapter = ViewAdapter(supportFragmentManager, data)

    }
}
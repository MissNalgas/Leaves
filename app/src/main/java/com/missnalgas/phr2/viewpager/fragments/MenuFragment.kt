package com.missnalgas.phr2.viewpager.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.rewarded.RewardedAd
import com.missnalgas.phr2.AdListener
import com.missnalgas.phr2.R
import com.missnalgas.phr2.menu.MenuSpanSizeLookup
import com.missnalgas.phr2.menu.PMenuItem
import com.missnalgas.phr2.menu.recyclerview.MenuAdapter

class MenuFragment : Fragment() {

    companion object {
        var isShowingAdd = false
    }

    private val items = ArrayList<PMenuItem>()

    private fun loadMenu() {
        val showAdd = PMenuItem("Show add", 2)
        showAdd.setOnClickListener {

            if (!isShowingAdd) {
                isShowingAdd = true
                Toast.makeText(context, "Getting add...", Toast.LENGTH_SHORT).show()
                rewardedAd = RewardedAd(context, "ca-app-pub-3940256099942544/5224354917")
                val activity = activity as AppCompatActivity
                rewardedAd.loadAd(AdRequest.Builder().build(), AdListener.AddLoadCallback(rewardedAd, activity))
            }

        }
        items.add(showAdd)

        items.add(PMenuItem("About"))
        items.add(PMenuItem("Version"))

        val addPhrase = PMenuItem("Add Phrase", 2, Color.parseColor("#dd3218"))
        addPhrase.textColor = Color.WHITE
        addPhrase.image = ContextCompat.getDrawable(context!!, R.drawable.phr)
        addPhrase.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://mssnapplications.com/phr/")
            startActivity(intent)
        }
        items.add(addPhrase)

        val github = PMenuItem("Go to GitHub", 2, Color.BLACK)
        github.image = context?.getDrawable(R.drawable.github_white)
        github.textColor = Color.WHITE
        github.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://github.com/MissNalgas/phr2")
            startActivity(intent)
        }
        items.add(github)
    }

    private lateinit var rewardedAd : RewardedAd


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.menu_layout, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadMenu()

        Log.i("asddsa", "onActivityCreated")

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
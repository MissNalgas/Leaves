package com.missnalgas.phr2.viewpager.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.material.snackbar.Snackbar
import com.missnalgas.phr2.AdListener
import com.missnalgas.phr2.R
import com.missnalgas.phr2.menu.MenuSpanSizeLookup
import com.missnalgas.phr2.menu.PMenuItem
import com.missnalgas.phr2.menu.recyclerview.MenuAdapter

class MenuFragment : Fragment() {

    companion object {
        var isShowingAdd = false
    }

    private var items : List<PMenuItem>? = null


    private fun loadMenu(context : Context) {
        items = List(5) { index ->
            when(index) {
                0-> PMenuItem.Builder().apply {

                    title = context.getString(R.string.watch_ad)
                    clickListener = View.OnClickListener {
                        if (!isShowingAdd) {
                            isShowingAdd = true
                            Toast.makeText(context, "Getting add...", Toast.LENGTH_SHORT).show()
                            rewardedAd = RewardedAd(context, "ca-app-pub-7933650770519707/4469011900")
                            val activity = activity as AppCompatActivity
                            rewardedAd.loadAd(AdRequest.Builder().build(), AdListener.AddLoadCallback(rewardedAd, activity))
                        }
                    }
                    hasDescription = true
                    description = context.getString(R.string.watch_ad_description)

                }.build()
                1 -> PMenuItem.Builder().apply {

                    title = context.getString(R.string.about)
                    span = 1
                    clickListener = View.OnClickListener {
                        view?.let {
                            Snackbar.make(it, context.getString(R.string.about_snackbar), Snackbar.LENGTH_SHORT).show()
                        }
                    }

                }.build()
                2 -> PMenuItem.Builder().apply {
                    title = context.getString(R.string.version)
                    val packInfo = context.packageManager.getPackageInfo(context.packageName, 0)
                    val sVersion = packInfo.versionName
                    span = 1
                    clickListener = View.OnClickListener {
                        view?.let {
                            Snackbar.make(it, "Version $sVersion", Snackbar.LENGTH_SHORT).show()
                        }
                    }
                    hasDescription = true
                    description = sVersion
                }.build()
                3 -> PMenuItem.Builder().apply {
                    title = context.getString(R.string.post_phrase)
                    textColor = Color.BLACK
                    image = ContextCompat.getDrawable(context, R.drawable.leaves_logo)
                    clickListener = View.OnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse("https://mssnapplications.com/leaves/")
                        startActivity(intent)
                    }
                }.build()
                4 -> PMenuItem.Builder().apply {
                    title = context.getString(R.string.go_to_github)
                    color = Color.BLACK
                    image = AppCompatResources.getDrawable(context, R.drawable.github_white)
                    textColor = Color.WHITE
                    clickListener = View.OnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse("https://github.com/MissNalgas/Leaves")
                        startActivity(intent)
                    }
                }.build()
                else -> PMenuItem.Builder().build()
            }
        }
    }

    private lateinit var rewardedAd : RewardedAd


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.menu_layout, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.let {context -> loadMenu(context) }

        view?.let {view ->
            items?.let {items ->
                val recyclerview = view.findViewById<RecyclerView>(R.id.menu_recyclerview)
                val adapter = MenuAdapter(items)
                recyclerview.setHasFixedSize(true)
                val layoutManager = GridLayoutManager(context, 2)
                layoutManager.spanSizeLookup = MenuSpanSizeLookup(items)
                recyclerview.setHasFixedSize(true)
                recyclerview.layoutManager = layoutManager
                recyclerview.adapter = adapter
            }

        }



    }

}
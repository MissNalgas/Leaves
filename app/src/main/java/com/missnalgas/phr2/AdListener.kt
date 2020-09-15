package com.missnalgas.phr2

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.missnalgas.phr2.viewpager.fragments.MenuFragment

class AdListener{

    class AddLoadCallback(private val ad : RewardedAd,private val activity : AppCompatActivity) : RewardedAdLoadCallback() {
        override fun onRewardedAdLoaded() {
            super.onRewardedAdLoaded()
            if (ad.isLoaded) {
                ad.show(activity, AdCallback())
                MenuFragment.isShowingAdd = false
            }
        }

        override fun onRewardedAdFailedToLoad(error: LoadAdError?) {
            super.onRewardedAdFailedToLoad(error)
            Toast.makeText(activity.baseContext, "We couldn't find any Ad :(", Toast.LENGTH_SHORT).show()
            Log.i("asddsa", "Error: $error")
            MenuFragment.isShowingAdd = false
        }
    }

    class AdCallback : RewardedAdCallback() {
        override fun onUserEarnedReward(reward: RewardItem) {
            Log.i("asddsa", "Rewarded ${reward.amount} ${reward.type}")
        }
    }

}
package com.missnalgas.phr2

import android.media.audiofx.BassBoost
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.missnalgas.phr2.phrase.Phrase
import com.missnalgas.phr2.viewmodel.MainViewModel
import com.missnalgas.phr2.viewmodel.ViewModelFactory
import com.missnalgas.phr2.viewpager.ViewAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModelFactory : ViewModelFactory
    private val viewModel : MainViewModel by lazy {
        return@lazy ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }
    private var data = Phrase("Journey before destination", "Tailing Darkness was a far different experience from tailing the captain. For one, it was daylight now. Still early morning, but light enough that Lift had to worry about being spotted. Fortunately, encountering Darkness had completely burned away the fog of sleepiness she ’d felt upon awaking. At first she tried to stay on the tops of the walls, in the gardens above the city. That proved difficult. Though there were some bridges up here crossing over the slots, they weren’t nearly as common as she needed. Each time Darkness hit an intersection she had a shiver of fear, worrying he ’d turn down a path she couldn’t follow without somehow leaping over a huge gap. Eventually she took the more dangerous route of scrambling down a ladder, then chasing after him within a trench. Fortunately, it seemed that people in here expected some measure of jostling as they moved through the streets. The confines weren’t completely cramped—many of the larger streets had plenty of space. But those walls did enhance the feeling of being boxed in. Lift had lots of practice with this sort of thing, and she kept the tail inconspicuous. She didn’t pick any pockets, despite several fine opportunities—people who were practically holding their pouches up, She hadn’t eaten since last night, and if she didn’t use the power, it eventually vanished. Took about half a day; she didn’t know why. She dodged around the figures of farmers heading to work, women carrying water, kids skipping to their lessons—where they ’d sit in rows and listen to a teacher while doing some menial task, like sewing, to pay for the education. Suckers. People gave Darkness lots of space, moving away from him like they would a guy whose backside couldn’t help but let everyone know what he ’d been eating lately. She smiled at the thought, climbing along the top of some boxes beside a few other urchins. Darkness, though, he wasn’t that normal. She had trouble imagining him eating, or anything like that. A shopkeeper chased them down off the boxes, but Lift had gotten a good look at Darkness and was able to scurry after him, Wyndle at her side. Darkness never paused to consider his route, or to look at the wares of street vendors. He seemed to move too quickly for his own steps, like he was melting from shadow to shadow as he strode. She nearly lost sight of him several times, which was bizarre. She ’d always been able to keep track of where people were. Darkness eventually reached a market where they sure had a lot of fruit on display. Looked like someone had planned a really, really big food fight, but had decided to call it off and were reluctantly selling their ammunition. Lift helped herself to a purple fruit—she didn’t know the name—while the shopkeeper was staring, uncomfortably, at Darkness. As people did. It— “Hey!” the shopkeeper shouted. “Hey, stop!” Lift spun, tucking her hand behind her back and dropping the fruit—which she kicked with her heel into the crowd. She smiled sweetly. But the shopkeeper wasn’t looking at her. He was looking at a different opportunist, a girl a few years Lift’s senior, who had swiped a whole basket of fruit. The young woman bolted the moment she was spotted, leaning down and clinging to the basket. She sprinted deftly through the crowd. Lift heard herself whimper. No. Not that way. Not toward— Darkness snatched the young woman from the crowd. He flowed toward her almost as if he were liquid, then seized her by the shoulder with the speed of a snapping rat trap. She struggled, battering against him, though he remained stiff and didn’t seem to notice or mind the attack. Still holding to her, he bent and picked up the basket of fruit, then carried it toward the shop, dragging the thief after him.")

    private val pageChangeListener : ViewPager.OnPageChangeListener by lazy {
        object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                viewModel.updatePageChangeListener(position+positionOffset)
            }

            override fun onPageSelected(position: Int) {
                /*EMPTY*/
            }

            override fun onPageScrollStateChanged(state: Int) {
                /*EMPTY*/
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        viewModelFactory = ViewModelFactory(application = application)

        val viewpager = this.findViewById<ViewPager>(R.id.main_viewpager)
        viewpager.adapter = ViewAdapter(supportFragmentManager, data)

        viewpager.addOnPageChangeListener(pageChangeListener)

        loadObs()

        viewModel.fetchData(this)

    }


    private fun loadObs() {
        val vp = this.findViewById<ViewPager>(R.id.main_viewpager)
        viewModel.backgroundColorLiveData.observe(this, Observer {color ->
            color?.let{
                vp.background = it
            }
        })
        viewModel.dataLiveData.observe(this, Observer { phr ->
            phr?.let {
                data = phr
                vp.adapter?.let {adapter ->
                    val viewAdapter = adapter as ViewAdapter
                    viewAdapter.data = data
                    viewAdapter.notifyDataSetChanged()
                    Log.i("asddsa", "dataChanged to ${phr.title}")
                }
            }

        })
    }
}
package com.module.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.module.kotlin.R
import kotlinx.android.synthetic.main.activity_tab.*

class TabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)

        viewpager.adapter = MyPagerAdapter(supportFragmentManager)
        tablayout.setupWithViewPager(viewpager)
//        val imageResId = intArrayOf(R.drawable.ic_teams, R.drawable.ic_teams, R.drawable.ic_favorites)
//        tabs_main.getTabAt(0)?.setIcon(imageResId[0])
//        tabs_main.getTabAt(1)?.setIcon(imageResId[1])
//        tabs_main.getTabAt(2)?.setIcon(imageResId[2])
    }

    class MyPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){
        private val pages = listOf(
            Tab1Fragment(),
            Tab2Fragment(),
            Tab3Fragment()
        )
        override fun getItem(position: Int): Fragment {
            return pages[position]
        }
        override fun getCount(): Int {
            return pages.size
        }
        override fun getPageTitle(position: Int): CharSequence? {
            return when(position){
                0 -> "Tab 1"
                1-> "Tab 2"
                else -> "Tab 3"
            }
        }
    }
}

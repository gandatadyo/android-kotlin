package com.module.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toolbar
import kotlinx.android.synthetic.main.activity_navigation_drawer.*

class NavigationDrawerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_drawer)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val mPlanetTitles = listOf("Item 1", "Item 2", "Item 3", "Item 4")
        left_drawer.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mPlanetTitles)

        val mMessageClickedHandler = AdapterView.OnItemClickListener { parent, v, position, id ->
            toasttest(mPlanetTitles[position], this)
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        left_drawer.onItemClickListener = mMessageClickedHandler

        val mDrawerToggle = ActionBarDrawerToggle(this, drawer_layout, R.string.drawer_open, R.string.drawer_close)
        drawer_layout.addDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    drawer_layout.closeDrawer(GravityCompat.START)
                } else {
                    drawer_layout.openDrawer(GravityCompat.START)
                }
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

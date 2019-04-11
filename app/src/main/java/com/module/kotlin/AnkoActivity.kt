@file:Suppress("DEPRECATION")

package com.module.kotlin

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.sdk27.coroutines.onClick

class AnkoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_anko)
        MainActivityUI().setContentView(this)
    }
}

// View Activity
class MainActivityUI : AnkoComponent<AnkoActivity> {
    override fun createView(ui: AnkoContext<AnkoActivity>) = with(ui) {
        scrollView {
            verticalLayout {
                imageView(R.drawable.iconkotlin).lparams(width = matchParent) {
                    padding = dip(20)
                    margin = dip(15)
                    height = dip(250)
                }
                val name = editText() {
                    hint = "Write for toast"
                }

                button("Toast") {
                    onClick {
                        val stringname = name.text.toString()
                        name.setText("")
                        toast("Hallo $stringname")
                    }
                }

                button("Alert") {
                    onClick {
                        alert("Happy Coding ?") {
                            yesButton { toast("Yeah..") }
                            noButton {}
                        }.show()
                    }
                }

                button("Selector") {
                    onClick {
                        val club = listOf("Barcelona", "Manchester United", "Real Madrid")
                        selector("What’s football club do you love?", club) { dialogInterface, i ->
                            toast(club[i].toString())
                        }
                    }
                }

                button("Progress Dialog") {
                    onClick {
                        indeterminateProgressDialog("Hello! Please wait...").show()
                    }
                }

                button("Snackbar") {
                    setOnClickListener {
                        snackbar("Hello, ${name.text}!")
                    }
                }

                button("Go to Second Activity") {
                    backgroundColor = getColor(context, R.color.colorPrimary)
                    textColor = Color.WHITE
                    setOnClickListener {
                        startActivity<AnkoSecondaryActivity>("name" to "${name.text}")
                    }
                }.lparams(width = matchParent) {
                    topMargin = dip(5)
                }
            }
        }
    }
}

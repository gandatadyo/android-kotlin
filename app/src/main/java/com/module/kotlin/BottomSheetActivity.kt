package com.module.kotlin

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_bottom_sheet.*
import kotlinx.android.synthetic.main.bottom_sheet.*

class BottomSheetActivity : AppCompatActivity() {
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)

        var bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet_layout)
        btnBottomSheet.setOnClickListener {
            if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
            }
        }

        context = this
        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            @SuppressLint("SwitchIntDef")
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // React to state change
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        Toast.makeText(context,"hidden",Toast.LENGTH_SHORT).show()
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        Toast.makeText(context,"expand",Toast.LENGTH_SHORT).show()
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        Toast.makeText(context,"collapse",Toast.LENGTH_SHORT).show()
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        Toast.makeText(context,"dragging",Toast.LENGTH_SHORT).show()
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                        Toast.makeText(context,"setting" ,Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // React to dragging events
            }
        })
    }

}

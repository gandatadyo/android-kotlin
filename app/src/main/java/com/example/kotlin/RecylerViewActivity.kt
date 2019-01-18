package com.example.kotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_recyler_view.*

class RecylerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyler_view)
        val mydata = arrayListOf<dataschemarecycler>()
        mydata.add(dataschemarecycler("ganda","082264576766"))
        mydata.add(dataschemarecycler("dinda","085647080809"))

        recylerview.layoutManager = GridLayoutManager(this, 1)
        recylerview.adapter = RecylerAdapter(mydata, this)
    }

    class RecylerAdapter(private val dataList: ArrayList<dataschemarecycler>, val context: Context) : RecyclerView.Adapter<RecylerAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_recyler, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindItems(context,dataList[position])
        }
        override fun getItemCount(): Int {
            return dataList.size
        }
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bindItems(context: Context, dataitem: dataschemarecycler) {
                val lblitem1 = itemView.findViewById<TextView>(R.id.lblitem1)
                val lblitem2 = itemView.findViewById<TextView>(R.id.lblitem2)
                lblitem1.text = dataitem.nama
                lblitem2.text = dataitem.telp
                itemView.setOnClickListener {
                    toasttest("Nama : "+dataitem.nama,context)
                }
            }
        }
    }


}

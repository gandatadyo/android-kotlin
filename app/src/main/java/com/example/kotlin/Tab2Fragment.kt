package com.example.kotlin


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_listview.*
import kotlinx.android.synthetic.main.fragment_tab2.view.*
import org.jetbrains.anko.support.v4.ctx


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Tab2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_tab2, container, false)
        val dataarray = listOf("Informatika","Manajemen","Hukum","Desain Grafis","Akutansi")
        val adapter = ArrayAdapter<String>(ctx,android.R.layout.simple_list_item_1, dataarray)
        rootView.listviewtab2.adapter = adapter
        val mMessageClickedHandler = AdapterView.OnItemClickListener { parent, v, position, id ->
            toasttest(dataarray[position], ctx)
        }
        rootView.listviewtab2.onItemClickListener = mMessageClickedHandler
        return rootView
    }


}

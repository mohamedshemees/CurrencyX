package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.BaseAdapter
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapplication.CurreenciesInfo
import com.example.myapplication.R

class SpinnerCustomAdapter  (private val context: Context,val items:List<CurreenciesInfo>)
    : BaseAdapter() {


    override fun getCount(): Int = items.size
    override fun getItem(p0: Int): Any = items[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.currncy_list_item, parent, false)

        val item = getItem(position) as CurreenciesInfo
        val icon = view.findViewById<ImageView>(R.id.flag)
        val text = view.findViewById<TextView>(R.id.currency)

        icon.setImageResource(item.flag)
        text.text = item.appreviation
        return view
    }

    override fun getDropDownView(position: Int,  convertView: View?, parent: ViewGroup?): View {
        val view :View
        view=super.getDropDownView(position, convertView, parent)
        view.visibility=View.VISIBLE
        val item = getItem(position) as CurreenciesInfo
        val icon = view.findViewById<ImageView>(R.id.flag)
        val text = view.findViewById<TextView>(R.id.currency)
        icon.setImageResource(item.flag)
        text.text = item.appreviation

        return view

    }

}




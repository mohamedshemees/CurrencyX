package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.myapplication.databinding.CurrncyListItemBinding
import com.example.myapplication.utils.CurrenciesInfo

class SpinnerCustomAdapter(val items: List<CurrenciesInfo>) :
    BaseAdapter() {


    override fun getCount(): Int = items.size
    override fun getItem(p0: Int): Any = items[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = CurrncyListItemBinding.inflate(LayoutInflater.from(parent?.context))
        val currencyInfo = getItem(position) as CurrenciesInfo
        val flag = binding.flag
        val text = binding.currency
        val country = binding.country

        country.text = currencyInfo.country
        flag.setImageResource(currencyInfo.flag)
        text.text = currencyInfo.appreviation
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = super.getDropDownView(position, convertView, parent)
        view.visibility = View.VISIBLE
        val binding = CurrncyListItemBinding.inflate(LayoutInflater.from(parent?.context))
        val currencyInfo = getItem(position) as CurrenciesInfo
        val flag = binding.flag
        val text = binding.currency
        val country = binding.country

        country.text = currencyInfo.country
        flag.setImageResource(currencyInfo.flag)
        text.text = currencyInfo.appreviation

        return binding.root

    }

}




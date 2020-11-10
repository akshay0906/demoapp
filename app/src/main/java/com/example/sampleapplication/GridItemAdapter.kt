package com.example.sampleapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GridItemAdapter(var list: ArrayList<Currency>) :
    RecyclerView.Adapter<GridItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewRoot = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_currency, parent, false)

        return ViewHolder(viewRoot)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.tvCurrency.text = data.code
        holder.tvValue.text = data.value.toString()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvCurrency: TextView = view.findViewById(R.id.tv_currency)
        var tvValue: TextView = view.findViewById(R.id.tv_value)
    }
}
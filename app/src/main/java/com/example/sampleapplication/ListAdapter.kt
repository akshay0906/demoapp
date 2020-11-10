package com.example.sampleapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ListAdapter(var context: Context, var rates: ArrayList<ConsolidatedData>) :
RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewRoot = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_currency_data, parent, false)

        return ViewHolder(viewRoot)
    }

    override fun getItemCount(): Int {
        return rates.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val data = rates[position]
        holder.tvDate.text = "Date :"+formatDateFromDateString("yyyy-MM-dd","dd/MMM/yyyy",data.date)

        val list: ArrayList<Currency> = ArrayList()
        data.currency.forEach {
            val curr = Currency()
            curr.code = it.key
            curr.value = it.value
            list.add(curr)
        }

        val adapter = GridItemAdapter(list)
        holder.rvCurrency.setHasFixedSize(true);
        holder.rvCurrency.layoutManager = GridLayoutManager(context,3)
        holder.rvCurrency.adapter = adapter;

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvDate: TextView = view.findViewById(R.id.tvDate)
        var rvCurrency: RecyclerView = view.findViewById(R.id.rv_currency)
    }

    fun formatDateFromDateString(
        inputDateFormat: String?, outputDateFormat: String?,
        inputDate: String?
    ): String? {
        val mParsedDate: Date
        val mOutputDateString: String
        val mInputDateFormat =
            SimpleDateFormat(inputDateFormat, Locale.getDefault())
        val mOutputDateFormat =
            SimpleDateFormat(outputDateFormat, Locale.getDefault())
        mParsedDate = mInputDateFormat.parse(inputDate)
        mOutputDateString = mOutputDateFormat.format(mParsedDate)
        return mOutputDateString
    }
}
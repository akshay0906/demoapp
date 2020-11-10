package com.example.sampleapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.ViewAnimator
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import retrofit2.Call
import retrofit2.Response

class ListActivity : AppCompatActivity() {
    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        progressBar.visibility = View.VISIBLE

        RetrofitClient.getApiInterface().getList("2018-01-01", "2018-09-01", "USD")
            .enqueue(object : retrofit2.Callback<CurrencyResponse> {
                override fun onResponse(
                    call: Call<CurrencyResponse>,
                    response: Response<CurrencyResponse>
                ) {
                    val list: ArrayList<ConsolidatedData> = ArrayList()
                    response.body()?.rates?.forEach {
                        val data = ConsolidatedData()
                        data.date = it.key
                        data.currency = it.value
                        list.add(data)
                    }

                    adapter = ListAdapter(this@ListActivity,list)
                    rv_data.layoutManager= LinearLayoutManager(this@ListActivity)
                    rv_data.adapter = adapter

                    progressBar.visibility = View.GONE
                }

                override fun onFailure(call: Call<CurrencyResponse>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this@ListActivity, t.message, Toast.LENGTH_SHORT).show()
                }

            })

    }
}
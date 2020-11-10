package com.example.sampleapplication

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("history")
    fun getList(
        @Query("start_at") start_at: String,
        @Query("end_at") end_at: String,
        @Query("base") base: String
    ): Call<CurrencyResponse>
}
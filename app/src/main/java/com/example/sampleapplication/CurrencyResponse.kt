package com.example.sampleapplication

class CurrencyResponse {
    var base: String = ""
    var end_at: String = ""
    var rates: Map<String, Map<String,Double>> = HashMap()
    var start_at: String = ""
}
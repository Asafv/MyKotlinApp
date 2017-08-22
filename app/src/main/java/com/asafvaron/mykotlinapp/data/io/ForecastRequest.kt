package com.asafvaron.mykotlinapp.data.io

import com.google.gson.Gson
import java.net.URL

/**
 * Simple way to get json data from url request
 * <br>
 *     This is not recommended for huge responses!
 */
// Previously used class
//class ForecastRequest(var url: String) {
//
//    fun run() {
//        val forecastJsonStr = URL(url).readText() // Kotlin support extension function for URL requests
//        Log.d(javaClass.simpleName, forecastJsonStr)
//    }
//}

class ForecastRequest(private val zipCode: String) {
    companion object {
        private val APP_ID = "APPID=15646a06818f61f7b8d7823ca833e1ce"
        private val API_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$API_URL&$APP_ID&q="

    }

    fun execute(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}


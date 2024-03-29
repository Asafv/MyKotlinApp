package com.asafvaron.mykotlinapp.domain.model

/**
 * Created by asafvaron on 22/08/2017.
 */

data class ForecastList(val city: String, val country: String, val dailyForecast: List<Forecast>)

data class Forecast(val date: String, val description: String, val high: Int, val low: Int)
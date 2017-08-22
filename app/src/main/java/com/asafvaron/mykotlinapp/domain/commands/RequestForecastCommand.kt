package com.asafvaron.mykotlinapp.domain.commands

import com.asafvaron.mykotlinapp.data.io.ForecastRequest
import com.asafvaron.mykotlinapp.domain.mappers.ForecastDataMapper
import com.asafvaron.mykotlinapp.domain.model.ForecastList

/**
 * Created by asafvaron on 22/08/2017.
 */
class RequestForecastCommand(private val zipCode: String) : Command<ForecastList> {

    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}
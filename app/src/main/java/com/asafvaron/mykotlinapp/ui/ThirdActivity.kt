package com.asafvaron.mykotlinapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.asafvaron.mykotlinapp.R
import com.asafvaron.mykotlinapp.adapters.ForecastListAdapter
import com.asafvaron.mykotlinapp.data.io.ForecastRequest
import com.asafvaron.mykotlinapp.domain.commands.RequestForecastCommand
import kotlinx.android.synthetic.main.third_activity.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

// Define the activity
class ThirdActivity : AppCompatActivity() {

    val TAG: String = "ThirdActivity"

    // forecast items
    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.third_activity)

        showSomeLogsAndToasts()

        // init the recycler view with adapter
        val forecastList = findViewById<RecyclerView>(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)
        // load fake data
//        forecastList.adapter = ForecastListAdapter(items)

        testCasting()
        printSomeNonCasting()

        requestWeatherNoUiUpdate(getString(R.string.weather_forecast_url))

        requestWeatherWithUiUpdate()

//        testCopy()
    }

    private fun requestWeatherWithUiUpdate() {
        doAsync {
            val result = RequestForecastCommand("94043").execute()
            uiThread {
                forecast_list.adapter = ForecastListAdapter(result)
            }
        }

    }

    /**
     * This method will help us copy an immutable object and modify its properties
     */
//    private fun testCopy() {
//        val f1 = Forecast(Date(), 30.5f, "Hot Day")
////        f1.details = "Test" // cannot change immutable property.
//
//
//        // copy the immutable object and changing only temperature & details properties
//        val f2 = f1.copy(temperature = 15.5f, details = "Rainy Day")
////        val (date, temperature, details) = f2
//        val (_, _, _) = f2
//
//        val date1 = f2.component1()
//        val temp = f2.component2()
//        val detail = f2.component3()
//
//        println("object mapping: date: $date1 temp: $temp details: $detail")
//
//        println("forecast 1: $f1")
//        println("forecast 2: $f2")
//
//
//    }

    private fun requestWeatherNoUiUpdate(weather_forecast_url: String) {
        doAsync {
            ForecastRequest(weather_forecast_url).execute()
            uiThread { niceToast("Weather request performed") }
        }
    }

    private fun printSomeNonCasting() {
        val i = 12      //AnInt
        val iHex = 0x0f // An Int from hexadecimal literal
        val l = 3L      //ALong
        val d = 3.5     //ADouble
        val f = 3.5F    //AFloat

        println("printSomeNonCasting, int: $i hex: $iHex long: $l double: $d float: $f")

        val s = "This is a test"
        println("string array: $s, extract from array 2,8,11: ${s[2]} ${s[8]} ${s[11]}") // will print the char at position

        println("iterator on string array")
        for (c in s) {
            println(c)
        }
    }

    private fun testCasting() {
        // try double casting
        val j = 7 // An Int
        val d: Double = j.toDouble()
        println("testCasting: d: $d")

        // try char casting
        val c: Char = 'c'
        val i: Int = c.toInt()
        println("testCasting: i: $i")

    }

    private fun showSomeLogsAndToasts() {
        message.text = getString(R.string.welcome_to_forecast)
        // only prints if message has text
        println(message!!.text)

        // normal toast with SHORT duration as default
        toast("SHORT TOAST - DEFAULT")
        // normal toast with LONG duration
        toast("LONG TOAST WITH PARAM", Toast.LENGTH_LONG)

        niceToast("Sheker 1")
        niceToast("Sheker 2", "NO_TAG")

        // named arguments
        niceToast(message = "WEIRD", tag = "MyTag", length = Toast.LENGTH_SHORT)

        println(add(2, 2))
        println(add(5, 5, 5))
    }

    fun niceToast(message: String,
                  tag: String = ThirdActivity::class.java.simpleName,
                  length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, "[$tag] $message", length).show()
    }

    fun toast(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, length).show()
    }

    /* will add 2 numbers */
    fun add(x: Int, y: Int): Int = x + y

    /* Will add 3 numbers with function block */
    fun add(x: Int, y: Int, z: Int): Int {
        return x + y + z
    }
}
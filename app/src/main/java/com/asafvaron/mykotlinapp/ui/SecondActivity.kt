package com.asafvaron.mykotlinapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.asafvaron.mykotlinapp.R
import kotlinx.android.synthetic.main.second_activity.*
import org.jetbrains.anko.toast

// Define the activity
class SecondActivity : AppCompatActivity() {

    val TAG: String = "SecondActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        val args: Bundle? = intent?.extras
        println("$TAG $args")

        val name: String = args?.getString("name")!!
        val age: Int = args.getInt("age")


        tv_message.text = """
            Hello $name,
            You are $age years old.
            How are you?
            Do you Like Israel?
        """

        // Anko extension functions toast
        toast(tv_message.text)
    }

}
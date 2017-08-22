package com.asafvaron.mykotlinapp.ui

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.asafvaron.mykotlinapp.R
import com.asafvaron.mykotlinapp.models.Person
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_welcome.text = getString(R.string.hello_world)
        tv_welcome.textSize = 20F
        tv_welcome.alpha = 0f

        ValueAnimator.ofFloat(1F)
                .setDuration(2000L)
                .apply {
                    addUpdateListener {
                        val ratio = it.animatedValue as Float
                        tv_welcome.alpha = ratio
                    }
                }.start()

        btn_show_magic.setOnClickListener {
            val name: String = input_name?.text.toString()
            val age: Int? = input_age.text.toString().toIntOrNull() ?: 0
            println("$TAG name: $name age: $age")
            if (name.isNotBlank() && age!! > 0) {
                val p = Person(name, age)
                println(p.test)
                startFlow(p)
            } else {
                Toast.makeText(this, "Make sure all fields correct", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun startFlow(p: Person) {
        val i: Intent = Intent(this, SecondActivity::class.java)
        // add flags using bitwiseAnd
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_NEW_TASK
        i.putExtra("name", p.name)
        i.putExtra("age", p.age)
        startActivity(i)
    }

}

package com.asafvaron.mykotlinapp.models

/**
 * Created by asafvaron on 02/07/2017.
 */
open class Person(var name: String, var age: Int) {

    var test: String = ""
        get() = field.toUpperCase()
        set(value) {
            field = "Name: $value"
        }
}
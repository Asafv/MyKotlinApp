package com.asafvaron.mykotlinapp.domain.commands

/**
 * Created by asafvaron on 22/08/2017.
 */
interface Command<out T> {
    fun execute(): T
}
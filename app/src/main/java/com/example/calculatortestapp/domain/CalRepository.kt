package com.example.calculatortestapp.domain

import androidx.lifecycle.MutableLiveData

interface CalRepository {

    fun cleanData()

    fun deleteLast()

    fun calculate(string: String): String

    fun addSymbol(string: String)

    fun memorySave(number: Float)

    fun memoryRead()

    fun memoryClean()

    fun memoryPlus()

    fun memoryMinus()

    fun getProblemLiveData(): MutableLiveData<String>

    fun getAnswerLiveData(): MutableLiveData<String>
}
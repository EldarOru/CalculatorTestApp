package com.example.calculatortestapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.calculatortestapp.data.CalRepositoryImpl
import com.example.calculatortestapp.domain.CalRepository
import kotlin.math.pow

object FakeCalRepository {

    fun calculate(str: String): String {
        var parsedProblem = parseProblem(str)

        val parsedDiv = timesDivisionCalculate(parsedProblem)
        return addSubtractCalculate(parsedDiv).toString()

    }

    private fun calculateBrackets(list: MutableList<Any>): MutableList<Any>{
        val data = list
        var opBracket = 0
        var endBracket = 0
        for (i in 0 until data.size){
            if (data[i] == '(') opBracket = i
            if (data[i] == ')') endBracket = i
        }
        var brackets = data.subList(opBracket + 1, endBracket)
        brackets = timesDivisionCalculate(brackets)
        val number = addSubtractCalculate(brackets).toString()
        data.subList(opBracket, endBracket + 1).clear()
        data.add(opBracket, number)
        return data
    }

    private fun parseProblem(str: String): MutableList<Any>
    {
        var data = str

        var beginWithMinus = data[0] == '-'
        if (beginWithMinus){
            data = data.substring(1)
        }
        val list = mutableListOf<Any>()

        var currentDigit = ""
        for(character in data.indices) {
            if(data[character].isDigit() || data[character] == '.')
                currentDigit += data[character]
            else {
                if (beginWithMinus){
                    list.add(-currentDigit.toFloat())
                    beginWithMinus = false
                }else {
                    list.add(currentDigit.toFloat())
                }
                list.add(data[character])
                currentDigit = ""
            }
        }

        if(currentDigit != "") {
            if (beginWithMinus) {
                list.add(-currentDigit.toFloat())
            }else list.add(currentDigit.toFloat())
        }

        return if (list[list.size-1] is Number ){
            list
        }else{
            list.removeLast()
            list
        }

    }

    private fun addSubtractCalculate(passedList: MutableList<Any>): Float
    {
        var result = passedList[0] as Float

        for(i in passedList.indices)
        {
            if(passedList[i] is Char && i != passedList.lastIndex)
            {
                val operator = passedList[i]
                val nextDigit = passedList[i + 1] as Float
                if (operator == '+')
                    result += nextDigit
                if (operator == '-')
                    result -= nextDigit
            }
        }
        return result
    }

    private fun timesDivisionCalculate(passedList: MutableList<Any>): MutableList<Any>
    {
        var list = passedList
        while (list.contains('^'))
        {
            list = calcPow(list)
        }
        while (list.contains('*') || list.contains('/'))
        {
            list = calcTimesDiv(list)
        }
        return list
    }

    private fun calcTimesDiv(passedList: MutableList<Any>): MutableList<Any> {
        val newList = mutableListOf<Any>()
        var restartIndex = passedList.size

        for(i in passedList.indices)
        {
            if(passedList[i] is Char && i != passedList.lastIndex && i < restartIndex)
            {
                val operator = passedList[i]
                val prevDigit = passedList[i - 1] as Float
                val nextDigit = passedList[i + 1] as Float
                when(operator)
                {
                    '*' ->
                    {
                        newList.add(prevDigit * nextDigit)
                        restartIndex = i + 1
                    }
                    '/' ->
                    {
                        newList.add(prevDigit / nextDigit)
                        restartIndex = i + 1
                    }
                    else ->
                    {
                        newList.add(prevDigit)
                        newList.add(operator)
                    }
                }
            }

            if(i > restartIndex)
                newList.add(passedList[i])
        }

        return newList
    }

    private fun calcPow(passedList: MutableList<Any>): MutableList<Any> {
        val newList = mutableListOf<Any>()
        var restartIndex = passedList.size

        for(i in passedList.indices)
        {
            if(passedList[i] is Char && i != passedList.lastIndex && i < restartIndex)
            {
                val operator = passedList[i]
                val prevDigit = passedList[i - 1] as Float
                val nextDigit = passedList[i + 1] as Float
                when(operator)
                {
                    '^' ->
                    {
                        newList.add((prevDigit.toDouble().pow(nextDigit.toDouble())).toFloat())
                        restartIndex = i + 1
                    }
                    else ->
                    {
                        newList.add(prevDigit)
                        newList.add(operator)
                    }
                }
            }

            if(i > restartIndex)
                newList.add(passedList[i])
        }

        return newList
    }

}
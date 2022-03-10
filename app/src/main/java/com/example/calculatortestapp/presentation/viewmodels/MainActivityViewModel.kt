package com.example.calculatortestapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.calculatortestapp.data.CalRepositoryImpl
import com.example.calculatortestapp.domain.usecases.*
import java.lang.Exception

class MainActivityViewModel(private val addSymbolUseCase: AddSymbolUseCase,
                            private val calculateUseCase: CalculateUseCase,
                            private val cleanEverythingUseCase: CleanEverythingUseCase,
                            private val deleteLastUseCase: DeleteLastUseCase,
                            private val getAnswerLiveDataUseCase: GetAnswerLiveDataUseCase,
                            private val getProblemLiveDataUseCase: GetProblemLiveDataUseCase,
                            private val memoryCleanUseCase: MemoryCleanUseCase,
                            private val memoryMinusUseCase: MemoryMinusUseCase,
                            private val memoryPlusUseCase: MemoryPlusUseCase,
                            private val memoryReadUseCase: MemoryReadUseCase,
                            private val memorySaveUseCase: MemorySaveUseCase): ViewModel() {

    val problemLiveData = getProblemLiveDataUseCase.invoke()

    val answerLiveData = getAnswerLiveDataUseCase.invoke()

    private var canAddOperation = false
    private var canAddDecimal = true

    fun addNumber(symbol: String){
        if (symbol == "."){
            if (canAddDecimal)
                addSymbolUseCase.invoke(symbol)
            canAddDecimal = false
        }
        else
            addSymbolUseCase.invoke(symbol)
        canAddOperation = true

    }

    fun addOperation(symbol: String){
        if (symbol == "-" && problemLiveData.value!!.isEmpty()){
            addSymbolUseCase.invoke(symbol)
            return
        }

        if (canAddOperation){
            addSymbolUseCase.invoke(symbol)
            canAddOperation = false
            canAddDecimal = true
        }
    }

    fun cleanEverything(){
        cleanEverythingUseCase.invoke()
        canAddOperation = false
        canAddDecimal = true
    }

    fun deleteLast(){
        if (problemLiveData.value!!.isNotEmpty()) {
            when {
                problemLiveData.value?.last() == '.' -> {

                }
                setOfOperators.contains(problemLiveData.value?.last()) -> {
                    canAddOperation = true
                }
                else -> {
                    canAddOperation = false
                    canAddDecimal = true
                }
            }
        }
        deleteLastUseCase.invoke()
        if (problemLiveData.value!!.isEmpty()){
            canAddOperation = false
            canAddDecimal = true
        }
    }

    fun calculate(string: String){
        try {
            answerLiveData.value = calculateUseCase.invoke(string)
        }catch (ex: Exception){
            answerLiveData.value = "Error"
        }
    }

    fun memorySave(string: String) {
        try {
            memorySaveUseCase.invoke(string.toFloat())
        }catch (e: Exception){
            memorySaveUseCase.invoke(0.0f)
        }
    }

    fun memoryRead() {
        memoryReadUseCase.invoke()
    }

    fun memoryClean() {
        memoryCleanUseCase.invoke()
    }

    fun memoryPlus() {
        memoryPlusUseCase.invoke()
    }

    fun memoryMinus() {
        memoryMinusUseCase.invoke()
    }

    companion object{
        val setOfOperators = hashSetOf('*', '/', '+', '-', '^')
    }
}
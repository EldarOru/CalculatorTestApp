package com.example.calculatortestapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.calculatortestapp.domain.CalRepository
import com.example.calculatortestapp.domain.usecases.*
import com.example.calculatortestapp.presentation.viewmodels.MainActivityViewModel

class ViewModelFactory(repository: CalRepository): ViewModelProvider.Factory {

    private val addSymbolUseCase = AddSymbolUseCase(repository)
    private val calculateUseCase = CalculateUseCase(repository)
    private val cleanEverythingUseCase = CleanEverythingUseCase(repository)
    private val deleteLastUseCase = DeleteLastUseCase(repository)
    private val memoryCleanUseCase = MemoryCleanUseCase(repository)
    private val memoryMinusUseCase = MemoryMinusUseCase(repository)
    private val memoryPlusUseCase = MemoryPlusUseCase(repository)
    private val memoryReadUseCase = MemoryReadUseCase(repository)
    private val memorySaveUseCase = MemorySaveUseCase(repository)
    private val getProblemLiveDataUseCase = GetProblemLiveDataUseCase(repository)
    private val getAnswerLiveDataUseCase = GetAnswerLiveDataUseCase(repository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(calculateUseCase = calculateUseCase,
                addSymbolUseCase = addSymbolUseCase,
                cleanEverythingUseCase = cleanEverythingUseCase,
                deleteLastUseCase = deleteLastUseCase,
                memoryCleanUseCase = memoryCleanUseCase,
                memoryMinusUseCase = memoryMinusUseCase,
                memoryPlusUseCase = memoryPlusUseCase,
                memoryReadUseCase = memoryReadUseCase,
                memorySaveUseCase = memorySaveUseCase,
                getAnswerLiveDataUseCase = getAnswerLiveDataUseCase,
                getProblemLiveDataUseCase = getProblemLiveDataUseCase) as T
        }
        throw IllegalAccessException("ViewModel class is not found")
    }
}
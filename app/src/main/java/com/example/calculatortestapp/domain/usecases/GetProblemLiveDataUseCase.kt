package com.example.calculatortestapp.domain.usecases

import androidx.lifecycle.MutableLiveData
import com.example.calculatortestapp.domain.CalRepository

class GetProblemLiveDataUseCase(private val calRepository: CalRepository) {
    operator fun invoke(): MutableLiveData<String> {
        return calRepository.getProblemLiveData()
    }
}
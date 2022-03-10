package com.example.calculatortestapp.domain.usecases

import androidx.lifecycle.MutableLiveData
import com.example.calculatortestapp.domain.CalRepository

class GetAnswerLiveDataUseCase(private val calRepository: CalRepository) {
    operator fun invoke(): MutableLiveData<String> {
        return calRepository.getAnswerLiveData()
    }
}
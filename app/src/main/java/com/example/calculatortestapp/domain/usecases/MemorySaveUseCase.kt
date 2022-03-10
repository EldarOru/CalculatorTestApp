package com.example.calculatortestapp.domain.usecases

import com.example.calculatortestapp.domain.CalRepository

class MemorySaveUseCase(private val calRepository: CalRepository) {
    operator fun invoke(number: Float){
        calRepository.memorySave(number)
    }
}
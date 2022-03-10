package com.example.calculatortestapp.domain.usecases

import com.example.calculatortestapp.domain.CalRepository

class MemoryPlusUseCase(private val calRepository: CalRepository) {
    operator fun invoke(){
        calRepository.memoryPlus()
    }
}
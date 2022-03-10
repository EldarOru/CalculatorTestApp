package com.example.calculatortestapp.domain.usecases

import com.example.calculatortestapp.domain.CalRepository

class MemoryReadUseCase(private val calRepository: CalRepository) {
    operator fun invoke(){
        calRepository.memoryRead()
    }
}
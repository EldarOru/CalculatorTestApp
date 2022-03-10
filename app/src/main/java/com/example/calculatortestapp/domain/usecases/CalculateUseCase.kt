package com.example.calculatortestapp.domain.usecases

import com.example.calculatortestapp.domain.CalRepository

class CalculateUseCase(private val calRepository: CalRepository) {
    operator fun invoke(string: String){
        calRepository.calculate(string)
    }
}
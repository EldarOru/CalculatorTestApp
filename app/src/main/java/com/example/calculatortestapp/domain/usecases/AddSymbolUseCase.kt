package com.example.calculatortestapp.domain.usecases

import com.example.calculatortestapp.domain.CalRepository

class AddSymbolUseCase(private val calRepository: CalRepository) {
    operator fun invoke(symbol: String){
        calRepository.addSymbol(symbol)
    }
}
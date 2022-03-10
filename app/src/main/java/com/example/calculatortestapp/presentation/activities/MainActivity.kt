package com.example.calculatortestapp.presentation.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.calculatortestapp.data.CalRepositoryImpl
import com.example.calculatortestapp.databinding.ActivityMainBinding
import com.example.calculatortestapp.presentation.ViewModelFactory
import com.example.calculatortestapp.presentation.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        mainActivityViewModel = ViewModelProvider(this, ViewModelFactory(CalRepositoryImpl))
            .get(MainActivityViewModel::class.java)
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        setContentView(mainActivityBinding.root)
        setListeners()
        setLiveDataObserves()
        readSharedPreferences()
    }

    private fun setListeners(){
        setNumberListeners()
        setOperationListeners()
        setSpecialListeners()
    }

    private fun setNumberListeners(){
        mainActivityBinding.apply {
            buttonOne.setOnClickListener { mainActivityViewModel.addNumber("1") }
            buttonTwo.setOnClickListener { mainActivityViewModel.addNumber("2") }
            buttonThree.setOnClickListener { mainActivityViewModel.addNumber("3") }
            buttonFour.setOnClickListener { mainActivityViewModel.addNumber("4") }
            buttonFive.setOnClickListener { mainActivityViewModel.addNumber("5") }
            buttonSix.setOnClickListener { mainActivityViewModel.addNumber("6") }
            buttonSeven.setOnClickListener { mainActivityViewModel.addNumber("7") }
            buttonEight.setOnClickListener { mainActivityViewModel.addNumber("8") }
            buttonNine.setOnClickListener { mainActivityViewModel.addNumber("9") }
            buttonZero.setOnClickListener { mainActivityViewModel.addNumber("0") }
            buttonDelimiter.setOnClickListener { mainActivityViewModel.addNumber(".") }
            buttonSquarePow.setOnClickListener { mainActivityViewModel.addOperation("^")
                mainActivityViewModel.addNumber("2")}
            buttonCubePow.setOnClickListener { mainActivityViewModel.addOperation("^")
                mainActivityViewModel.addNumber("3") }
            buttonPow.setOnClickListener { mainActivityViewModel.addOperation("^") }
        }
    }

    private fun setOperationListeners(){
        mainActivityBinding.apply {
            buttonDivide.setOnClickListener { mainActivityViewModel.addOperation("/") }
            buttonMultiply.setOnClickListener { mainActivityViewModel.addOperation("*") }
            buttonPlus.setOnClickListener { mainActivityViewModel.addOperation("+") }
            buttonMinus.setOnClickListener { mainActivityViewModel.addOperation("-") }
        }
    }

    private fun setSpecialListeners(){
        mainActivityBinding.apply {
            buttonClean.setOnClickListener { mainActivityViewModel.cleanEverything() }
            buttonDeleteLast.setOnClickListener { mainActivityViewModel.deleteLast() }
            buttonResult.setOnClickListener {
                mainActivityViewModel.calculate(mainActivityViewModel.problemLiveData.value!!)
            }
            buttonMc.setOnClickListener { mainActivityViewModel.memoryClean() }
            buttonMr.setOnClickListener { mainActivityViewModel.memoryRead() }
            buttonMs.setOnClickListener { mainActivityViewModel.memorySave(mainActivityViewModel.answerLiveData.value!!) }
            buttonMMinus.setOnClickListener { mainActivityViewModel.memoryMinus() }
            buttonMPlus.setOnClickListener { mainActivityViewModel.memoryPlus() }
        }
    }

    private fun setLiveDataObserves(){
        mainActivityViewModel.problemLiveData.observe(this){
            mainActivityBinding.tvQuestion.text = it
            writeProblemSharedPreferences(it)
        }

        mainActivityViewModel.answerLiveData.observe(this){
            mainActivityBinding.tvResult.text = it
            writeAnswerSharedPreferences(it)
        }
    }

    private fun readSharedPreferences(){
        mainActivityViewModel.problemLiveData.value = sharedPreferences.getString(PROBLEM_DATA, "")
        mainActivityViewModel.answerLiveData.value = sharedPreferences.getString(ANSWER_DATA, "")
    }

    private fun writeProblemSharedPreferences(str: String){
        val editor = sharedPreferences.edit()
        editor.apply {
            putString(PROBLEM_DATA, str)
            apply()
        }
    }

    private fun writeAnswerSharedPreferences(str: String){
        val editor = sharedPreferences.edit()
        editor.apply {
            putString(ANSWER_DATA, str)
            apply()
        }
    }

    companion object{
        const val SHARED_PREFERENCES_NAME = "SAVE_DATA"
        const val PROBLEM_DATA = "PROBLEM_DATA"
        const val ANSWER_DATA = "ANSWER_DATA"
    }
}



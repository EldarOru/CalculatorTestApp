package com.example.calculatortestapp

import com.example.calculatortestapp.data.CalRepositoryImpl
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun `test_plus`(){
        assertEquals(8.0f, FakeCalRepository.calculate("5+3").toFloat())
    }

    @Test
    fun `test_multiply`(){
        assertEquals(15.0f, FakeCalRepository.calculate("5*3").toFloat())
    }

    @Test
    fun `test_minus`(){
        assertEquals(2.0f, FakeCalRepository.calculate("5-3").toFloat())
    }

    @Test
    fun `test_divide`(){
        assertEquals(2.0f, FakeCalRepository.calculate("6/3").toFloat())
    }

    @Test
    fun `test_pow`(){
        assertEquals(125.0f, FakeCalRepository.calculate("5^3").toFloat())
    }

    @Test
    fun `test_difficult_problem`(){
        assertEquals(28.0f, FakeCalRepository.calculate("5+3*2^3-1").toFloat())
    }

}
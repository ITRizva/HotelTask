package com.example.hoteltest.navigation

import com.example.hoteltest.order.presentation.ui.OrderFragment
import java.io.Serializable

interface NavigatorInterface{
    fun stepBack()
    fun addScreen(data: Serializable, fragmentKey:String?)
    fun replaceScreen(data: Serializable, fragmentKey:String?)
    fun popUntil(fragmentKey:String)
}

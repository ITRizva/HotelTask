package com.example.hoteltest.navigation

import androidx.fragment.app.Fragment
import java.io.Serializable

interface NavigatorInterface{
    fun stepBack()
    fun addScreen(data: Serializable?, fragmentKey:String?)
    fun replaceScreen(data:Serializable?,fragmentKey:String?)
    fun popUntil(fragmentKey:String)
}

package com.example.hoteltest

import androidx.fragment.app.Fragment

interface NavigatorInterface{
    fun stepBack()
    fun addScreen(fragment: Fragment)
    fun replaceScreen(fragment: Fragment)
    fun popUntil(fragmentName:String)
}
fun Fragment.navigator(): NavigatorInterface{
    return requireActivity() as NavigatorInterface
}
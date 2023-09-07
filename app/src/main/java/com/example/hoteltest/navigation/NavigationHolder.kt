package com.example.hoteltest.navigation

import androidx.appcompat.app.AppCompatActivity
class NavigationHolder() {
    private val navigator = NavigatorImp()
    fun getNavigator():NavigatorInterface = navigator

    fun attachActivity(activity: AppCompatActivity){
        navigator.attachActivity(activity)
    }
    fun detachActivity(){
        navigator.detachActivity()
    }

}
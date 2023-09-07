package com.example.hoteltest.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.io.Serializable
import javax.inject.Inject

class NavigatorImp: NavigatorInterface{

    private var fragmentManager:FragmentManager? = null
    fun attachActivity(activity:AppCompatActivity){
        fragmentManager = activity.supportFragmentManager
    }

    fun detachActivity(){
        fragmentManager = null
    }

    override fun stepBack() {
        TODO("Not yet implemented")
    }

    override fun addScreen(data: Serializable?, fragmentKey:String?) {
        TODO("Not yet implemented")
    }

    override fun replaceScreen(data: Serializable?, fragmentKey:String?) {
        TODO("Not yet implemented")
    }

    override fun popUntil(key: String) {
        TODO("Not yet implemented")
    }
}

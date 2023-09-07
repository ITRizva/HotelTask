package com.example.hoteltest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.hoteltest.hotel.presentation.ui.HotelFragment
import com.example.hoteltest.navigation.NavigatorInterface

import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigatorInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.mainContainer, HotelFragment())
                .commit()
        }
    }

    override fun stepBack() {
        supportFragmentManager.popBackStack()
    }

    override fun addScreen(fragment: Fragment) {
        launchAddFragment(fragment)
    }

    override fun replaceScreen(fragment: Fragment) {
        launchReplaceFragment(fragment)
    }

    override fun popUntil(fragmentName: String) {
        supportFragmentManager.popBackStack(fragmentName, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    private fun launchAddFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.mainContainer, fragment).addToBackStack(fragment.tag).commit()
    }
    private fun launchReplaceFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.mainContainer,fragment).addToBackStack(fragment.tag).commit()
    }


}




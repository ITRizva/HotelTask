package com.example.hoteltest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.hoteltest.hotel.presentation.ui.HotelFragment
import com.example.hoteltest.navigation.NavigationHolder
import com.example.hoteltest.navigation.NavigatorInterface

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigationHolder: NavigationHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            navigationHolder.attachActivity(this)
            supportFragmentManager.beginTransaction().add(R.id.mainContainer, HotelFragment()).commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navigationHolder.detachActivity()
    }
}




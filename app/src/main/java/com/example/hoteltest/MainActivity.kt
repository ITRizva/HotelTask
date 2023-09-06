package com.example.hoteltest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.hoteltest.presentation.hotel.HotelScreen

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigatorInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.mainContainer, HotelScreen())
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




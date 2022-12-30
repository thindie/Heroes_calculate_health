package com.example.thindie.heroes.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private val viewModel: HeroesViewModel by lazy {
        ViewModelProvider(this)[HeroesViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            Log.d("Service", "Main")
            Heroes(
                viewModel = viewModel,
                modifier = Modifier.padding(
                )
            )

        }
    }
}



package com.example.thindie.heroes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModelProvider
import com.example.thindie.heroes.R

class MainActivity : AppCompatActivity() {
    private val viewModel: HeroesViewModel by lazy {
        ViewModelProvider(this)[HeroesViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { 
            Surface(
                content = { Heroes(viewModel = viewModel) }
            )
        }
    }
}
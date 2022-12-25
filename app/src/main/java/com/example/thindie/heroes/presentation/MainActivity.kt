package com.example.thindie.heroes.presentation

import android.content.res.Resources.Theme
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModelProvider
import com.example.thindie.heroes.R
import com.example.thindie.heroes.presentation.ui.theme.HeroesTheme

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
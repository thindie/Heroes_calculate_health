package com.example.thindie.heroes.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private val viewModel: HeroesViewModel by lazy {
        ViewModelProvider(this)[HeroesViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.allMonsters.observeForever {
            println(it.forEach {
                it.name
            })
        }
       /* setContent {
            Surface(
                content = { Heroes(viewModel = viewModel) }
            )
        }*/
    }
}

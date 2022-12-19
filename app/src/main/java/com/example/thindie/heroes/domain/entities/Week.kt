package com.example.thindie.heroes.domain.entities

data class Week (
    var weekNumber: Int = FIRST_WEEK
        )
{
    companion object{
        private const val FIRST_WEEK = 1
    }
}
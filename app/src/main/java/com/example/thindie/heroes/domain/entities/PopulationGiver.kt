package com.example.thindie.heroes.domain.entities

class PopulationGiver {
    companion object {
        const val FORT = 1.0
        const val CITADEL = 1.2
        const val CASTLE = 1.5
        fun multiplier(any: Any) : Int {
            when (any) {
                is Dwelling -> {}
                is Monster -> {}
                else -> {}
            }
            return 1
        }
    }
}
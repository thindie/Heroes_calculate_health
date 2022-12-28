package com.example.thindie.heroes.domain.entities

class PopulationGiver {
    companion object {
        private const val FORT = 1
        const val CITADEL = 1.5
        private const val CASTLE = 2
        fun multiplier(any: Any) : Int {
            when (any) {
                is Dwelling -> {return  any.growth * FORT}
                is Monster -> {return any.growth * CASTLE}
                else -> {}
            }
            return FORT
        }
    }
}
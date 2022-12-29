package com.example.thindie.heroes.domain.entities

import android.util.Log

class PopulationGiver {
    companion object {
        private const val FORT = 1
        const val CITADEL = 1.5
        private const val CASTLE = 2
        fun multiplier(dwelling: Dwelling?, monster: Monster?) : Int {
            if(dwelling == null)return  CASTLE
            if(monster == null)return  FORT
            return FORT
        }
    }
}
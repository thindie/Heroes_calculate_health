package com.example.thindie.heroes.data.local

import com.example.thindie.heroes.domain.entities.Fraction
import com.example.thindie.heroes.domain.entities.FractionPair
import com.example.thindie.heroes.domain.entities.Monster

class FractionData(private  val fileReader: RawResourceReader) {

    private var monsterList: List<Monster>? = null

    fun getAllCreatures(): List<Monster> {
        if(monsterList.isNullOrEmpty()) {
            monsterList = fileReader.readFromCreaturesFile()
        }
           monsterList.let {
               it!!.forEach { monster ->
                   monster.fraction = detectFraction(monster.name) }
           }
        return monsterList as List<Monster>
    }

    private fun detectFraction(name: String): Fraction {
        Fraction.values().forEach { fraction ->
            val fractionList = representFraction(fraction)
            val monsterToCompare = getSingleMonsterByName(name)
            fractionList.forEach { fractionMonster ->
                if (monsterToCompare.name == fractionMonster.name)
                    return fraction
            }
        }
        throw RuntimeException("Fraction in detectFraction detected incorrect")
    }

    fun representFraction(fraction: Fraction): List<Monster> {
        val fractionList: MutableList<Monster> = mutableListOf()
        when (fraction) {
            Fraction.CASTLE -> {
                fractionList.add(getSingleMonsterByName("Pikeman"))
                fractionList.add(getSingleMonsterByName("Halberdier"))
                fractionList.add(getSingleMonsterByName("Archer"))
                fractionList.add(getSingleMonsterByName("Marksman"))
                fractionList.add(getSingleMonsterByName("Griffin"))
                fractionList.add(getSingleMonsterByName("RoyalGriffin"))
                fractionList.add(getSingleMonsterByName("Swordsman"))
                fractionList.add(getSingleMonsterByName("Crusader"))
                fractionList.add(getSingleMonsterByName("Monk"))
                fractionList.add(getSingleMonsterByName("Zealot"))
                fractionList.add(getSingleMonsterByName("Cavalier"))
                fractionList.add(getSingleMonsterByName("Champion"))
                fractionList.add(getSingleMonsterByName("Angel"))
                fractionList.add(getSingleMonsterByName("Archangel"))
            }
            Fraction.RAMPART -> {
                fractionList.add(getSingleMonsterByName("Centaur"))
                fractionList.add(getSingleMonsterByName("CentaurCaptain"))
                fractionList.add(getSingleMonsterByName("Dwarf"))
                fractionList.add(getSingleMonsterByName("BattleDwarf"))
                fractionList.add(getSingleMonsterByName("WoodElf"))
                fractionList.add(getSingleMonsterByName("GrandElf"))
                fractionList.add(getSingleMonsterByName("Pegasus"))
                fractionList.add(getSingleMonsterByName("SilverPegasus"))
                fractionList.add(getSingleMonsterByName("DendroidGuard"))
                fractionList.add(getSingleMonsterByName("DendroidSoldier"))
                fractionList.add(getSingleMonsterByName("Unicorn"))
                fractionList.add(getSingleMonsterByName("WarUnicorn"))
                fractionList.add(getSingleMonsterByName("GreenDragon"))
                fractionList.add(getSingleMonsterByName("GoldDragon"))
            }
            Fraction.TOWER -> {
                fractionList.add(getSingleMonsterByName("Gremlin"))
                fractionList.add(getSingleMonsterByName("MasterGremlin"))
                fractionList.add(getSingleMonsterByName("StoneGargoyle"))
                fractionList.add(getSingleMonsterByName("ObsidianGargoyle"))
                fractionList.add(getSingleMonsterByName("StoneGolem"))
                fractionList.add(getSingleMonsterByName("IronGolem"))
                fractionList.add(getSingleMonsterByName("Mage"))
                fractionList.add(getSingleMonsterByName("ArchMage"))
                fractionList.add(getSingleMonsterByName("Genie"))
                fractionList.add(getSingleMonsterByName("MasterGenie"))
                fractionList.add(getSingleMonsterByName("Naga"))
                fractionList.add(getSingleMonsterByName("NagaQueen"))
                fractionList.add(getSingleMonsterByName("Giant"))
                fractionList.add(getSingleMonsterByName("Titan"))
            }
            Fraction.INFERNO -> {
                fractionList.add(getSingleMonsterByName("Imp"))
                fractionList.add(getSingleMonsterByName("Familiar"))
                fractionList.add(getSingleMonsterByName("Gog"))
                fractionList.add(getSingleMonsterByName("Magog"))
                fractionList.add(getSingleMonsterByName("HellHound"))
                fractionList.add(getSingleMonsterByName("Cerberus"))
                fractionList.add(getSingleMonsterByName("Demon"))
                fractionList.add(getSingleMonsterByName("HornedDemon"))
                fractionList.add(getSingleMonsterByName("PitFiend"))
                fractionList.add(getSingleMonsterByName("PitLord"))
                fractionList.add(getSingleMonsterByName("Efreeti"))
                fractionList.add(getSingleMonsterByName("EfreetSultan"))
                fractionList.add(getSingleMonsterByName("Devil"))
                fractionList.add(getSingleMonsterByName("ArchDevil"))
            }
            Fraction.NECRO -> {
                fractionList.add(getSingleMonsterByName("Skeleton"))
                fractionList.add(getSingleMonsterByName("SkeletonWarrior"))
                fractionList.add(getSingleMonsterByName("WalkingDead"))
                fractionList.add(getSingleMonsterByName("Zombie"))
                fractionList.add(getSingleMonsterByName("Wight"))
                fractionList.add(getSingleMonsterByName("Wraith"))
                fractionList.add(getSingleMonsterByName("Vampire"))
                fractionList.add(getSingleMonsterByName("VampireLord"))
                fractionList.add(getSingleMonsterByName("Lich"))
                fractionList.add(getSingleMonsterByName("PowerLich"))
                fractionList.add(getSingleMonsterByName("BlackKnight"))
                fractionList.add(getSingleMonsterByName("DreadKnight"))
                fractionList.add(getSingleMonsterByName("BoneDragon"))
                fractionList.add(getSingleMonsterByName("GhostDragon"))
            }
            Fraction.DUNGEON -> {
                fractionList.add(getSingleMonsterByName("Troglodyte"))
                fractionList.add(getSingleMonsterByName("InfernalTroglodyte"))
                fractionList.add(getSingleMonsterByName("Harpy"))
                fractionList.add(getSingleMonsterByName("HarpyHag"))
                fractionList.add(getSingleMonsterByName("Beholder"))
                fractionList.add(getSingleMonsterByName("EvilEye"))
                fractionList.add(getSingleMonsterByName("Medusa"))
                fractionList.add(getSingleMonsterByName("MedusaQueen"))
                fractionList.add(getSingleMonsterByName("Minotaur"))
                fractionList.add(getSingleMonsterByName("MinotaurKing"))
                fractionList.add(getSingleMonsterByName("Manticore"))
                fractionList.add(getSingleMonsterByName("Scorpicore"))
                fractionList.add(getSingleMonsterByName("RedDragon"))
                fractionList.add(getSingleMonsterByName("BlackDragon"))
            }
            Fraction.CITADEL -> {
                fractionList.add(getSingleMonsterByName("Goblin"))
                fractionList.add(getSingleMonsterByName("Hobgoblin"))
                fractionList.add(getSingleMonsterByName("WolfRider"))
                fractionList.add(getSingleMonsterByName("WolfRaider"))
                fractionList.add(getSingleMonsterByName("Orc"))
                fractionList.add(getSingleMonsterByName("OrcChieftain"))
                fractionList.add(getSingleMonsterByName("Ogre"))
                fractionList.add(getSingleMonsterByName("OgreMage"))
                fractionList.add(getSingleMonsterByName("Roc"))
                fractionList.add(getSingleMonsterByName("Thunderbird"))
                fractionList.add(getSingleMonsterByName("Cyclops"))
                fractionList.add(getSingleMonsterByName("CyclopsKing"))
                fractionList.add(getSingleMonsterByName("Behemoth"))
                fractionList.add(getSingleMonsterByName("AncientBehemoth"))
            }
            Fraction.FORTRESS -> {
                fractionList.add(getSingleMonsterByName("Gnoll"))
                fractionList.add(getSingleMonsterByName("GnollMarauder"))
                fractionList.add(getSingleMonsterByName("Lizardman"))
                fractionList.add(getSingleMonsterByName("LizardWarrior"))
                fractionList.add(getSingleMonsterByName("SerpentFly"))
                fractionList.add(getSingleMonsterByName("DragonFly"))
                fractionList.add(getSingleMonsterByName("Basilisk"))
                fractionList.add(getSingleMonsterByName("GreaterBasilisk"))
                fractionList.add(getSingleMonsterByName("Gorgon"))
                fractionList.add(getSingleMonsterByName("MightyGorgon"))
                fractionList.add(getSingleMonsterByName("Wyvern"))
                fractionList.add(getSingleMonsterByName("WyvernMonarch"))
                fractionList.add(getSingleMonsterByName("Hydra"))
                fractionList.add(getSingleMonsterByName("ChaosHydra"))
            }
            Fraction.CONFLUX -> {
                fractionList.add(getSingleMonsterByName("Pixie"))
                fractionList.add(getSingleMonsterByName("Sprite"))
                fractionList.add(getSingleMonsterByName("AirElemental"))
                fractionList.add(getSingleMonsterByName("StormElemental"))
                fractionList.add(getSingleMonsterByName("WaterElemental"))
                fractionList.add(getSingleMonsterByName("IceElemental"))
                fractionList.add(getSingleMonsterByName("FireElemental"))
                fractionList.add(getSingleMonsterByName("EnergyElemental"))
                fractionList.add(getSingleMonsterByName("EarthElemental"))
                fractionList.add(getSingleMonsterByName("MagmaElemental"))
                fractionList.add(getSingleMonsterByName("PsychicElemental"))
                fractionList.add(getSingleMonsterByName("MagicElemental"))
                fractionList.add(getSingleMonsterByName("Firebird"))
                fractionList.add(getSingleMonsterByName("Phoenix"))
            }
            Fraction.NEUTRAL -> {
                throw RuntimeException("Something with Fraction detecting")
            }
        }
        return fractionList
    }

    fun getSingleMonsterByName(name: String): Monster {
        monsterList!!.forEach { monster ->
            if (monster.name == name) {
                return monster
            }
        }

        throw RuntimeException("search single monster malfunction")
    }

    fun getFractionImage(): List<FractionPair> {
        return listOf(
            FractionPair(
                Fraction.CASTLE,
                "https://heroes.thelazy.net/images/6/63/Adventure_Map_Castle_capitol.gif"
            ),
            FractionPair(
                Fraction.RAMPART,
                "https://heroes.thelazy.net/images/c/cd/Adventure_Map_Rampart_capitol.gif"
            ),
            FractionPair(
                Fraction.TOWER,
                "https://heroes.thelazy.net/images/9/9f/Adventure_Map_Tower_capitol.gif"
            ),
            FractionPair(
                Fraction.INFERNO,
                "https://heroes.thelazy.net/images/0/03/Adventure_Map_Inferno_capitol.gif"
            ),
            FractionPair(
                Fraction.NECRO,
                "https://heroes.thelazy.net/images/7/70/Adventure_Map_Necropolis_capitol.gif"
            ),
            FractionPair(
                Fraction.DUNGEON,
                "https://heroes.thelazy.net/images/7/74/Adventure_Map_Dungeon_capitol.gif"
            ),
            FractionPair(
                Fraction.CITADEL,
                "https://heroes.thelazy.net/images/5/50/Adventure_Map_Stronghold_capitol.gif"
            ),
            FractionPair(
                Fraction.FORTRESS,
                "https://heroes.thelazy.net/images/d/df/Adventure_Map_Fortress_capitol.gif"
            ),
            FractionPair(
                Fraction.CONFLUX,
                "https://heroes.thelazy.net/images/a/ac/Adventure_Map_Conflux_capitol.gif"
            )
        )
    }
}
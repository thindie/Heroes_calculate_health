package com.example.thindie.heroes.data.web

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class JsoupGetter {
    val heroes = "https://heroes.thelazy.net/"

    @RequiresApi(Build.VERSION_CODES.N)
     fun get() {
        val doc = Jsoup.connect("$heroes/index.php/List_of_creatures").get()    // <1>
        doc.select(".whiteSortArrows:first-of-type tbody td:first-of-type a")    // <2>
            .map { col -> col.attr("title") }    // <3>
            .parallelStream()    // <4>
            .map { extractMovieData(it) }    // <5>
            .filter { it != null }
            .forEach { Log.d("SERVICE_TAG", "$it") }
    }

    private fun extractMovieData(url: String): MonsterWeb? { // <1>
        val doc: Document
        try {
            doc = Jsoup.connect("$heroes$url").get()  // <2>
        } catch (e: Exception) {
            return null
        }

        val monster = MonsterWeb("", "", "", "", ""
            , "", "", "", "", "", "")// <3>
        doc.select(".infobox tr")   // <4>
            .forEach { ele ->   // <5>
                when {
                    ele.getElementsByTag("a href").hasClass("mw-redirect") ?: false -> {   // <6>
                        monster.name = ele.getElementsByTag("a").text()
                    }
                    else -> {
                        val value: String? = if (ele.getElementsByTag("td").size > 1)
                            ele.getElementsByTag("td")
                                .map(Element::text)
                                .filter(String::isNotEmpty)
                                .joinToString(", ") else
                            ele.getElementsByTag("span").first()?.text() // <7>

                        when (ele.getElementsByTag("span").first()?.text()) {    // <8>
                            "Attack" -> monster.attack = value ?: ""
                            "Defence" -> monster.defense = value ?: ""
                            "Minimum Damage" -> monster.minimumDamage = value ?: ""
                            "Maximum Damage" -> monster.maximumDamage = value ?: ""
                            "Health" -> monster.health = value ?: ""
                            "Speed" -> monster.speed = value ?: ""
                            "Growth" -> monster.growth = value ?: ""
                        }
                    }
                }
            }
        return monster
    }
}
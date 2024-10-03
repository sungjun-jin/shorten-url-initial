package me.sj.study.shortenurlinitial.domain

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "shorten_url")
class ShortenUrl(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    @Column(nullable = false, unique = false)
    var originalUrl: String,

    @Column(nullable = false, unique = true)
    var shortenUrlKey: String,

    @Column(nullable = false)
    var redirectionCount: Long = 0L
) {

    fun increaseRedirectCount() {
        this.redirectionCount = this.redirectionCount + 1
    }

    companion object {
        fun generateShortenUrlKey(): String {
            val base56Characters = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz"
            val random = Random()
            val shortenUrlKey = StringBuilder()
            for (count in 0..7) {
                val base56CharactersIndex: Int = random.nextInt(base56Characters.length)
                val base56Character = base56Characters[base56CharactersIndex]
                shortenUrlKey.append(base56Character)
            }
            return shortenUrlKey.toString()
        }
    }
}
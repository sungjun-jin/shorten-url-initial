package me.sj.study.shortenurlinitial.domain

interface ShortenUrlRepository {

    fun saveShortenUrl(shortenUrl: ShortenUrl)

    fun findShortenUrlByShortenUrlKey(key: String): ShortenUrl?
}
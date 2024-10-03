package me.sj.study.shortenurlinitial.infrastructure

import me.sj.study.shortenurlinitial.domain.ShortenUrl
import org.springframework.data.jpa.repository.JpaRepository

interface JpaShortenUrlRepository : JpaRepository<ShortenUrl, Long> {
    fun findByShortenUrlKey(key: String): ShortenUrl?
}
package me.sj.study.shortenurlinitial.infrastructure

import me.sj.study.shortenurlinitial.domain.ShortenUrl
import me.sj.study.shortenurlinitial.domain.ShortenUrlRepository
import org.springframework.stereotype.Repository

@Repository
class ShortenUrlRepositoryImpl(
    private val jpaShortenUrlRepository: JpaShortenUrlRepository
) : ShortenUrlRepository {
    override fun saveShortenUrl(shortenUrl: ShortenUrl) {
        jpaShortenUrlRepository.save(shortenUrl)
    }

    override fun findShortenUrlByShortenUrlKey(key: String): ShortenUrl? {
        return jpaShortenUrlRepository.findByShortenUrlKey(key)
    }
}
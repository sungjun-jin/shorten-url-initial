package me.sj.study.shortenurlinitial.application

import me.sj.study.shortenurlinitial.domain.LackOfShortenUrlKeyException
import me.sj.study.shortenurlinitial.domain.NotFoundShortenUrlKeyException
import me.sj.study.shortenurlinitial.domain.ShortenUrl
import me.sj.study.shortenurlinitial.domain.ShortenUrlRepository
import me.sj.study.shortenurlinitial.presentation.ShortenUrlCreateRequestDto
import me.sj.study.shortenurlinitial.presentation.ShortenUrlCreateResponseDto
import me.sj.study.shortenurlinitial.presentation.ShortenUrlInformationDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class SimpleShortenUrlService(
    private val shortenUrlRepository: ShortenUrlRepository
) {
    @Transactional
    fun generateShortenUrl(request: ShortenUrlCreateRequestDto): ShortenUrlCreateResponseDto {
        val shortenUrlKey = this.getUniqueShortenUrlKey()
        val shortenUrl = ShortenUrl(
            originalUrl = request.originalUrl,
            shortenUrlKey = shortenUrlKey
        )

        shortenUrlRepository.saveShortenUrl(shortenUrl)

        return ShortenUrlCreateResponseDto(originalUrl = request.originalUrl, shortenUrlKey = shortenUrlKey)
    }

    @Transactional
    fun getOriginalUrlByShortenUrlKey(shortenUrlkey: String): String {
        val shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlkey)
            ?: throw NotFoundShortenUrlKeyException()

        shortenUrl.increaseRedirectCount()

        return shortenUrl.originalUrl
    }

    @Transactional(readOnly = true)
    fun getShortenUrlInformationByShortenUrlKey(shortenUrlkey: String): ShortenUrlInformationDto {
        val shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlkey)
            ?: throw NotFoundShortenUrlKeyException()

        return ShortenUrlInformationDto(
            originalUrl = shortenUrl.originalUrl,
            shortenUrlKey = shortenUrl.shortenUrlKey,
            redirectCount = shortenUrl.redirectionCount
        )
    }

    private fun getUniqueShortenUrlKey(): String {
        val MAX_RETRY_COUNT = 5
        var count = 0
        while (count++ < MAX_RETRY_COUNT) {
            val shortenUrlKey = ShortenUrl.generateShortenUrlKey()
            shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey) ?: return shortenUrlKey
        }
        throw LackOfShortenUrlKeyException()
    }
}
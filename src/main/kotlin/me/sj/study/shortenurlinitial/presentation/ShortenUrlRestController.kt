package me.sj.study.shortenurlinitial.presentation

import jakarta.validation.Valid
import me.sj.study.shortenurlinitial.application.SimpleShortenUrlService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class ShortenUrlRestController(
    private val shortenUrlService: SimpleShortenUrlService
) {

    @PostMapping("/shortenUrl")
    fun createShortenUrl(
        @Valid @RequestBody
        request: ShortenUrlCreateRequestDto
    ): ResponseEntity<ShortenUrlCreateResponseDto> {
        val response = shortenUrlService.generateShortenUrl(request)

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @GetMapping("/{shortenUrlKey}")
    fun redirectShortenUrlKey(
        @PathVariable
        shortenUrlKey: String
    ): ResponseEntity<Unit> {
        val originalUrl = shortenUrlService.getOriginalUrlByShortenUrlKey(shortenUrlKey)

        val redirectUri = URI(originalUrl)

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION).location(redirectUri).build()
    }

    @GetMapping("/shortenUrl/{shortenUrlKey}")
    fun getShortenUrlInformation(
        @PathVariable
        shortenUrlKey: String
    ): ResponseEntity<ShortenUrlInformationDto> {
        val response = shortenUrlService.getShortenUrlInformationByShortenUrlKey(shortenUrlKey)

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}
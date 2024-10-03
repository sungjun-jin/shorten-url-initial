package me.sj.study.shortenurlinitial.presentation

import me.sj.study.shortenurlinitial.domain.LackOfShortenUrlKeyException
import me.sj.study.shortenurlinitial.domain.NotFoundShortenUrlKeyException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(LackOfShortenUrlKeyException::class)
    fun handleLackOfShortenUrlKeyException(): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("단축 URL 자원이 부족합니다")
    }

    @ExceptionHandler(NotFoundShortenUrlKeyException::class)
    fun handleNotFoundShortenUrlException(): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("단축 URL을 찾지 못했습니다")
    }
}
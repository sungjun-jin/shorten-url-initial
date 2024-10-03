package me.sj.study.shortenurlinitial.presentation

data class ShortenUrlCreateResponseDto(
    val originalUrl: String,
    val shortenUrlKey: String,
)

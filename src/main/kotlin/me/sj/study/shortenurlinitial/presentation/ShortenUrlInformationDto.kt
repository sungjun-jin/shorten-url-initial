package me.sj.study.shortenurlinitial.presentation

data class ShortenUrlInformationDto(
    val originalUrl: String,
    val shortenUrlKey: String,
    val redirectCount: Long
)

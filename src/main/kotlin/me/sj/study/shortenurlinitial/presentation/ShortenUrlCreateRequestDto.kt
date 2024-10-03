package me.sj.study.shortenurlinitial.presentation

import org.hibernate.validator.constraints.URL
import org.jetbrains.annotations.NotNull

data class ShortenUrlCreateRequestDto(
    @NotNull
    @URL(regexp = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")
    val originalUrl: String
)

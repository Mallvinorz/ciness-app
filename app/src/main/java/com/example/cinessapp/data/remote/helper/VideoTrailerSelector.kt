package com.example.cinessapp.data.remote.helper

import com.example.cinessapp.data.remote.dto.VideoTrailerResultsDto

fun List<VideoTrailerResultsDto>.pickBestTrailer(): VideoTrailerResultsDto? {
    return this
        .filter { it.site.equals("Youtube", ignoreCase = true) }
        .filter { it.type.equals("Trailer", ignoreCase = true) }.maxByOrNull { it.official }
        ?: this.firstOrNull { it.site.equals("Youtube", ignoreCase = true) }
}
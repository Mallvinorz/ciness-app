package com.example.cinessapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cinessapp.data.mapper.toDomain
import com.example.cinessapp.data.remote.dto.MovieDto
import com.example.cinessapp.data.remote.dto.MovieListDto
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.cinessapp", appContext.packageName)
    }
}

class MovieMakerTest {
    @Test
    fun useMovieListDtoToDomainMovieList() {
        val dto = MovieListDto(
            page = 1,
            results = listOf(
                MovieDto(
                    id = 1,
                    title = "Avengers",
                    overview = "overview",
                    posterPath = "/poster.jpg",
                    backdropPath = "/backdrop.jpg",
                    genreIds = listOf(28, 12),
                    releaseDate = "2026-01-01",
                    voteAverage = 8.5,
                    voteCount = 1000,
                    popularity = 99.9,
                    adult = false,
                    video = false,
                    originalLanguage = "en",
                    originalTitle = "Avengers"
                )
            ),
            totalPages = 10,
            totalResults = 100
        )

        val result = dto.toDomain()

        assertEquals(1, result.page)
        assertEquals(10, result.totalPages)
        assertEquals(100, result.totalResults)
        assertEquals(1, result.results.size)
        assertEquals("Avengers", result.results.first().title)
    }

    @Test
    fun useMovieListDtoWithEmptyResult() {
        val dto = MovieListDto(
            page = 1,
            results = emptyList(),
            totalPages = 0,
            totalResults = 0
        )

        val result = dto.toDomain()

        assertTrue(result.results.isEmpty())
    }
}
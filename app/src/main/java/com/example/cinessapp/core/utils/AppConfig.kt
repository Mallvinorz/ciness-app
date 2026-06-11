package com.example.cinessapp.core.utils

import com.example.cinessapp.BuildConfig

object AppConfig {
    val BASE_URL: String get() = BuildConfig.API_BASE_URL
    val ACCESS_TOKEN: String get() = BuildConfig.API_READ_ACCESS_TOKEN

    val IMAGE_BASE_URL: String get() = BuildConfig.IMAGE_BASE_URL
}
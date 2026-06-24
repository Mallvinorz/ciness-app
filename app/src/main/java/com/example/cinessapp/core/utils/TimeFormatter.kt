package com.example.cinessapp.core.utils

fun Int.toRuntime(): String {
    val hours = this / 60
    val minutes = this % 60
    return "${hours}h ${minutes}m"
}
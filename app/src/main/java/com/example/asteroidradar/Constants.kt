package com.example.asteroidradar

object Constants {
    const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 8
    const val BASE_URL = "https://api.nasa.gov/"
    const val ASTEROIDS_ENDPOINT = "neo/rest/v1/feed/"
    const val PICTURE_OF_DAY_URL = "/planetary/apod"
    const val API_KEY_URL = "api_key"
    const val START_DATE = "start_date"
    const val END_DATE = "end_date"
}

enum class RequiredAsteroids {
    ALL,
    TODAY,
    WEEK
}
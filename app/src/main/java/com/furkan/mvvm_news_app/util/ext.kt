package com.furkan.mvvm_news_app.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.nDaysBeforeFromDateString(n: Long): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(this, formatter)
    val nDaysBefore = date.minusDays(n)
    return nDaysBefore.format(formatter)
}

fun String.nDaysAfterFromDateString(n: Long): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(this, formatter)
    val nDaysAfter = date.plusDays(n)
    return nDaysAfter.format(formatter)
}

fun String.zeroPadDateAndMonth(): String {
    val parts = this.split("-")
    if (parts.size == 3) {
        val year = parts[0]
        val month = parts[1].padStart(2, '0')
        val day = parts[2].padStart(2, '0')
        return "$year-$month-$day"
    }
    return this
}

fun getCurrentDateTimeString(): String {
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return currentDateTime.format(formatter)
}
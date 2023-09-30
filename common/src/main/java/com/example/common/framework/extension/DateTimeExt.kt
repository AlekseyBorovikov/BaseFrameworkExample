package com.example.common.framework.extension

import timber.log.Timber
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

const val DATE_AND_TIME_TO_MILLIS_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS"
const val SECOND_IN_MILLIS = 1000L
const val MINUTE_IN_MILLIS = 60 * SECOND_IN_MILLIS
const val HOUR_IN_MILLIS = 60 * MINUTE_IN_MILLIS
const val DAY_IN_MILLIS = 24 * HOUR_IN_MILLIS
const val WEEK_IN_MILLIS = 7 * DAY_IN_MILLIS
const val MONTH_IN_MILLIS = 30 * DAY_IN_MILLIS
const val YEAR_IN_MILLIS = 12 * MONTH_IN_MILLIS

/**
 * Creates a string that shows how long ago the specified date was
 *
 * @param time [Long] time in utc
 * @param locale
 * @param suffix the line that will be at the end of the sentence
 * @return formatted [String]
 */
fun Long.toTimeAgo(locale: Locale? = null, suffix: String = "ago"): String {

    val time = Calendar.getInstance(locale).apply { timeInMillis = this@toTimeAgo * 1000 }.timeInMillis
    val now = Calendar.getInstance(locale).timeInMillis

    // convert back to second
    val diff = now - time
    Timber.d("time $time | now $now | diff $diff")

    return when {
        diff < MINUTE_IN_MILLIS -> "Just now"
        diff < 2 * MINUTE_IN_MILLIS -> "a minute $suffix"
        diff < 60 * MINUTE_IN_MILLIS -> "${diff / MINUTE_IN_MILLIS} minutes $suffix"
        diff < 2 * HOUR_IN_MILLIS -> "an hour ago"
        diff < 24 * HOUR_IN_MILLIS -> "${diff / HOUR_IN_MILLIS} hours $suffix"
        diff < 2 * DAY_IN_MILLIS -> "yesterday"
        diff < 30 * DAY_IN_MILLIS -> "${diff / DAY_IN_MILLIS} days $suffix"
        diff < 2 * MONTH_IN_MILLIS -> "a month $suffix"
        diff < 12 * MONTH_IN_MILLIS -> "${diff / MONTH_IN_MILLIS} months $suffix"
        diff < 2 * YEAR_IN_MILLIS -> "a year $suffix"
        else -> "${diff / YEAR_IN_MILLIS} years $suffix"
    }
}

/**
 * Get current time
 * */
fun nowCalendar(locale: Locale? = null) = Calendar.getInstance(locale)

/**
 * Convert [Long] utc time to [Calendar]
 * */
fun Long.utcToCalendar(locale: Locale? = null): Calendar =
    Calendar
        .getInstance(locale)
        .apply {
            timeZone = TimeZone.getTimeZone("UTC")
            timeInMillis = this@utcToCalendar
        }

val Calendar.hour get() = this.get(Calendar.HOUR_OF_DAY)
val Calendar.minute get() = this.get(Calendar.MINUTE)
val Calendar.second get() = this.get(Calendar.SECOND)
val Calendar.year get() = this.get(Calendar.YEAR)
val Calendar.month get() = this.get(Calendar.MONTH)
val Calendar.dayOfMonth get() = this.get(Calendar.DAY_OF_MONTH)
val Calendar.utc get() = this.apply { timeZone = TimeZone.getTimeZone("UTC") }
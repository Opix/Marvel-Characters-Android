package net.opix.marvel.characters.extensions

import java.text.SimpleDateFormat
import java.util.*
import java.text.ParseException

val String.dateFromISO8601WithMilliseconds: Date?
    get() {
        val sdf = SimpleDateFormat(DATE_YEAR_TIME_SERVER_SSS_FORMAT, Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")

        try {
            return sdf.parse(this)
        } catch (ignored: ParseException) {
        }
        return null
    }
package net.opix.marvel.characters.extensions

import java.text.SimpleDateFormat
import java.util.*

const val DATE_YEAR_TIME_SERVER_SSS_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val MMMM_d_YYYY_h_mm_a = "MMMM d, yyyy 'at' h:mm a"

val Date.eventDateFormat: String
    get() {
        val sdf = SimpleDateFormat(MMMM_d_YYYY_h_mm_a, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(this)
    }

val Date.localDateAsString: String
    get() {
        val sdf = SimpleDateFormat(DATE_YEAR_TIME_SERVER_SSS_FORMAT, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(this)
    }
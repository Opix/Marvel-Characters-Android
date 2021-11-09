package net.opix.marvel.characters.extensions

import java.math.BigInteger
import java.security.MessageDigest
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

val String.md5Hash: String
    get() {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(this.toByteArray())).toString(16).padStart(32, '0')
    }

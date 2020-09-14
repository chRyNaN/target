package com.chrynan.target.plugin.util

import java.text.SimpleDateFormat
import java.util.*

private val iso8601Format by lazy {
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
}

fun timestampForNow(): String = iso8601Format.format(Date())

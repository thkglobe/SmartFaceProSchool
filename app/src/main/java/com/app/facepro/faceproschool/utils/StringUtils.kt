package com.app.facepro.faceproschool.utils

import java.lang.System.currentTimeMillis
import java.text.SimpleDateFormat
import java.util.*


fun getDisplayableTime(dataDate: String): String? {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val pasTime = dateFormat.parse(dataDate)
    var difference: Long = 0
    val mDate =currentTimeMillis()
    if (mDate > pasTime.time) {
        difference = mDate - pasTime.time
        val seconds = difference / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val months = days / 31
        val years = days / 365

        return if (seconds < 0) {
            "not yet"
        } else if (seconds < 60) {
            if (seconds == 1L) "one second ago" else "$seconds seconds ago"
        } else if (seconds < 120) {
            "a minute ago"
        } else if (seconds < 2700)
// 45 * 60
        {
            "$minutes minutes ago"
        } else if (seconds < 5400)
// 90 * 60
        {
            "an hour ago"
        } else if (seconds < 86400)
// 24 * 60 * 60
        {
            "$hours hours ago"
        } else if (seconds < 172800)
// 48 * 60 * 60
        {
            "yesterday"
        } else if (seconds < 2592000)
// 30 * 24 * 60 * 60
        {
            "$days days ago"
        } else if (seconds < 31104000)
// 12 * 30 * 24 * 60 * 60
        {

            if (months <= 1) "one month ago" else "$days months ago"
        } else {

            if (years <= 1) "one year ago" else "$years years ago"
        }
    }
    return null
}

fun getDateDAY(): String {
    val cal = Calendar.getInstance()
    val sdf = SimpleDateFormat("dd")
    return sdf.format(cal.time)
}
fun getDateMonth(): String {
    val cal = Calendar.getInstance()
    val sdf = SimpleDateFormat("EEE\nMMM")
    return sdf.format(cal.time)
}

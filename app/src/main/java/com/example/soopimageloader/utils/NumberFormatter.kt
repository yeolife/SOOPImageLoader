package com.example.soopimageloader.utils

import android.icu.text.DecimalFormat

fun formatWithCommas(number: Int): String {
    return DecimalFormat("#,###").format(number)
}
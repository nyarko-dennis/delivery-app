package dev.dna.deliveryapp.utils

import android.content.Context
import android.widget.Toast

object AppUtils {
    internal fun screenHeightAndWidth(context: Context): Triple<Float, Float, Float> {
        val displayMetrics = context.resources.displayMetrics
        val x = displayMetrics.widthPixels / displayMetrics.density
        val y = displayMetrics.heightPixels / displayMetrics.density

        return Triple(
            x,
            y,
            x*y
        )
    }

    internal fun validPhoneNumber(text: String): Boolean {
        val pattern = "^[+]?[0-9]{10,12}$".toRegex()
//        val y = PhoneNumberUtils.isGlobalPhoneNumber(text)
//        val a = android.util.Patterns.PHONE.matcher(text).matches()
        return pattern.matches(text)
    }

    internal fun validUserInput(text: String): Boolean {
        return "[a-zA-Z_0-9]".toRegex().containsMatchIn(text)
    }

    internal fun validEmail(text: String): Boolean {
//        val pattern =
        return "[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,18}".toRegex().matches(text)
    }

    internal fun showToastMsg(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}
package com.example.timezone.helper

import android.app.Activity
import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import com.example.timezone.R

fun Activity.transparentStatusBar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            window.statusBarColor = ContextCompat.getColor(this, R.color.orange)
        } else {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
            window.statusBarColor = ContextCompat.getColor(this, R.color.orange)

        }

}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}
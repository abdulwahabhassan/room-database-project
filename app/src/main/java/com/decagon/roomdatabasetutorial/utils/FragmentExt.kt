package com.decagon.roomdatabasetutorial.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(message: String) {
    Toast.makeText(this.requireActivity(), message, Toast.LENGTH_SHORT).show()
}
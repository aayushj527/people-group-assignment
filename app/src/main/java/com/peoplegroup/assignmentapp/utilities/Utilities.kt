package com.peoplegroup.assignmentapp.utilities

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.peoplegroup.assignmentapp.AppClass

fun showToast(text: String) {
    Handler(Looper.getMainLooper()).post {
        Toast.makeText(
            AppClass.getContext(),
            text,
            Toast.LENGTH_SHORT
        ).show()
    }
}
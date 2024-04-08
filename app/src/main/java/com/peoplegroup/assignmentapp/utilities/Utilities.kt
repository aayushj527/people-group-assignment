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

fun nameBuilder(name: Name): String {
    var nameString = ""

    name.title?.let {
        nameString = nameString.plus(it)
    }

    name.first?.let {
        nameString = nameString.plus(" $it")
    }

    name.last?.let {
        nameString = nameString.plus(" $it")
    }

    return "${name.title} ${name.first} ${name.last}"
}
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

    return nameString
}

fun addressBuilder(location: Location): String {
    var locationString = ""

    location.street?.let {
        locationString = locationString.plus(streetBuilder(it))
    }

    location.city?.let {
        locationString = locationString.plus(" $it")
    }

    location.state?.let {
        locationString = locationString.plus(" $it")
    }

    return locationString
}

fun streetBuilder(street: Street): String {
    var streetString = ""

    street.number?.let {
        streetString = streetString.plus(it)
    }

    street.name?.let {
        streetString = streetString.plus(" $it")
    }

    return streetString
}
package com.peoplegroup.assignmentapp.utilities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.peoplegroup.assignmentapp.AppClass
import com.peoplegroup.assignmentapp.R

const val CONNECTIVITY_INTENT_ACTION = "android.net.conn.CONNECTIVITY_CHANGE"

val networkChangeReceiver: BroadcastReceiver = object : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == CONNECTIVITY_INTENT_ACTION) {
            getCurrentConnectivityState().let {
                AppClass.connectivityState.value = it
                if (it == ConnectionState.Unavailable) {
                    showToast(context.getString(R.string.error_network_disconnected))
                }
            }
        }
    }
}

fun getCurrentConnectivityState(): ConnectionState {
    val connectivityManager =
        AppClass.getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= 23) {
        val network = connectivityManager.activeNetwork ?: return ConnectionState.Unavailable

        val actNetwork = connectivityManager.getNetworkCapabilities(network)
            ?: return ConnectionState.Unavailable

        return when {
            actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                ConnectionState.Available
            }

            else -> {
                ConnectionState.Unavailable
            }
        }
    } else {
        return if (connectivityManager.activeNetworkInfo?.isConnected == true) {
            ConnectionState.Available
        } else {
            ConnectionState.Unavailable
        }
    }
}

sealed class ConnectionState {
    data object Available : ConnectionState()
    data object Unavailable : ConnectionState()
}
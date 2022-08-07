package com.androbrain.qr.scanner.util.navigation

import android.util.Log
import androidx.annotation.IdRes
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections

fun NavController.safeNavigate(navDirections: NavDirections) {
    try {
        navigate(navDirections)
    } catch (e: IllegalArgumentException) {
        Log.e("NavigationError", e.toString())
    }
}

fun <T> NavController.setResult(key: String, value: T) {
    previousBackStackEntry?.savedStateHandle?.set(key, value)
}

fun <T> NavController.getResult(key: String): LiveData<T>? =
    currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)

fun <T> NavController.removeResult(key: String) =
    currentBackStackEntry?.savedStateHandle?.remove<T>(key)

fun NavDestination.matchId(@IdRes destId: Int): Boolean {
    var currentDestination: NavDestination? = this

    while (currentDestination?.id != destId && currentDestination?.parent != null) {
        currentDestination = currentDestination.parent
    }

    return currentDestination?.id == destId
}

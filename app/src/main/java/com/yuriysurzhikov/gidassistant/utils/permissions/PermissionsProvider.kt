package com.yuriysurzhikov.gidassistant.utils.permissions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionsProvider<T: PermissionsType>
constructor(
    val context: Context,
    val callback: IPermissionsCallback<T>,
    private val type: T): Activity(), IPermissionsProvider<T> {

    override fun requestPermissions() {
        if(ContextCompat.checkSelfPermission(context, type.permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            callback.onGranted(type)
        } else {
            ActivityCompat.requestPermissions(this, type.permissions, type.requestCode)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == type.requestCode && grantResults.isNotEmpty()) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callback.onGranted(type)
            } else {
                callback.onDecline(type)
            }
        }
    }

    companion object {
        val BUNDLE_ARGS = "permissions_args"
    }
}
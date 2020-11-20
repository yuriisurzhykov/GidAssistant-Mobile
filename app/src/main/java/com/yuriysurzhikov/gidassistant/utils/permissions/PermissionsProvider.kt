package com.yuriysurzhikov.gidassistant.utils.permissions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionsProvider<T: PermissionsType> constructor(val context: Context): IPermissionsProvider<T> {

    override fun requestPermissions(
        activity: Activity,
        type: PermissionsType,
        callback: IPermissionsCallback<T>
    ) {
        if(ContextCompat.checkSelfPermission(context, type.permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            callback.onGranted(type.requestCode)
        } else {
            ActivityCompat.requestPermissions(activity, arrayOf(type.permissions[0]), type.requestCode)
        }
    }

}
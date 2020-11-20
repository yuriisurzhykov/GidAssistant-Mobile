package com.yuriysurzhikov.gidassistant.utils.permissions

import android.app.Activity

interface IPermissionsProvider<T: PermissionsType> {
    fun requestPermissions(activity: Activity, type: PermissionsType, callback: IPermissionsCallback<T>)
}
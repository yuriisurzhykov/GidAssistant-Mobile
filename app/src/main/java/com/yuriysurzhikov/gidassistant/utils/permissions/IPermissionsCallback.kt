package com.yuriysurzhikov.gidassistant.utils.permissions

interface IPermissionsCallback<T: PermissionsType> {
    fun onGranted(requestCode: Int)
    fun onDecline(requestCode: Int)
}
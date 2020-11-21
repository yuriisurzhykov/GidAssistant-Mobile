package com.yuriysurzhikov.gidassistant.utils.permissions

interface IPermissionsCallback<T: PermissionsType> {
    fun onGranted(type: T)
    fun onDecline(type: T)
}
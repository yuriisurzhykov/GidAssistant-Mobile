package com.yuriysurzhikov.gidassistant.utils.permissions

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import com.yuriysurzhikov.gidassistant.App
import com.yuriysurzhikov.gidassistant.R
import com.yuriysurzhikov.gidassistant.utils.CommonUtils

sealed class PermissionsType(val permissions: Array<String>,
                             val requestCode: Int) {

    object LocationPermissions: PermissionsType(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 100) {
        override fun showGrantedMessage() {
            Toast.makeText(App.instance?.applicationContext, App.instance?.applicationContext?.resources?.getString(
                R.string.location_permissions_granted), Toast.LENGTH_LONG).show()
        }

        override fun showDeclinedMessage() {
            Toast.makeText(App.instance?.applicationContext, App.instance?.applicationContext?.resources?.getString(
                R.string.location_permissions_declined), Toast.LENGTH_LONG).show()
        }
    }

    object WriteStoragePermissions : PermissionsType(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 101) {
        override fun showGrantedMessage() {
            Toast.makeText(App.instance?.applicationContext, App.instance?.applicationContext?.resources?.getString(
                R.string.write_storage_granted), Toast.LENGTH_LONG).show()
        }

        override fun showDeclinedMessage() {
            Toast.makeText(App.instance?.applicationContext, App.instance?.applicationContext?.resources?.getString(
                R.string.write_storage_declined), Toast.LENGTH_LONG).show()
        }
    }

    object ReadStoragePermissions : PermissionsType(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 102) {
        override fun showGrantedMessage() {
            Toast.makeText(App.instance?.applicationContext, App.instance?.applicationContext?.resources?.getString(
                R.string.read_storage_granted), Toast.LENGTH_LONG).show()
        }

        override fun showDeclinedMessage() {
            Toast.makeText(App.instance?.applicationContext, App.instance?.applicationContext?.resources?.getString(
                R.string.read_storage_declined), Toast.LENGTH_LONG).show()
        }
    }

    abstract fun showGrantedMessage()
    abstract fun showDeclinedMessage()
}
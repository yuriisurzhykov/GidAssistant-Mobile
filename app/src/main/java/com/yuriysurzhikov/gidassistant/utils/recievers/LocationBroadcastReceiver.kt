package com.yuriysurzhikov.gidassistant.utils.recievers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class LocationBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.action == ACTION_LOCATION_UPDATE
    }

    companion object {
        const val ACTION_LOCATION_UPDATE = "location_update_receiver_action"
    }
}
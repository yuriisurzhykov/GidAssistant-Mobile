package com.yuriysurzhikov.gidassistant.ui.splash

interface IRunHandler {
    fun isFirstRun(): Boolean
    fun needRequestRegistration(): Boolean
}
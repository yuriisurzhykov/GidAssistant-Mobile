package com.yuriysurzhikov.gidassistant.utils

sealed class DataState<out R> {
    object Loading : DataState<Nothing>()
    class Success<out T>(val data: T) : DataState<T>()
    class Error(val ex: Throwable) : DataState<Nothing>()
}
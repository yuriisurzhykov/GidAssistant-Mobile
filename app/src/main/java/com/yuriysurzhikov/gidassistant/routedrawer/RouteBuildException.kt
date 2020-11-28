package com.yuriysurzhikov.gidassistant.routedrawer

class RouteBuildException(override val message: String, val causeType: Int): Exception(message) {
}
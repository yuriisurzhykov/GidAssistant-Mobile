package com.yuriysurzhikov.gidassistant.customviews.bottomnavigation

sealed class NavigationalType {
    object Best: NavigationalType()
    object Profile: NavigationalType()
    object BestSuitable: NavigationalType()
}
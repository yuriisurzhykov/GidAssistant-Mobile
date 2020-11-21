package com.yuriysurzhikov.gidassistant.ui

import androidx.fragment.app.Fragment

abstract class AbstractFragment: Fragment(), IRefreshableFragment {
    var TAG: String? = null
    var mTitle: String? = null
}
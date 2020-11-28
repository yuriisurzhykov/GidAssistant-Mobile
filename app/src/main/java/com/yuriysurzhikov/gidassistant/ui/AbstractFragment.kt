package com.yuriysurzhikov.gidassistant.ui

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class AbstractFragment: Fragment(), IRefreshableFragment {
    var TAG: String? = null
    var mTitle: String? = null

    open fun putArguments(bundle: Bundle?) {
        arguments = bundle
    }
}
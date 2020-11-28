/*

Copyright 2020 Malik Dawar

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

 */
package com.yuriysurzhikov.gidassistant.routedrawer.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Step (
    var distance: Distance? = null,
    var duration: Duration? = null,

    @SerializedName("start_location")
    @Expose
    var startLocation: GeoPoint? = null,
    @SerializedName("end_location")
    @Expose
    var endLocation: GeoPoint? = null,
    @SerializedName("html_instructions")
    var htmlInstructions: String? = null,
    @SerializedName("travel_mode")
    @Expose
    var travelMode: String? = null
)
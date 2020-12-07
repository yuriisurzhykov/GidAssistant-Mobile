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
package com.yuriysurzhikov.gidassistant.routedrawer.parser

import com.google.gson.GsonBuilder

class RouteJsonParser<T> : RouteParser<T> {
    @Suppress("UNCHECKED_CAST")
    override suspend fun parse(data: String?, type: Class<T>?): T {
        val gson = GsonBuilder().create()
        return gson.fromJson<Any>(data, type) as T
    }
}
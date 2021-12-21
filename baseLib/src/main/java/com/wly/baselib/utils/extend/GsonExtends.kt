@file:JvmName(name = "GsonExtends")
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package com.wly.baselib.utils.extend

import com.google.gson.Gson
import com.google.gson.GsonBuilder

inline fun <reified T> Gson.fromJson(json: String): T = fromJson(json, T::class.java)


@kotlin.internal.InlineOnly
inline fun <T> Gson.typedToJson(json: T): String = toJson(json)

@kotlin.internal.InlineOnly
inline fun Any.toJson(): String = GsonBuilder().create().typedToJson(this)


@kotlin.internal.InlineOnly
inline fun Any.toJson(serializeNulls: Boolean): String =
    GsonBuilder()
        .serializeNulls()
        .create()
        .typedToJson(this)
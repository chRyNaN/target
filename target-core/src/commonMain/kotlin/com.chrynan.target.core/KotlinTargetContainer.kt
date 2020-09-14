@file:Suppress("unused")

package com.chrynan.target.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class KotlinTargetContainer(
    @SerialName(value = "last_update") val lastUpdated: String,
    @SerialName(value = "targets") val targets: List<KotlinTarget>
) {

    companion object {

        fun fromJsonString(input: String, json: Json = Json): KotlinTargetContainer =
            json.decodeFromString(serializer(), input)
    }

    fun toJsonString(json: Json = Json): String = json.encodeToString(serializer(), this)
}

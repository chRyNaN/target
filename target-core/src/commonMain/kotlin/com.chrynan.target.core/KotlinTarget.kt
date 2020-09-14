@file:Suppress("unused")

package com.chrynan.target.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class KotlinTarget(
    @SerialName(value = "target_name") val targetName: String,
    @SerialName(value = "name") val name: String,
    @SerialName(value = "platform_type") val platformType: String,
    @SerialName(value = "components") val components: List<String>,
    @SerialName(value = "publishable") val publishable: Boolean,
    @SerialName(value = "artifacts_task_name") val artifactsTaskName: String,
    @SerialName(value = "default_configuration_name") val defaultConfigurationName: String,
    @SerialName(value = "api_elements_configuration_name") val apiElementsConfigurationName: String,
    @SerialName(value = "runtime_elements_configuration_name") val runtimeElementsConfigurationName: String
) {

    companion object {

        fun fromJsonString(input: String, json: Json = Json): KotlinTarget = json.decodeFromString(serializer(), input)
    }

    fun toJsonString(json: Json = Json): String = json.encodeToString(serializer(), this)
}

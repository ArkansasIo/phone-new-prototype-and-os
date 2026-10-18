package io.arkansas.universalphone

import kotlinx.serialization.Serializable

@Serializable
data class SystemSettings(
    val theme: String = "system",
    val networkMode: String = "auto",
    val performanceMode: String = "balanced",
    val securityLevel: String = "standard",
    val developerMode: Boolean = false,
    val usbDebugging: Boolean = false,
    val telemetryEnabled: Boolean = false,
    val automaticUpdates: Boolean = true
)

class SettingsStore(private var state: SystemSettings = SystemSettings()) {
    fun read(): SystemSettings = state
    fun update(transform: (SystemSettings) -> SystemSettings): SystemSettings {
        state = transform(state)
        return state
    }
}

class UniversalPhoneRuntime(
    val config: PhoneConfig = PhoneConfig(),
    val settings: SettingsStore = SettingsStore()
) {
    fun boot(): BootState = BootState(
        osName = config.osName,
        version = config.osVersion,
        secureBoot = config.secureBoot,
        developerMode = settings.read().developerMode
    )
}

data class BootState(
    val osName: String,
    val version: String,
    val secureBoot: Boolean,
    val developerMode: Boolean
)

package io.arkansas.universalphone

enum class Theme { LIGHT, DARK, SYSTEM }
enum class NetworkMode { AUTO, WIFI_ONLY, CELLULAR_ONLY, AIRPLANE }
enum class PerformanceMode { POWER_SAVER, BALANCED, PERFORMANCE, CUSTOM }
enum class SecurityLevel { STANDARD, HIGH, LOCKED_DOWN }

data class OsSettings(
    val theme: Theme = Theme.SYSTEM,
    val networkMode: NetworkMode = NetworkMode.AUTO,
    val performanceMode: PerformanceMode = PerformanceMode.BALANCED,
    val securityLevel: SecurityLevel = SecurityLevel.HIGH,
    val animationsEnabled: Boolean = true,
    val backgroundActivity: Boolean = true,
    val automaticUpdates: Boolean = true,
    val telemetryEnabled: Boolean = false,
    val developerMode: Boolean = false,
    val usbDebugging: Boolean = false,
    val installUnknownApps: Boolean = false
)

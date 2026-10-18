package io.arkansas.universalphone

data class PhoneConfig(
    val modelName: String = "UniversalPhone Prototype",
    val osName: String = "UniversalPhoneOS",
    val osVersion: String = "0.1.0",
    val apiLevel: Int = 1,
    val maxRamGiB: Int = 256,
    val storageGiB: Int = 1024,
    val displayWidth: Int = 1080,
    val displayHeight: Int = 2400,
    val refreshRateHz: Int = 120,
    val secureBoot: Boolean = true,
    val developerMode: Boolean = false
)

data class FeatureOptions(
    val camera: Boolean = true,
    val cellular: Boolean = true,
    val wifi: Boolean = true,
    val bluetooth: Boolean = true,
    val nfc: Boolean = true,
    val gnss: Boolean = true,
    val biometrics: Boolean = true,
    val desktopMode: Boolean = false,
    val experimentalHardware: Boolean = false
)

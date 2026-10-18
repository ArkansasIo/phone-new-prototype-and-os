package io.arkansas.universalphone

enum class Capability {
    CAMERA, MICROPHONE, LOCATION, CONTACTS, STORAGE, BLUETOOTH, NFC,
    PHONE, SMS, NOTIFICATIONS, BIOMETRICS, NETWORK, SENSORS
}

class PermissionManager {
    private val grants = mutableMapOf<String, MutableSet<Capability>>()

    fun grant(packageName: String, capability: Capability) {
        grants.getOrPut(packageName) { mutableSetOf() }.add(capability)
    }

    fun revoke(packageName: String, capability: Capability) {
        grants[packageName]?.remove(capability)
    }

    fun isAllowed(packageName: String, capability: Capability): Boolean =
        grants[packageName]?.contains(capability) == true
}

package io.arkansas.universalphone

class UniversalPhone(
    private val config: PhoneConfig,
    private val display: PhoneDisplay,
    private val storage: PhoneStorage,
    private val network: PhoneNetwork
) {
    var state: PhoneState = PhoneState.OFFLINE
        private set

    fun boot(): Boolean {
        if (state != PhoneState.OFFLINE) return false
        if (!display.initialize()) return false
        if (!storage.initialize()) {
            display.shutdown()
            return false
        }
        if (!network.initialize()) {
            storage.shutdown()
            display.shutdown()
            return false
        }
        state = PhoneState.RUNNING
        return true
    }

    fun shutdown() {
        network.shutdown()
        storage.shutdown()
        display.shutdown()
        state = PhoneState.OFFLINE
    }

    fun configuration(): PhoneConfig = config
}

enum class PhoneState { OFFLINE, BOOTING, RUNNING, RECOVERY, SHUTTING_DOWN }

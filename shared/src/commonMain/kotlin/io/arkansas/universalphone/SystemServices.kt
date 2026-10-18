package io.arkansas.universalphone

class SettingsService(initial: OsSettings = OsSettings()) {
    var settings: OsSettings = initial
        private set

    fun update(transform: (OsSettings) -> OsSettings) {
        settings = transform(settings)
    }
}

class NotificationService {
    data class Notification(val packageName: String, val title: String, val body: String)
    private val queue = mutableListOf<Notification>()

    fun post(notification: Notification): Boolean {
        if (notification.packageName.isBlank() || notification.title.isBlank()) return false
        queue += notification
        return true
    }

    fun pending(): List<Notification> = queue.toList()
    fun clear() = queue.clear()
}

class PowerService {
    private var lowPowerMode = false
    fun setLowPowerMode(enabled: Boolean) { lowPowerMode = enabled }
    fun isLowPowerMode(): Boolean = lowPowerMode
}

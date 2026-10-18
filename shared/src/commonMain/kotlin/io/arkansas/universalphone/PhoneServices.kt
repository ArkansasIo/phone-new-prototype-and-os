package io.arkansas.universalphone

interface PhoneDisplay {
    val width: Int
    val height: Int
    val refreshRateHz: Int
    fun initialize(): Boolean
    fun present(frame: ByteArray)
    fun shutdown()
}

interface PhoneCamera {
    suspend fun open(cameraId: Int): Boolean
    suspend fun capture(): ByteArray
    fun close()
}

interface PhoneStorage {
    fun initialize(): Boolean
    fun read(path: String): ByteArray?
    fun write(path: String, data: ByteArray): Boolean
    fun delete(path: String): Boolean
    fun shutdown()
}

interface PhoneNetwork {
    fun initialize(): Boolean
    fun isOnline(): Boolean
    fun shutdown()
}

interface PhoneSensor {
    fun read(): Map<String, Double>
}

interface PhonePower {
    fun batteryPercent(): Int
    fun isCharging(): Boolean
    fun requestLowPowerMode(enabled: Boolean): Boolean
}

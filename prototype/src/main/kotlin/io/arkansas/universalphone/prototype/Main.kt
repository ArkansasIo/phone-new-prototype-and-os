package io.arkansas.universalphone.prototype

import io.arkansas.universalphone.*

private class SimDisplay : PhoneDisplay {
    override val width = 1080
    override val height = 2400
    override val refreshRateHz = 120
    override fun initialize() = true
    override fun present(frame: ByteArray) = Unit
    override fun shutdown() = Unit
}

private class SimStorage : PhoneStorage {
    private val files = mutableMapOf<String, ByteArray>()
    override fun initialize() = true
    override fun read(path: String) = files[path]
    override fun write(path: String, data: ByteArray): Boolean { files[path] = data; return true }
    override fun delete(path: String) = files.remove(path) != null
    override fun shutdown() = Unit
}

private class SimNetwork : PhoneNetwork {
    private var online = false
    override fun initialize(): Boolean { online = true; return true }
    override fun isOnline() = online
    override fun shutdown() { online = false }
}

fun main() {
    val phone = UniversalPhone(
        PhoneConfig(),
        SimDisplay(),
        SimStorage(),
        SimNetwork()
    )
    check(phone.boot())
    println("${phone.configuration().osName} ${phone.configuration().osVersion} booted")
    phone.shutdown()
}

package io.arkansas.universalphone

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

private class TestDisplay : PhoneDisplay {
    override val width = 1080
    override val height = 2400
    override val refreshRateHz = 120
    override fun initialize() = true
    override fun present(frame: ByteArray) = Unit
    override fun shutdown() = Unit
}

private class TestStorage : PhoneStorage {
    private val files = mutableMapOf<String, ByteArray>()
    override fun initialize() = true
    override fun read(path: String) = files[path]
    override fun write(path: String, data: ByteArray): Boolean { files[path] = data; return true }
    override fun delete(path: String) = files.remove(path) != null
    override fun shutdown() = Unit
}

private class TestNetwork : PhoneNetwork {
    override fun initialize() = true
    override fun isOnline() = true
    override fun shutdown() = Unit
}

class UniversalPhoneTest {
    @Test
    fun bootAndShutdown() {
        val phone = UniversalPhone(PhoneConfig(), TestDisplay(), TestStorage(), TestNetwork())
        assertEquals(PhoneState.OFFLINE, phone.state)
        assertTrue(phone.boot())
        assertEquals(PhoneState.RUNNING, phone.state)
        phone.shutdown()
        assertEquals(PhoneState.OFFLINE, phone.state)
    }

    @Test
    fun permissionsWork() {
        val manager = PermissionManager()
        manager.grant("demo.app", Capability.CAMERA)
        assertTrue(manager.isAllowed("demo.app", Capability.CAMERA))
        manager.revoke("demo.app", Capability.CAMERA)
        assertFalse(manager.isAllowed("demo.app", Capability.CAMERA))
    }
}

package io.arkansas.universalphone.network

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SyntheticNetworkTest {
    @Test
    fun defaultFabricContainsInternetPath() {
        val fabric = SyntheticInternetFabric().buildDefault()
        assertTrue(fabric.engine.reachable("phone", "core"))
        assertTrue(fabric.engine.reachable("phone", "dns"))
    }

    @Test
    fun narrowLinkHasExpectedConstraints() {
        val fabric = SyntheticInternetFabric().buildDefault()
        val link = fabric.engine.link("access-narrow")
        requireNotNull(link)
        assertEquals(10L, link.bandwidthMbps)
        assertEquals(40L, link.latencyMs)
        assertEquals(1200, link.mtu)
        assertFalse(link.packetLossPercent < 0.0)
    }
}

package io.arkansas.universalphone.network

import kotlinx.serialization.Serializable

@Serializable
data class NetworkHudState(
    val connected: Boolean = false,
    val activeInterface: NetworkInterfaceInfo? = null,
    val downloadMbps: Double = 0.0,
    val uploadMbps: Double = 0.0,
    val latencyMs: Long? = null,
    val signalPercent: Int? = null,
    val nodeCount: Int = 0,
    val onlineNodeCount: Int = 0,
    val vpnActive: Boolean = false,
    val internetAvailable: Boolean = false
)

class NetworkHudController {
    var state: NetworkHudState = NetworkHudState()
        private set

    fun update(snapshot: NetworkSnapshot) {
        val online = snapshot.nodes.count { it.online }
        state = state.copy(
            connected = online > 0,
            nodeCount = snapshot.nodes.size,
            onlineNodeCount = online,
            internetAvailable = snapshot.nodes.any { it.type != NetworkType.LOOPBACK && it.online },
            latencyMs = snapshot.nodes.mapNotNull { it.latencyMs }.minOrNull()
        )
    }
}

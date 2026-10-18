package io.arkansas.universalphone.network

sealed interface NetworkEvent {
    data class InterfaceConnected(val interfaceId: String) : NetworkEvent
    data class InterfaceDisconnected(val interfaceId: String) : NetworkEvent
    data class NodeOnline(val nodeId: String) : NetworkEvent
    data class NodeOffline(val nodeId: String) : NetworkEvent
    data class SnapshotUpdated(val snapshot: NetworkSnapshot) : NetworkEvent
    data class Error(val message: String, val cause: Throwable? = null) : NetworkEvent
}

class NetworkEventBus {
    private val listeners = mutableListOf<(NetworkEvent) -> Unit>()
    fun subscribe(listener: (NetworkEvent) -> Unit) { listeners += listener }
    fun unsubscribe(listener: (NetworkEvent) -> Unit) { listeners -= listener }
    fun publish(event: NetworkEvent) { listeners.toList().forEach { it(event) } }
}

package io.arkansas.universalphone.network

interface NetworkAdapter {
    suspend fun interfaces(): List<NetworkInterfaceInfo>
    suspend fun snapshot(): NetworkSnapshot
    suspend fun connect(interfaceId: String): Result<Unit>
    suspend fun disconnect(interfaceId: String): Result<Unit>
}

class NetworkManager(private val adapter: NetworkAdapter) {
    private val observers = mutableListOf<(NetworkSnapshot) -> Unit>()

    suspend fun snapshot(): NetworkSnapshot = adapter.snapshot().also { snapshot ->
        observers.toList().forEach { it(snapshot) }
    }

    suspend fun interfaces(): List<NetworkInterfaceInfo> = adapter.interfaces()

    suspend fun connect(interfaceId: String): Result<Unit> = adapter.connect(interfaceId)
    suspend fun disconnect(interfaceId: String): Result<Unit> = adapter.disconnect(interfaceId)

    fun observe(observer: (NetworkSnapshot) -> Unit) {
        observers += observer
    }

    fun removeObserver(observer: (NetworkSnapshot) -> Unit) {
        observers -= observer
    }
}

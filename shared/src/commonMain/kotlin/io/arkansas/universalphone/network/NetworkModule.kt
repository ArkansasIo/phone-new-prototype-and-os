package io.arkansas.universalphone.network

/** Dependency container for the universal network subsystem. */
class NetworkModule(adapter: NetworkAdapter) {
    val nodeTree = NetworkNodeTree()
    val manager = NetworkManager(adapter)
    val hud = NetworkHudController()

    init {
        manager.observe { snapshot ->
            nodeTree.clear()
            snapshot.nodes.forEach { nodeTree.upsert(it) }
            hud.update(snapshot)
        }
    }
}

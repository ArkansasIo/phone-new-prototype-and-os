package io.arkansas.universalphone.virtualnet

import kotlin.time.Clock

class VirtualNetworkManager {
    private val networks = linkedMapOf<String, VirtualNetwork>()
    private val nics = linkedMapOf<String, VirtualNic>()
    private val nodes = linkedMapOf<String, VirtualNode>()
    private val routes = mutableListOf<VirtualRoute>()

    fun createNetwork(network: VirtualNetwork): Result<VirtualNetwork> {
        if (networks.containsKey(network.id)) return Result.failure(IllegalArgumentException("Network already exists: ${network.id}"))
        networks[network.id] = network
        return Result.success(network)
    }

    fun deleteNetwork(id: String): Result<Unit> {
        if (nics.values.any { it.networkId == id }) return Result.failure(IllegalStateException("Network has attached virtual NICs"))
        networks.remove(id) ?: return Result.failure(NoSuchElementException(id))
        routes.removeAll { it.networkId == id }
        nodes.values.filter { it.networkId == id }.forEach { nodes.remove(it.id) }
        return Result.success(Unit)
    }

    fun getNetwork(id: String): VirtualNetwork? = networks[id]
    fun listNetworks(): List<VirtualNetwork> = networks.values.toList()

    fun attachNic(nic: VirtualNic): Result<Unit> {
        if (!networks.containsKey(nic.networkId)) return Result.failure(IllegalArgumentException("Unknown network: ${nic.networkId}"))
        if (nics.containsKey(nic.id)) return Result.failure(IllegalArgumentException("NIC already exists: ${nic.id}"))
        nics[nic.id] = nic
        return Result.success(Unit)
    }

    fun detachNic(id: String): VirtualNic? = nics.remove(id)
    fun listNics(networkId: String? = null): List<VirtualNic> = nics.values.filter { networkId == null || it.networkId == networkId }

    fun addNode(node: VirtualNode): Result<Unit> {
        if (!networks.containsKey(node.networkId)) return Result.failure(IllegalArgumentException("Unknown network: ${node.networkId}"))
        if (nodes.containsKey(node.id)) return Result.failure(IllegalArgumentException("Node already exists: ${node.id}"))
        nodes[node.id] = node
        return Result.success(Unit)
    }

    fun addRoute(route: VirtualRoute): Result<Unit> {
        if (!networks.containsKey(route.networkId)) return Result.failure(IllegalArgumentException("Unknown network: ${route.networkId}"))
        routes += route
        return Result.success(Unit)
    }

    fun snapshot(): VirtualNetworkSnapshot = VirtualNetworkSnapshot(
        timestampEpochMs = Clock.System.now().toEpochMilliseconds(),
        networks = networks.values.toList(),
        nics = nics.values.toList(),
        nodes = nodes.values.toList(),
        routes = routes.toList()
    )
}

package io.arkansas.universalphone.network

/** Synthetic, in-process network fabric used by the phone prototype sandbox. */
enum class SyntheticLayer { LINK, INTERNET, TRANSPORT, APPLICATION }

enum class SyntheticNodeRole {
    DEVICE,
    ACCESS_POINT,
    NARROW_LINK_ENDPOINT,
    REGIONAL_HUB,
    INTERNET_CORE_HUB,
    DNS_HUB,
    NAT_GATEWAY,
    SERVICE_NODE
}

data class SyntheticNetworkNode(
    val id: String,
    val name: String,
    val role: SyntheticNodeRole,
    val layer: SyntheticLayer,
    val ipv4: String,
    val online: Boolean = true,
    val parentId: String? = null
)

data class SyntheticNetworkLink(
    val id: String,
    val from: String,
    val to: String,
    val layer: SyntheticLayer = SyntheticLayer.LINK,
    val bandwidthMbps: Long,
    val latencyMs: Long,
    val packetLossPercent: Double = 0.0,
    val mtu: Int = 1500,
    val active: Boolean = true
)

class SyntheticNetworkEngine {
    private val nodes = linkedMapOf<String, SyntheticNetworkNode>()
    private val links = linkedMapOf<String, SyntheticNetworkLink>()

    fun addNode(node: SyntheticNetworkNode) {
        require(node.id !in nodes) { "Synthetic node already exists: ${node.id}" }
        nodes[node.id] = node
    }

    fun addLink(link: SyntheticNetworkLink) {
        require(link.from in nodes) { "Unknown source node: ${link.from}" }
        require(link.to in nodes) { "Unknown destination node: ${link.to}" }
        require(link.bandwidthMbps > 0) { "Bandwidth must be positive" }
        require(link.latencyMs >= 0) { "Latency cannot be negative" }
        require(link.packetLossPercent in 0.0..100.0) { "Packet loss must be 0..100" }
        require(link.mtu >= 576) { "MTU must be at least 576" }
        links[link.id] = link
    }

    fun node(id: String): SyntheticNetworkNode? = nodes[id]
    fun link(id: String): SyntheticNetworkLink? = links[id]
    fun nodes(): List<SyntheticNetworkNode> = nodes.values.toList()
    fun links(): List<SyntheticNetworkLink> = links.values.toList()

    fun neighbors(id: String): List<SyntheticNetworkNode> =
        links.values.filter { it.active }.flatMap { link ->
            when (id) {
                link.from -> listOf(link.to)
                link.to -> listOf(link.from)
                else -> emptyList()
            }
        }.mapNotNull(nodes::get)

    fun reachable(from: String, destination: String): Boolean {
        if (from == destination) return true
        val visited = mutableSetOf<String>()
        val queue = ArrayDeque<String>()
        queue.addLast(from)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (!visited.add(current)) continue
            if (current == destination) return true
            neighbors(current).forEach { next ->
                if (next.id !in visited) queue.addLast(next.id)
            }
        }
        return false
    }

    fun clear() {
        nodes.clear()
        links.clear()
    }
}

class SyntheticInternetFabric {
    val engine = SyntheticNetworkEngine()

    fun buildDefault(): SyntheticInternetFabric {
        engine.addNode(SyntheticNetworkNode("phone", "Universal Phone", SyntheticNodeRole.DEVICE, SyntheticLayer.LINK, "10.10.1.10"))
        engine.addNode(SyntheticNetworkNode("access", "Virtual Access Gateway", SyntheticNodeRole.ACCESS_POINT, SyntheticLayer.LINK, "10.10.1.1"))
        engine.addNode(SyntheticNetworkNode("narrow-endpoint", "Synthetic Narrow Link", SyntheticNodeRole.NARROW_LINK_ENDPOINT, SyntheticLayer.LINK, "10.10.254.1"))
        engine.addNode(SyntheticNetworkNode("regional", "Regional Internet Hub", SyntheticNodeRole.REGIONAL_HUB, SyntheticLayer.INTERNET, "10.10.0.1"))
        engine.addNode(SyntheticNetworkNode("core", "Synthetic Internet Core", SyntheticNodeRole.INTERNET_CORE_HUB, SyntheticLayer.INTERNET, "10.255.0.1"))
        engine.addNode(SyntheticNetworkNode("dns", "Synthetic DNS Hub", SyntheticNodeRole.DNS_HUB, SyntheticLayer.APPLICATION, "10.255.0.53"))
        engine.addNode(SyntheticNetworkNode("nat", "Synthetic NAT Gateway", SyntheticNodeRole.NAT_GATEWAY, SyntheticLayer.INTERNET, "10.255.0.254"))

        engine.addLink(SyntheticNetworkLink("phone-access", "phone", "access", bandwidthMbps = 1000, latencyMs = 2))
        engine.addLink(SyntheticNetworkLink("access-narrow", "access", "narrow-endpoint", bandwidthMbps = 10, latencyMs = 40, packetLossPercent = 0.5, mtu = 1200))
        engine.addLink(SyntheticNetworkLink("narrow-regional", "narrow-endpoint", "regional", bandwidthMbps = 10, latencyMs = 40, packetLossPercent = 0.5, mtu = 1200))
        engine.addLink(SyntheticNetworkLink("regional-core", "regional", "core", SyntheticLayer.INTERNET, 10_000, 12))
        engine.addLink(SyntheticNetworkLink("core-dns", "core", "dns", SyntheticLayer.APPLICATION, 1_000, 5))
        engine.addLink(SyntheticNetworkLink("core-nat", "core", "nat", SyntheticLayer.INTERNET, 10_000, 3))
        return this
    }
}

package io.arkansas.universalphone.virtualnet

/** Builds isolated multi-network development environments for the OS simulator. */
class VirtualNetworkSandbox {
    val manager = VirtualNetworkManager()

    fun createDefaultLab(): VirtualNetworkSandbox {
        manager.createNetwork(VirtualNetwork("sandbox", "Sandbox LAN", VirtualNetworkType.SANDBOX, "10.10.0.0/24", "10.10.0.1"))
        manager.createNetwork(VirtualNetwork("nat", "NAT Network", VirtualNetworkType.NAT, "10.20.0.0/24", "10.20.0.1", isolated = false))
        manager.createNetwork(VirtualNetwork("host", "Host Only", VirtualNetworkType.HOST_ONLY, "10.30.0.0/24", "10.30.0.1"))
        manager.createNetwork(VirtualNetwork("overlay", "Overlay Mesh", VirtualNetworkType.OVERLAY, "10.40.0.0/24", "10.40.0.1"))

        manager.attachNic(VirtualNic("nic-sandbox", "sandbox", "vnet0", "02:00:00:00:00:01", "10.10.0.10", true))
        manager.attachNic(VirtualNic("nic-nat", "nat", "vnet1", "02:00:00:00:00:02", "10.20.0.10", true))
        manager.attachNic(VirtualNic("nic-host", "host", "vnet2", "02:00:00:00:00:03", "10.30.0.10", true))
        manager.attachNic(VirtualNic("nic-overlay", "overlay", "vnet3", "02:00:00:00:00:04", "10.40.0.10", true))

        manager.addNode(VirtualNode("phone", "Virtual Phone", "sandbox", listOf("nic-sandbox"), true))
        manager.addNode(VirtualNode("router", "Virtual Router", "nat", listOf("nic-nat"), true))
        manager.addNode(VirtualNode("host", "Virtual Host", "host", listOf("nic-host"), true))
        manager.addNode(VirtualNode("mesh", "Overlay Peer", "overlay", listOf("nic-overlay"), true))
        return this
    }

    fun snapshot(): VirtualNetworkSnapshot = manager.snapshot()
}

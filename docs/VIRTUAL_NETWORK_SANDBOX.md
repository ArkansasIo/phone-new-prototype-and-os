# UniversalPhoneOS Virtual Multi-Network Sandbox

The virtual network sandbox provides an OS-level simulation layer for development, testing, networking UI, and future emulator support without requiring direct changes to the physical Android network configuration.

## Architecture

```text
VirtualNetworkSandbox
        |
        v
VirtualNetworkManager
   |       |       |
   v       v       v
Networks  vNICs   Nodes
   |
   v
Routes / Graph
   |
   v
VirtualNetworkSnapshot
```

## Supported virtual network types

- `SANDBOX` — isolated development LAN
- `NAT` — simulated outbound network
- `BRIDGE` — future physical-to-virtual bridge
- `HOST_ONLY` — host/device-only network
- `OVERLAY` — virtual multi-node mesh
- `VPN` — virtual tunnel network
- `LOOPBACK` — internal-only network

## Default laboratory topology

```text
                    +-------------------+
                    | Virtual Phone     |
                    | 10.10.0.10        |
                    +---------+---------+
                              |
                       Sandbox LAN
                       10.10.0.0/24

+------------------+     +------------------+
| Virtual Router   |-----| NAT Network     |
| 10.20.0.1        |     | 10.20.0.0/24    |
+------------------+     +------------------+

+------------------+     +------------------+
| Virtual Host     |     | Overlay Peer     |
| 10.30.0.10       |     | 10.40.0.10       |
+------------------+     +------------------+
```

## Files

```text
shared/src/commonMain/kotlin/io/arkansas/universalphone/virtualnet/
├── VirtualNetworkModels.kt
├── VirtualNetworkManager.kt
├── VirtualNetworkSandbox.kt
└── VirtualNetworkGraph.kt
```

## Safety boundary

This implementation is an in-process simulation model. It does not create privileged Android interfaces, alter host routes, capture traffic, bypass Android networking controls, or expose a real network tunnel. Platform adapters can later map these models to approved OS virtualization APIs.

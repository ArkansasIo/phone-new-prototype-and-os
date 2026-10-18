# Network HUD and Node Tree

The UniversalPhoneOS network subsystem is split into portable Kotlin models and platform adapters.

## Structure

```text
NetworkModule
├── NetworkManager
│   └── NetworkAdapter
├── NetworkNodeTree
├── NetworkGraph
├── NetworkEventBus
├── NetworkHudController
└── NetworkRegistry / RingBuffer

Android
└── AndroidNetworkAdapter
    └── NetworkHud (Compose)
```

## Node hierarchy

```text
Phone
├── Wi-Fi
│   ├── Router
│   └── LAN devices
├── Cellular
│   └── Carrier network
├── VPN
└── Internet
```

The tree is an application/system visualization model. It does not bypass Android networking permissions or modem firmware.

## Data flow

```text
Platform APIs
   -> NetworkAdapter
   -> NetworkManager
   -> NetworkSnapshot
   -> NetworkNodeTree / NetworkGraph
   -> NetworkHudController
   -> Compose NetworkHud
```

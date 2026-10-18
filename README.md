# UniversalPhoneOS — Phone New Prototype and OS

A vendor-neutral smartphone hardware, firmware and operating-system research platform with **Kotlin Multiplatform as the universal software/framework layer**.

## Primary architecture

```text
Kotlin Applications
        ↓
Kotlin Universal Framework
        ↓
Kotlin System Services / Permissions / Package Manager
        ↓
Android adapter | iOS-class adapter | JVM prototype
        ↓
Hardware Abstraction Layer
        ↓
Native kernel / drivers / firmware / vendor BSP
        ↓
SoC / LPDDR / UFS / display / camera / modem / sensors / power
```

## Repository layers

- `shared/` — Kotlin Multiplatform universal APIs, runtime contracts, settings, permissions and tests
- `androidApp/` — Android prototype application
- `prototype/` — JVM hardware/software simulation
- `kotlin/` — Kotlin architecture and future universal modules
- `prototypes/` — reusable phone prototype templates
- `native/` — kernel, driver, HAL and firmware boundary
- `firmware/` — boot and trusted-firmware responsibilities
- `kernel/` — kernel architecture and interfaces
- `drivers/` — hardware-driver boundaries
- `hal/` — hardware abstraction layer
- `os/` — OS/framework implementations and compatibility layers
- `hardware/` — phone component architecture
- `config/` — machine-readable product configuration
- `docs/` — architecture, security, validation and prototype documentation

## Universal settings

The Kotlin API models system options including theme, network mode, performance mode, security level, developer mode, USB debugging, update policy, telemetry and feature flags.

## Hardware model

The architecture supports configurable SoC, CPU/GPU/NPU/ISP, LPDDR, UFS, display, touch, camera, RF, audio, sensors, PMIC, battery and USB-C components. The software model uses a **256 GiB RAM ceiling** for research. LPDDR6–LPDDR9 are treated as experimental/future profiles rather than claims of published standards.

## Platform boundaries

Android integration is intended for AOSP plus the appropriate vendor BSP. The iOS-class layer is an independent architecture and does not contain Apple source, private APIs, signing material or proprietary firmware.

## Build

The original C++20 reference model remains available. The new Kotlin project uses Gradle/Kotlin Multiplatform:

```sh
./gradlew :shared:test :prototype:test :androidApp:assembleDebug
```

A Gradle wrapper is not committed yet; use a compatible Gradle installation or generate the wrapper locally.

## Prototype workflow

1. Copy `docs/PROTOTYPE_TEMPLATE.md`.
2. Define hardware in `config/universal-phone.yaml` or a product-specific configuration.
3. Implement platform adapters behind the common Kotlin interfaces.
4. Add native HAL/driver bindings for real hardware.
5. Run unit, integration, boot, power, thermal, security and hardware validation.
6. Only label the device production-ready after vendor BSP, certification, signing and physical validation are complete.

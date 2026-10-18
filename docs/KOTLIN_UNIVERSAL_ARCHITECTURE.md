# Kotlin Universal Architecture

## Goal

Use Kotlin Multiplatform as the portable application and OS-framework layer for the UniversalPhone prototype.

## Layers

1. Kotlin application APIs
2. Kotlin system framework
3. Kotlin service and permission layer
4. Platform adapters
5. Hardware abstraction layer
6. Native kernel, drivers, firmware and vendor BSP
7. Physical phone hardware

## Platform targets

- Android: integrate the universal layer with Android/AOSP platform services.
- iOS-class: independent API/runtime design; no Apple source, private APIs, signing material or proprietary firmware.
- JVM prototype: deterministic desktop simulation of hardware interfaces.
- Native HAL: bridge to actual ARM64 device drivers and vendor BSP components.

## Design rules

- Keep common business logic in `commonMain`.
- Put platform APIs in platform source sets.
- Keep hardware-dependent code behind interfaces.
- Never place credentials or signing keys in source control.
- Treat experimental memory generations as simulation profiles until hardware and standards are verified.

# Phone New Prototype and OS

A vendor-neutral smartphone hardware and operating-system research scaffold.

## Scope

This repository documents a complete phone platform architecture: SoC, CPU/GPU/NPU, LPDDR memory, UFS storage, display/touch, cameras, cellular RF, Wi-Fi/Bluetooth, GNSS, NFC, USB-C, sensors, audio, battery/PMIC, thermal design, security, boot, drivers, HALs, system services, and sample applications.

**Important:** this is an architecture reference and software model, not a buildable phone firmware image. A real device needs vendor-specific SoC support packages, signed boot firmware, kernel/driver support, RF certification, schematics, and validated physical components. Apple's iOS is proprietary; `ios_class` describes an original iOS-class design, not Apple's source code. Android integration should use AOSP and the relevant vendor BSP.

## Memory target

The companion reference model defines a software capacity ceiling of 256 GiB. DDR6–DDR9/LPDDR6–LPDDR9 entries are experimental placeholders, not published JEDEC specifications. Modern mobile devices use LPDDR-family memory, not desktop DIMMs.

## Initial structure

- `docs/` — architecture, hardware, software, security, power, and development plans
- `hardware/` — component and interface inventory
- `firmware/` — boot-chain and firmware responsibilities
- `kernel/` and `drivers/` — kernel/driver boundaries
- `hal/` — hardware abstraction contracts
- `os/android/` and `os/ios_class/` — high-level OS architecture
- `apps/` — system-app placeholders and contracts
- `config/` — JSON reference configurations
- `src/` and `tests/` — portable C++ reference model and tests
- `scripts/` — build/test helpers

## Build the reference model

Requirements: CMake 3.20+ and a C++20 compiler.

```sh
cmake -S . -B build
cmake --build build
ctest --test-dir build --output-on-failure
```

This build validates the reference model only; it does not produce Android or iOS firmware.

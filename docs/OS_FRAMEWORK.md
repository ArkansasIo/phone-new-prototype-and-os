# PhoneOS Framework

## Architecture

```text
+------------------------------------------------------+
| Applications                                         |
+-----------------------+------------------------------+
| PhoneOS Public APIs   | Android-class | iOS-class    |
+-----------------------+------------------------------+
| Application Runtime / Lifecycle / Permissions        |
+------------------------------------------------------+
| System Services: power, settings, media, camera,    |
| networking, location, sensors, notifications, update |
+------------------------------------------------------+
| IPC / Service Registry / Security Policy              |
+------------------------------------------------------+
| Kernel abstraction: processes / VM / scheduler / IPC |
+------------------------------------------------------+
| HAL / Drivers / Vendor BSP                           |
+------------------------------------------------------+
| SoC / LPDDR / UFS / Display / RF / Camera / PMIC    |
+------------------------------------------------------+
```

## Current implementation

The repository now contains a small host-side C++20 framework implementing application lifecycle, service registration, permissions, package management, system server state, graphics initialization, settings, power mode and notification contracts.

It is deliberately hardware-neutral. It can be expanded into a real mobile OS only after choosing a supported ARM64 board/SoC and integrating its kernel, boot firmware, device tree and vendor drivers.

## Android

The Android-class framework is an integration layer around AOSP concepts. It does not replace AOSP and does not include Google's proprietary services.

## iOS-class

The iOS-class framework is an original architecture with similar mobile-platform responsibilities. It does not copy or reproduce Apple's proprietary implementation.
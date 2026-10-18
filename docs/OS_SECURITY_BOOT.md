# OS, Boot, and Security Design

## Boot chain

```text
Immutable boot ROM
  -> first-stage bootloader
  -> secure monitor / trusted firmware
  -> device firmware and memory initialization
  -> verified kernel and device tree
  -> system services and HALs
  -> user applications
```

Use a hardware root of trust, signed firmware, verified boot, rollback protection, recovery path, protected key storage, and audit logs. Never commit production signing keys, private keys, modem secrets, or production credentials to the repository.

## Android-class stack

- AOSP framework and system services
- ART/runtime and native libraries
- package and permission management
- graphics, media, camera, audio, sensors, location and telephony APIs
- HAL/vendor services
- Linux kernel, device tree and drivers
- vendor boot firmware and hardware

Real device support depends on the specific SoC vendor BSP and supported Android release.

## iOS-class stack

- application lifecycle and UI frameworks
- graphics, media, networking, location and sensor APIs
- permission and sandbox services
- system daemons and IPC
- kernel and driver boundary
- signed boot firmware and hardware

This repository does not contain Apple's proprietary operating system, firmware, signing keys, or private interfaces.

## Security controls

- secure and verified boot
- hardware-backed key storage
- full-disk/file-based storage encryption
- application sandboxing
- process and memory isolation
- least-privilege permissions
- signed OTA updates and anti-rollback
- secure recovery and factory reset
- biometric templates kept in an isolated trusted path
- modem/baseband isolation from the main OS where supported
- vulnerability disclosure and security update process

## Threat-model targets

Document trust boundaries between apps, framework, kernel, modem, baseband firmware, security processor, debug interfaces, and external USB. Disable production debug access unless deliberately authorized; gate engineering features by build type and hardware fuse policy.

# Development Plan

## Phase 0 — platform choice

Select a documented ARM64 development board with upstream or vendor-supported kernel, supported display/touch, Wi-Fi/Bluetooth, storage and power management. A generic prototype repository cannot supply undocumented SoC firmware.

## Phase 1 — reproducible baseline

- lock toolchain versions
- build reference C++ model and tests
- document hardware revision, SoC, RAM size, storage and display
- enable serial console and recovery
- create CI build/test workflow

## Phase 2 — kernel and drivers

- use board vendor BSP or supported upstream drivers
- implement device tree and regulator/clock configuration
- validate memory training, storage, display and touch
- validate suspend/resume and thermal limits

## Phase 3 — operating system

- integrate AOSP for Android-class system
- define original `ios_class` contracts separately; do not present it as Apple iOS
- implement HAL services, permissions, updates and diagnostics

## Phase 4 — device experience

- launcher, settings, phone, messaging, contacts, camera, gallery, browser, calculator, clock and file manager
- accessibility, localization, backup/restore and update flows

## Phase 5 — validation

- automated unit/integration tests
- long-run power/thermal testing
- security review and recovery validation
- carrier/RF/EMC/battery and regulatory certification as applicable

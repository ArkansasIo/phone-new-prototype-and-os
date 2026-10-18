# Android Integration Notes

Use the Android Open Source Project (AOSP) and supported board vendor BSP. Hardware functionality depends on vendor kernel drivers, proprietary firmware where legitimately supplied by the vendor, device tree, HAL implementations and compatibility requirements.

Recommended bring-up sequence:

1. bootloader and serial console
2. kernel and device tree
3. storage and verified boot
4. memory configuration and stability
5. display/touch
6. power, charging, thermal and suspend/resume
7. Wi-Fi/Bluetooth and sensors
8. audio and camera
9. cellular modem through the vendor-supported integration
10. HAL and framework services
11. CTS/VTS and board-specific validation

This repository is not a complete AOSP source tree and does not contain modem firmware, signing material or a bootable image.
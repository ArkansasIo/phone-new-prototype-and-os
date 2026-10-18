# Subsystem Status

The repository contains a C++20 reference model and descriptive specifications. The following areas are **architecture placeholders**, not implemented hardware drivers or boot firmware:

- boot ROM/bootloader and secure monitor
- LPDDR PHY and memory training firmware
- production kernel and device tree
- camera sensor/ISP drivers and tuning
- cellular modem protocol stack and RF calibration
- display panel initialization and touch drivers
- UFS controller firmware
- PMIC and charging configuration
- production HALs and OS framework services
- actual Android build system / AOSP source integration
- actual iOS implementation

To progress toward a real phone, first select a development board/SoC with publicly supported BSP and use its reference hardware design.
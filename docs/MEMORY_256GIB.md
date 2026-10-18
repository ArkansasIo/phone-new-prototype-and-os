# 6 GiB Memory Target

The UniversalPhoneOS reference phone profile targets **6 GiB of RAM**. This is a software/hardware reference configuration and does not by itself validate a commercial SoC, DRAM package, or memory controller.

A physical design would need:

- a compatible memory controller and PHY
- supported DRAM devices and package topology
- address-width and capacity validation
- memory training firmware
- power/thermal analysis and vendor timing data
- signal/power integrity validation
- OS physical-memory discovery and IOMMU configuration

The active prototype configuration uses 6 GiB RAM. DDR6–DDR9 and LPDDR6–LPDDR9 remain experimental labels in this project unless an official specification is independently confirmed.

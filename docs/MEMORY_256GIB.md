# 256 GiB Memory Target

The project may model 256 GiB of addressable memory in software. That configuration is a research target and does not imply a commercially available phone memory package or support from a current smartphone SoC.

A physical design would need:

- a compatible memory controller and PHY
- supported DRAM devices and package topology
- address-width and capacity validation
- memory training firmware
- power/thermal analysis and vendor timing data
- signal/power integrity validation
- OS physical-memory discovery and IOMMU configuration

DDR6–DDR9 and LPDDR6–LPDDR9 are experimental labels in this project unless a future, official specification is independently confirmed.
# Hardware Categories and Interface Guide

This is a category-level bill of materials, not a purchasing list or manufacturing schematic.

| Block | Parts | Typical interface / notes |
|---|---|---|
| SoC | CPU, GPU, NPU, ISP, video/display engines, memory controller, security engine | BGA package; needs vendor design guide and power sequencing |
| DRAM | LPDDR package and PHY | Dedicated high-speed routing; memory training required |
| Persistent storage | UFS flash/controller | UFS/M-PHY-class interface; secure boot partitions |
| Display | OLED/LTPO or LCD, display driver IC | MIPI DSI-class link; panel-specific power rails |
| Touch | Digitizer and touch controller | I2C/SPI or vendor interface; interrupt GPIO |
| Cameras | Sensor modules, optics, optional OIS, flash | MIPI CSI-class data lanes; ISP integration |
| Cellular | Modem/baseband, RF transceiver, PA, LNA, filters, duplexers, antenna switches/tuner | RF front-end must match bands and certified reference design |
| Wi-Fi/Bluetooth | Combo radio module/chip | PCIe, SDIO or USB depending on vendor |
| GNSS | Receiver and antenna path | RF interface; antenna placement matters |
| NFC | NFC controller and antenna | I2C/SPI plus matched antenna network |
| UWB | Optional UWB radio and antenna | Device-specific high-frequency RF layout |
| Audio | Codec, ADC/DAC, amps, speakers, microphones | I2S/SLIMbus-class interfaces; acoustic design required |
| Motion sensors | Accelerometer, gyroscope, magnetometer, barometer | Typically I2C/SPI, interrupts and calibration |
| Other sensors | Ambient light, proximity, Hall, temperature, fingerprint, optional depth | Sensor-specific interfaces and security constraints |
| Power | PMIC, buck/boost, LDOs, charger, fuel gauge, protection | Sequencing and load-transient validation required |
| Battery | Li-ion/Li-polymer pack with protection | Certified cell and safe charging profile required |
| USB | USB-C receptacle, Type-C/PD controller, ESD protection | USB 2/3/USB4 support depends on SoC and board design |
| Haptics | LRA/ERM motor and driver | Dedicated driver or GPIO/PWM control |
| Thermal | Graphite, vapor chamber, thermal interface material, sensors | Designed against measured SoC/DRAM/RF heat load |
| Board/mechanical | Multilayer PCB, flex cables, shielding, frame, buttons, connectors | Controlled impedance, stack-up and assembly design required |

## Electrical and safety warning

Do not design a phone PCB by connecting parts based on this table alone. Use the exact SoC and component vendor reference schematics, power-sequence requirements, layout constraints, antenna tuning guidance, battery safety limits, and laboratory validation. Cellular RF and battery/charging circuits require specialist design and regulatory testing.

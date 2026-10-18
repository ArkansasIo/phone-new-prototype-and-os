# Hardware Interface Inventory

| Subsystem | Possible interface | Integration caveat |
|---|---|---|
| LPDDR | Dedicated memory PHY | Exact DRAM/controller pairing and layout are mandatory |
| UFS | UFS/M-PHY-class | Requires controller IP, firmware and NAND package support |
| Display | MIPI DSI-class | Panel timing, rails and init sequence are vendor-specific |
| Camera | MIPI CSI-class | Sensor drivers, clocking, CSI lanes and ISP tuning required |
| Sensors | I2C/SPI/GPIO | Addresses, pull-ups, interrupt polarity and calibration required |
| Audio | I2S/SLIMbus-class | Codec clocks, routing and acoustic validation required |
| Wi-Fi/BT | SDIO/PCIe/USB | Depends on selected radio chipset and vendor drivers |
| USB-C | USB 2/3/PD-class | Connector, controller, ESD and power roles must match design |
| Cellular | Modem/RF interfaces | Vendor integration, antenna tuning and certifications required |
| GNSS/NFC/UWB | Chip-specific buses and RF | Antenna and RF layout must be tuned for enclosure |
| Power | PMIC I2C/SPI plus rails | Exact startup/shutdown sequence and load response are critical |

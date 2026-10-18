# Power, Battery, and Thermal Architecture

## Power path

```text
USB-C / wireless receiver -> charger and power-path controller
                          -> battery protection and fuel gauge
                          -> PMIC and regulators
                          -> SoC / DRAM / display / camera / RF / audio
```

## Required functions

- battery protection and validated charging profile
- temperature and current monitoring
- PMIC sequencing and regulator sizing
- dynamic voltage and frequency scaling
- sleep states, wake sources and suspend/resume
- display refresh/brightness management
- modem transmit-power and radio sleep states
- thermal throttling and emergency shutdown
- battery state-of-charge/state-of-health estimation
- charging limits based on cell temperature

## Thermal zones

Track at minimum SoC, skin/chassis, battery, DRAM, display, and RF/PA temperatures where sensors are available. Thermal policy should prioritize battery safety, skin-temperature limits, radio compliance and stable user experience.

## Modeling note

Power values in the reference C++ model are illustrative only. Production estimates require component vendor characterization, workload traces, measured regulator efficiency, PCB/package thermal models and instrumented prototypes. Do not use estimates from this project to choose a battery or charger.

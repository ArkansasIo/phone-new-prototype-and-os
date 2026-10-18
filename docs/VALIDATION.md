# Validation Matrix

| Area | Software validation | Hardware validation |
|---|---|---|
| C++ reference model | CMake and unit tests | Not applicable |
| DRAM | Capacity/bandwidth arithmetic checks | DRAM training, signal integrity, stress/temperature |
| UFS | Model configuration checks | Boot, sustained I/O, power loss and wear behavior |
| Display/touch | API/interface contract tests | Panel timing, touch accuracy, suspend/resume |
| Camera | pipeline/API contract tests | Sensor modes, ISP tuning, low-light and thermal tests |
| Cellular/RF | Integration interface tests | RF lab, carrier tests and certification |
| Battery/charging | State-machine tests | Safety testing, thermal and fault injection |
| Secure boot/update | signature and rollback tests | key provisioning and recovery validation |
| Android | unit/integration tests | CTS/VTS and board-specific tests |

Never treat a passing host-side simulation as proof that a physical device is electrically or regulatorily safe.
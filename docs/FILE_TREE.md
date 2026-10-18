# Repository Layout

- `src/`, `include/`, `tests/`: portable C++ model
- `config/`: JSON platform model configuration
- `hardware/`: component and hardware subsystem scope
- `firmware/`: boot chain responsibilities
- `kernel/`, `drivers/`, `hal/`: OS integration boundaries
- `os/android/`: AOSP integration notes
- `os/ios_class/`: independent iOS-class architecture
- `apps/`, `libraries/`, `tools/`: planned components
- `docs/`: architecture, security, power and development

Placeholder files are explicit architectural boundaries, not implemented drivers or firmware.
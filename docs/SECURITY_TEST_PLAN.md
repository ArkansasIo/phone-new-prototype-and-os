# Security Test Plan

- verify boot image signature rejection
- verify rollback index behavior
- confirm production debug ports are restricted
- confirm application processes cannot read other app data
- validate permissions for camera, microphone, location, sensors and telephony
- confirm user-data encryption and key lifecycle
- test OTA interruption, recovery and downgrade prevention
- verify that logs do not reveal secrets
- confirm keys and modem credentials are absent from source control

Use a dedicated test device and authorized security test procedures.
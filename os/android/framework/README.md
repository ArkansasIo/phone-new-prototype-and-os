# Android-Class Framework

This layer defines the interface between Android application/system APIs and the vendor HAL/kernel boundary.

Recommended production integration:

`Apps -> Android Framework -> System Server -> Native Services/HAL -> Linux Kernel -> Vendor BSP -> SoC`

Use AOSP for the actual Android platform implementation. This repository supplies original contracts and architecture, not Google's proprietary services or a complete AOSP checkout.
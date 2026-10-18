package io.arkansas.universalphone

data class SocProfile(
    val name: String,
    val cpuCores: Int,
    val gpuModel: String,
    val npuModel: String,
    val ispModel: String
)

data class MemoryProfile(
    val technology: String = "LPDDR",
    val generation: String = "prototype",
    val capacityGiB: Int = 16,
    val busBits: Int = 128,
    val dataRateMtPerSec: Long = 0
)

data class StorageProfile(
    val technology: String = "UFS",
    val capacityGiB: Int = 256,
    val bootDevice: Boolean = true
)

data class HardwareProfile(
    val soc: SocProfile,
    val memory: MemoryProfile,
    val storage: StorageProfile,
    val display: PhoneConfig
)

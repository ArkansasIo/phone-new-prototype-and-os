package io.arkansas.universalphone

interface KernelBoundary {
    fun start(): Boolean
    fun stop()
    fun uptimeMillis(): Long
    fun allocate(bytes: Long): Long
    fun release(handle: Long): Boolean
}

interface HardwareAbstractionLayer {
    fun enumerate(): List<String>
    fun open(device: String): Boolean
    fun close(device: String)
}

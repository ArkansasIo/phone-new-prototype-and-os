package io.arkansas.universalphone.network

/** Lightweight collection helpers used by the network subsystem. */
class NetworkRegistry<T> {
    private val values = linkedMapOf<String, T>()
    fun put(id: String, value: T) { values[id] = value }
    fun get(id: String): T? = values[id]
    fun remove(id: String): T? = values.remove(id)
    fun contains(id: String): Boolean = values.containsKey(id)
    fun ids(): List<String> = values.keys.toList()
    fun values(): List<T> = values.values.toList()
    fun clear() = values.clear()
    val size: Int get() = values.size
}

class RingBuffer<T>(private val capacity: Int) {
    init { require(capacity > 0) }
    private val items = ArrayDeque<T>()
    fun add(value: T) {
        if (items.size == capacity) items.removeFirst()
        items.addLast(value)
    }
    fun toList(): List<T> = items.toList()
    val size: Int get() = items.size
}

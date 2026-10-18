package io.arkansas.universalphone.network

class NetworkGraph {
    private val adjacency = linkedMapOf<String, MutableSet<String>>()

    fun connect(from: String, to: String) {
        adjacency.getOrPut(from) { linkedSetOf() }.add(to)
        adjacency.getOrPut(to) { linkedSetOf() }
    }

    fun disconnect(from: String, to: String) {
        adjacency[from]?.remove(to)
    }

    fun neighbors(nodeId: String): Set<String> = adjacency[nodeId]?.toSet() ?: emptySet()

    fun nodes(): Set<String> = adjacency.keys

    fun hasPath(from: String, to: String): Boolean {
        if (from == to) return true
        val visited = mutableSetOf<String>()
        val queue = ArrayDeque<String>()
        queue.addLast(from)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (!visited.add(current)) continue
            neighbors(current).forEach { next ->
                if (next == to) return true
                queue.addLast(next)
            }
        }
        return false
    }
}
